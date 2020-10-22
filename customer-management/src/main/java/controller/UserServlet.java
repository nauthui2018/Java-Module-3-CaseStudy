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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
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
                case "updatePassword":
                    updatePassword(request, response);
                    break;
                case "updateInformation":
                    updateInformation(request, response);
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
                case "register":
                    showRegisterForm(request, response);
                    break;
                case "login":
                    showLoginForm(request, response);
                    break;
                case "list":
                    listUsers(request, response);
                    break;
                case "view":
                    view(request, response);
                    break;
                case "updatePassword":
                    showUpdatePasswordForm(request, response);
                    break;
                case "updateInformation":
                    showUpdateInformationForm(request, response);
                    break;
                case "logout":
                    logout(request, response);
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
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User)session.getAttribute("user");
            request.setAttribute("userUsername", user.getUserUsername());
            request.setAttribute("buttonName", "Logout");
            request.setAttribute("iconName", "fas fa-sign-out-alt");
            request.setAttribute("actionName", "logout");
        } else {
            request.setAttribute("buttonName", "Login");
            request.setAttribute("actionName", "login");
            request.setAttribute("iconName", "fas fa-user");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/login.jsp");
        dispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String userUsername = request.getParameter("userUsername");
        String userPassword = request.getParameter("userPassword");
        User user = userDAO.login(userUsername, userPassword);
        RequestDispatcher dispatcher;
        if (userDAO.login(userUsername, userPassword) != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if (user.isUserAdmin()) {
                List<Customer> listCustomer = customerDAO.findAll();
                request.setAttribute("listCustomer", listCustomer);
                List<Rank> listRank = rankDAO.findAll();
                request.setAttribute("listRank", listRank);
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
            } else {
                request.setAttribute("userUsername", userUsername);
                request.setAttribute("buttonName", "Logout");
                request.setAttribute("iconName", "fas fa-sign-out-alt");
                request.setAttribute("actionName", "logout");
                request.setAttribute("message", "Welcome back");
                dispatcher = request.getRequestDispatcher("home.jsp");
            }
        } else {
            request.setAttribute("userUsername", userUsername);
            request.setAttribute("message", "Incorrect username or password. Please try again!");
            dispatcher = request.getRequestDispatcher("user?action=login");
        }
        dispatcher.forward(request, response);
    }
    private void view(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        request.setAttribute("user", user);
        Customer customer = customerDAO.selectById(user.getCustomerID());
        request.setAttribute("customer", customer);
        Province province = provinceDAO.selectById(customer.getProvinceID());
        request.setAttribute("province", province);
        Rank rank = rankDAO.selectById(customer.getRankID());
        request.setAttribute("rank", rank);
        String action;
        if (user.isUserAdmin()) {
            action = "/customers";
        } else {
            action = "/users";
        }
        request.setAttribute("action", action);
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

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/register.jsp");
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
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String provinceID = request.getParameter("provinceID");
        String totalOrders = request.getParameter("totalOrders");
        String totalAmounts = request.getParameter("totalAmounts");
        String rankID = request.getParameter("rankID");
        Customer customer = new Customer();
        HashMap<String, String> validationResult = new HashMap<>();
        validationResult = customer.validationCustomer(lastName, firstName, gender, dob, mobile, address, email, provinceID, totalOrders, totalAmounts, rankID);
        RequestDispatcher dispatcher;
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        if (validationResult.size() == 0) {
            customerDAO.add(customer);
            Customer newCustomer = customerDAO.getNewCustomer();
            String userUsername = request.getParameter("userUsername");
            String userPassword = request.getParameter("userPassword");
            User user = new User(userUsername, userPassword, newCustomer.getCustomerID(), false);
            userDAO.add(user);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            request.setAttribute("userUsername", userUsername);
            request.setAttribute("buttonName", "Logout");
            request.setAttribute("iconName", "fas fa-sign-out-alt");
            request.setAttribute("actionName", "logout");
            dispatcher = request.getRequestDispatcher("home.jsp");
        } else {
            request.setAttribute("validationResult", validationResult);
            request.setAttribute("customer", customer);
            dispatcher = request.getRequestDispatcher("user/add.jsp");
        }
        dispatcher.forward(request, response);

    }

    private void showUpdatePasswordForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/updatePassword.jsp");
        dispatcher.forward(request, response);
    }

    private void updatePassword(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String userUsername = user.getUserUsername();
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        RequestDispatcher dispatcher;
        if (userDAO.login(userUsername, currentPassword) != null) {
            User newUser = new User(userUsername, newPassword, user.getCustomerID(), user.isUserAdmin());
            userDAO.update(newUser);
            session.setAttribute("user", newUser);
            request.setAttribute("message", "Password is changed");
            if (newUser.isUserAdmin()) {
                List<Customer> listCustomer = customerDAO.findAll();
                request.setAttribute("listCustomer", listCustomer);
                dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
            } else {
                request.setAttribute("userUsername", userUsername);
                request.setAttribute("buttonName", "Logout");
                request.setAttribute("iconName", "fas fa-sign-out-alt");
                request.setAttribute("actionName", "logout");
                dispatcher = request.getRequestDispatcher("home.jsp");
            }
        } else {
            request.setAttribute("user", user);
            request.setAttribute("message", "Current password is incorrect. Please try again!");
            dispatcher = request.getRequestDispatcher("user/updatePassword.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void showUpdateInformationForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        request.setAttribute("user", user);
        Customer customer = customerDAO.selectById(user.getCustomerID());
        request.setAttribute("customer", customer);
        List<Province> listProvince =  provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/updateInformation.jsp");
        dispatcher.forward(request, response);
    }

    private void updateInformation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Customer customer = customerDAO.selectById(user.getCustomerID());
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String dob = request.getParameter("dob");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        int provinceID = Integer.parseInt(request.getParameter("provinceID"));
        Customer newCustomer = new Customer(customer.getCustomerID(), lastName, firstName, gender, dob, mobile, address, email, provinceID, customer.getTotalOrders(), customer.getTotalAmounts(), customer.getRankID());
        customerDAO.update(newCustomer);
        request.setAttribute("message", "Information is updated");
        RequestDispatcher dispatcher;
        if (user.isUserAdmin()) {
            List<Customer> listCustomer = customerDAO.findAll();
            request.setAttribute("listCustomer", listCustomer);
            dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
        } else {
            request.setAttribute("userUsername", user.getUserUsername());
            request.setAttribute("buttonName", "Logout");
            request.setAttribute("iconName", "fas fa-sign-out-alt");
            request.setAttribute("actionName", "logout");
            dispatcher = request.getRequestDispatcher("home.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user.isUserAdmin()) {
            dispatcher = request.getRequestDispatcher("user/login.jsp");
        } else {
            request.setAttribute("buttonName", "Login");
            request.setAttribute("actionName", "login");
            request.setAttribute("iconName", "fas fa-user");
            dispatcher = request.getRequestDispatcher("home.jsp");
        }
        session.invalidate();
        dispatcher.forward(request, response);
    }
}
