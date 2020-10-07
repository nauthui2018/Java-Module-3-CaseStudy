package controller;

import dao.CustomerDAO;
import dao.UserDAO;
import model.Customer;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    private CustomerDAO customerDAO = new CustomerDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "add":
                    registerUser(request, response);
                    break;
                case "update":
                    updatePassword(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                case "login":
                    login(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "login":
                    showLoginForm(request, response);
                    break;
                case "add":
                    showRegisterForm(request, response);
                    break;
                case "update":
                    showUpdatePasswordForm(request, response);
                    break;
                case "delete":
                    showDeleteForm(request, response);
                    break;
                default:
                    listUsers(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.findAll();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/listUser.jsp");
        dispatcher.forward(request, response);
    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/login.jsp");
        dispatcher.forward(request, response);
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/register.jsp");
        dispatcher.forward(request, response);
    }

    private void showUpdatePasswordForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String userUsername = request.getParameter("userUsername");
        User user = userDAO.selectByUsername(userUsername);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/update.jsp");
        request.setAttribute("user", user);
        dispatcher.forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String userUsername = request.getParameter("userUsername");
        User user = userDAO.selectByUsername(userUsername);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/delete.jsp");
        request.setAttribute("user", user);
        dispatcher.forward(request, response);
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String userUsername = request.getParameter("userUsername");
        String userPassword = request.getParameter("userPassword");
        //in case customer ID is existed
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        User user = new User(userUsername, userPassword, customerID, false);
        RequestDispatcher dispatcher;
        if (userDAO.selectByUsername(userUsername) != null) {
            userDAO.add(user);
            List<User> listUser = userDAO.findAll();
            request.setAttribute("listUser", listUser);
            request.setAttribute("message", "Register successful!");
            dispatcher = request.getRequestDispatcher("user/listUser.jsp");
        } else {
            request.setAttribute("message", "Username is existed!");
            dispatcher = request.getRequestDispatcher("user/register.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String userUsername = request.getParameter("userUsername");
        String userPassword = request.getParameter("userPassword");
        RequestDispatcher dispatcher;
        if (userDAO.login(userUsername, userPassword)) {
            User user = userDAO.selectByUsername(userUsername);
            request.setAttribute("message", "Welcome back");
            if (user.isUserAdmin()) {
                List<Customer> listCustomer = customerDAO.findAll();
                request.setAttribute("listCustomer", listCustomer);
                dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
            } else {
                dispatcher = request.getRequestDispatcher("www.google.com");
            }
        } else {
            request.setAttribute("message", "Incorrect username or password. Please try again!");
            dispatcher = request.getRequestDispatcher("rank/login.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void updatePassword(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String userUsername = request.getParameter("userUsername");
        String userPassword = request.getParameter("userPassword");
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        boolean userAdmin = Boolean.parseBoolean(request.getParameter("userAdmin"));
        User user = new User(userUsername, userPassword, customerID, userAdmin);
        userDAO.update(user);
        //in case the password is accepted and updated successfully
        RequestDispatcher dispatcher;
        request.setAttribute("message", "Password is changed");
        if (user.isUserAdmin()) {
            List<Customer> listCustomer = customerDAO.findAll();
            request.setAttribute("listCustomer", listCustomer);
            dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("www.google.com");
        }
        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String userUsername = request.getParameter("userUsername");
        User user = userDAO.selectByUsername(userUsername);
        userDAO.delete(user);
        List<User> listUser = userDAO.findAll();
        request.setAttribute("listUser", listUser);
        request.setAttribute("message", "Register successful!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/listUser.jsp");
        dispatcher.forward(request, response);
    }
}
