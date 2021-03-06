package controller;

import dao.CustomerDAO;
import dao.ProvinceDAO;
import dao.RankDAO;
import dao.UserDAO;
import model.Customer;
import model.Province;
import model.Rank;
import model.User;
import service.ValidateHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private CustomerDAO customerDAO = new CustomerDAO();
    private RankDAO rankDAO = new RankDAO();
    private ProvinceDAO provinceDAO = new ProvinceDAO();
    private UserDAO userDAO = new UserDAO();
    private ValidateHelper validateHelper = new ValidateHelper();

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
                    addNewCustomer(request, response);
                    break;
                case "update":
                    updateCustomer(request, response);
                    break;
                case "delete":
                    deleteCustomer(request, response);
                    break;
                case "searchName":
                    searchByName(request, response);
                    break;
                case "searchRank":
                    listCustomerByRank(request, response);
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
                case "add":
                    showAddForm(request, response);
                    break;
                case "update":
                    showUpdateForm(request, response);
                    break;
                case "delete":
                    showDeleteForm(request, response);
                    break;
                case "view":
                    viewDetail(request, response);
                    break;
                case "sort":
                    sortCustomer(request, response);
                    break;
                default:
                    listCustomer(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                List<Customer> listCustomer = customerDAO.findAll();
                request.setAttribute("listCustomer", listCustomer);
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                List<Rank> listRank = rankDAO.findAll();
                request.setAttribute("listRank", listRank);
                List<User> listUser = userDAO.findAll();
                request.setAttribute("listUser", listUser);
                request.setAttribute("sort", "sort");
                dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
            } else {
                request.setAttribute("user", user);
                request.setAttribute("buttonName", "Logout");
                request.setAttribute("iconName", "fas fa-sign-out-alt");
                request.setAttribute("actionName", "logout");
                dispatcher = request.getRequestDispatcher("home.jsp");
            }
        } else {
            dispatcher = request.getRequestDispatcher("user/login.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void sortCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                List<Customer> listCustomer = customerDAO.sortByName();
                request.setAttribute("listCustomer", listCustomer);
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                List<Rank> listRank = rankDAO.findAll();
                request.setAttribute("listRank", listRank);
                List<User> listUser = userDAO.findAll();
                request.setAttribute("listUser", listUser);
                request.setAttribute("sort", "");
                dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
            } else {
                request.setAttribute("user", user);
                request.setAttribute("buttonName", "Logout");
                request.setAttribute("iconName", "fas fa-sign-out-alt");
                request.setAttribute("actionName", "logout");
                dispatcher = request.getRequestDispatcher("home.jsp");
            }
        } else {
            dispatcher = request.getRequestDispatcher("user/login.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                String searchName = request.getParameter("searchName");
                List<Customer> listCustomer = customerDAO.selectByName(searchName);
                request.setAttribute("listCustomer", listCustomer);
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                List<Rank> listRank = rankDAO.findAll();
                request.setAttribute("listRank", listRank);
                request.setAttribute("searchName", searchName);
                request.setAttribute("sort", "");
                dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
            } else {
                request.setAttribute("user", user);
                request.setAttribute("buttonName", "Logout");
                request.setAttribute("iconName", "fas fa-sign-out-alt");
                request.setAttribute("actionName", "logout");
                dispatcher = request.getRequestDispatcher("home.jsp");
            }
        } else {
            dispatcher = request.getRequestDispatcher("user/login.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void listCustomerByRank(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                int rankID = Integer.parseInt(request.getParameter("rankID"));
                List<Customer> listCustomerByRank = customerDAO.selectByRank(rankID);
                request.setAttribute("listCustomer", listCustomerByRank);
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                List<Rank> listRank = rankDAO.findAll();
                request.setAttribute("listRank", listRank);
                request.setAttribute("sort", "");
                dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
            } else {
                request.setAttribute("user", user);
                request.setAttribute("buttonName", "Logout");
                request.setAttribute("iconName", "fas fa-sign-out-alt");
                request.setAttribute("actionName", "logout");
                dispatcher = request.getRequestDispatcher("home.jsp");
            }
        } else {
            dispatcher = request.getRequestDispatcher("user/login.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void viewDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                List<Rank> listRank = rankDAO.findAll();
                request.setAttribute("listRank", listRank);
                int customerID = Integer.parseInt(request.getParameter("customerID"));
                Customer customer = customerDAO.selectById(customerID);
                request.setAttribute("customer", customer);
                dispatcher = request.getRequestDispatcher("customer/view.jsp");
            } else {
                request.setAttribute("user", user);
                request.setAttribute("buttonName", "Logout");
                request.setAttribute("iconName", "fas fa-sign-out-alt");
                request.setAttribute("actionName", "logout");
                dispatcher = request.getRequestDispatcher("home.jsp");
            }
        } else {
            dispatcher = request.getRequestDispatcher("user/login.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                List<Rank> listRank = rankDAO.findAll();
                request.setAttribute("listRank", listRank);
                dispatcher = request.getRequestDispatcher("customer/add.jsp");
            } else {
                request.setAttribute("user", user);
                request.setAttribute("buttonName", "Logout");
                request.setAttribute("iconName", "fas fa-sign-out-alt");
                request.setAttribute("actionName", "logout");
                dispatcher = request.getRequestDispatcher("home.jsp");
            }
        } else {
            dispatcher = request.getRequestDispatcher("user/login.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                List<Rank> listRank = rankDAO.findAll();
                request.setAttribute("listRank", listRank);
                int customerID = Integer.parseInt(request.getParameter("customerID"));
                Customer customer = customerDAO.selectById(customerID);
                request.setAttribute("customer", customer);
                dispatcher = request.getRequestDispatcher("customer/update.jsp");
            } else {
                request.setAttribute("user", user);
                request.setAttribute("buttonName", "Logout");
                request.setAttribute("iconName", "fas fa-sign-out-alt");
                request.setAttribute("actionName", "logout");
                dispatcher = request.getRequestDispatcher("home.jsp");
            }
        } else {
            dispatcher = request.getRequestDispatcher("user/login.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                List<Rank> listRank = rankDAO.findAll();
                request.setAttribute("listRank", listRank);
                int customerID = Integer.parseInt(request.getParameter("customerID"));
                Customer customer = customerDAO.selectById(customerID);
                request.setAttribute("customer", customer);
                dispatcher = request.getRequestDispatcher("customer/delete.jsp");
            } else {
                request.setAttribute("user", user);
                request.setAttribute("buttonName", "Logout");
                request.setAttribute("iconName", "fas fa-sign-out-alt");
                request.setAttribute("actionName", "logout");
                dispatcher = request.getRequestDispatcher("home.jsp");
            }
        } else {
            dispatcher = request.getRequestDispatcher("user/login.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void addNewCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
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
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                List<Rank> listRank = rankDAO.findAll();
                request.setAttribute("listRank", listRank);
                if (validationResult.size() == 0) {
                    customerDAO.add(customer);
                    List<Customer> listCustomer = customerDAO.findAll();
                    request.setAttribute("listCustomer", listCustomer);
                    request.setAttribute("message", "A new customer is added!");
                    request.setAttribute("sort", "");
                    dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
                } else {
                    request.setAttribute("validationResult", validationResult);
                    request.setAttribute("customer", customer);
                    dispatcher = request.getRequestDispatcher("customer/add.jsp");
                }
            } else {
                request.setAttribute("user", user);
                request.setAttribute("buttonName", "Logout");
                request.setAttribute("iconName", "fas fa-sign-out-alt");
                request.setAttribute("actionName", "logout");
                dispatcher = request.getRequestDispatcher("home.jsp");
            }
        } else {
            dispatcher = request.getRequestDispatcher("user/login.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                int customerID = Integer.parseInt(request.getParameter("customerID"));
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
                customer.setCustomerID(customerID);
                HashMap<String, String> validationResult = customer.validationCustomer(lastName, firstName, gender, dob, mobile, address, email, provinceID, totalOrders, totalAmounts, rankID);
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                List<Rank> listRank = rankDAO.findAll();
                request.setAttribute("listRank", listRank);
                if (validationResult.size() == 0) {
                    customerDAO.update(customer);
                    List<Customer> listCustomer = customerDAO.findAll();
                    request.setAttribute("listCustomer", listCustomer);
                    request.setAttribute("sort", "");
                    request.setAttribute("message", "Customer is updated!");
                    dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
                } else {
                    request.setAttribute("validationResult", validationResult);
                    request.setAttribute("customer", customer);
                    dispatcher = request.getRequestDispatcher("customer/update.jsp");
                }
            } else {
                request.setAttribute("user", user);
                request.setAttribute("buttonName", "Logout");
                request.setAttribute("iconName", "fas fa-sign-out-alt");
                request.setAttribute("actionName", "logout");
                dispatcher = request.getRequestDispatcher("home.jsp");
            }
        } else {
            dispatcher = request.getRequestDispatcher("user/login.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                int customerID = Integer.parseInt(request.getParameter("customerID"));
                Customer customer = customerDAO.selectById(customerID);
                customerDAO.delete(customer);
                List<Customer> listCustomer = customerDAO.findAll();
                request.setAttribute("listCustomer", listCustomer);
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                List<Rank> listRank = rankDAO.findAll();
                request.setAttribute("listRank", listRank);
                request.setAttribute("sort", "");
                request.setAttribute("message", "Customer is deleted!");
                dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
            } else {
                request.setAttribute("user", user);
                request.setAttribute("buttonName", "Logout");
                request.setAttribute("iconName", "fas fa-sign-out-alt");
                request.setAttribute("actionName", "logout");
                dispatcher = request.getRequestDispatcher("home.jsp");
            }
        } else {
            dispatcher = request.getRequestDispatcher("user/login.jsp");
        }
        dispatcher.forward(request, response);
    }
}
