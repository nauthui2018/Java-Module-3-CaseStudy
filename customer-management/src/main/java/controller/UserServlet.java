package controller;

import dao.CustomerDAO;
import dao.ProvinceDAO;
import dao.RankDAO;
import dao.UserDAO;
import model.Customer;
import model.Province;
import model.Rank;
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
    private RankDAO rankDAO = new RankDAO();
    private ProvinceDAO provinceDAO = new ProvinceDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "register":
                    registerUser(request, response);
                    break;
                case "add":
                    addUser(request, response);
                    break;
                case "update":
                    updatePassword(request, response);
                    break;
                case "login":
                    login(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;
                case "showLoginForm":
                    showLoginForm(request, response);
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
                case "register":
                    showRegisterForm(request, response);
                    break;
                case "update":
                    showUpdatePasswordForm(request, response);
                    break;
                case "view":
                    view(request, response);
                    break;
                case "list":
                    listUsers(request, response);
                    break;
                default:
                    showHomepage(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showHomepage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        request.setAttribute("buttonName", "Login");
        request.setAttribute("actionName", "showLoginForm");
        request.setAttribute("iconLogin", "fas fa-user");
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    private void view(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/view.jsp");
        dispatcher.forward(request, response);
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.findAll();
        request.setAttribute("listUser", listUser);
        List<Customer> listCustomer = customerDAO.findAll();
        request.setAttribute("listCustomer", listCustomer);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
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
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/update.jsp");
        dispatcher.forward(request, response);
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String userUsername = request.getParameter("userUsername");
        String userPassword = request.getParameter("userPassword");
        request.setAttribute("userUsername", userUsername);
        request.setAttribute("userPassword", userPassword);
        RequestDispatcher dispatcher;
        if (userDAO.checkUsername(userUsername)) {
            request.setAttribute("message", "This username is existed. Please try other username!");
            dispatcher = request.getRequestDispatcher("user/register.jsp");
        } else {
            List<Province> listProvince = provinceDAO.findAll();
            request.setAttribute("listProvince", listProvince);
            dispatcher = request.getRequestDispatcher("user/add.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String dob = request.getParameter("dob");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        int provinceID = Integer.parseInt(request.getParameter("provinceID"));
        int totalOrders = Integer.parseInt(request.getParameter("totalOrders"));
        double totalAmounts = Double.parseDouble(request.getParameter("totalAmounts"));
        int rankID = Integer.parseInt(request.getParameter("rankID"));
        Customer newCustomer = new Customer(lastName, firstName, gender, dob, mobile, address, email, provinceID, totalOrders, totalAmounts, rankID);
        customerDAO.add(newCustomer);
        String userUsername = request.getParameter("userUsername");
        String userPassword = request.getParameter("userPassword");
        Customer customer = customerDAO.getNewCustomer();
        User user = new User(userUsername, userPassword, customer.getCustomerID(), false);
        userDAO.add(user);
        request.setAttribute("userUsername", userUsername);
        request.setAttribute("buttonName", "Logout");
        request.setAttribute("iconLogin", "fas fa-sign-out-alt");
        request.setAttribute("status", "logout");
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
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
                request.setAttribute("userUsername", userUsername);
                dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
            } else {
                request.setAttribute("userUsername", userUsername);
                request.setAttribute("buttonName", "Logout");
                request.setAttribute("iconLogin", "fas fa-sign-out-alt");
                request.setAttribute("status", "logout");
                dispatcher = request.getRequestDispatcher("home.jsp");
            }
        } else {
            request.setAttribute("userUsername", userUsername);
            request.setAttribute("message", "Incorrect username or password. Please try again!");
            dispatcher = request.getRequestDispatcher("user/login.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String userUsername = request.getParameter("userUsername");
        User user = userDAO.selectByUsername(userUsername);
        RequestDispatcher dispatcher;
        if (user.isUserAdmin()) {
            dispatcher = request.getRequestDispatcher("user/login.jsp");
        } else {
            request.setAttribute("userUsername", "");
            request.setAttribute("buttonName", "Login");
            request.setAttribute("status", "login");
            request.setAttribute("iconLogin", "fas fa-user");
            dispatcher = request.getRequestDispatcher("home.jsp");
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
            dispatcher = request.getRequestDispatcher("home.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void resetForm() {
        String message = "";
    }
}
