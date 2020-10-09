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

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private CustomerDAO customerDAO = new CustomerDAO();
    private RankDAO rankDAO = new RankDAO();
    private ProvinceDAO provinceDAO = new ProvinceDAO();
    private UserDAO userDAO = new UserDAO();

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
                    listCustomerByName(request, response);
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
        List<Customer> listCustomer = customerDAO.findAll();
        request.setAttribute("listCustomer", listCustomer);
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        List<User> listUser = userDAO.findAll();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
        dispatcher.forward(request, response);
    }

    private void listCustomerByName(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String searchName = request.getParameter("searchName");
        List<Customer> listCustomer = customerDAO.selectByName(searchName);
        request.setAttribute("listCustomer", listCustomer);
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        request.setAttribute("searchName", searchName);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
        dispatcher.forward(request, response);
    }

    private void listCustomerByRank(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int rankID = Integer.parseInt(request.getParameter("rankID"));
        List<Customer> listCustomerByRank = customerDAO.selectByRank(rankID);
        request.setAttribute("listCustomer", listCustomerByRank);
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
        dispatcher.forward(request, response);
    }

    private void viewDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        Customer customer = customerDAO.selectById(customerID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/view.jsp");
        request.setAttribute("customer", customer);
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/add.jsp");
        dispatcher.forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        Customer customer = customerDAO.selectById(customerID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/update.jsp");
        request.setAttribute("customer", customer);
        dispatcher.forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        Customer customer = customerDAO.selectById(customerID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/delete.jsp");
        request.setAttribute("customer", customer);
        dispatcher.forward(request, response);
    }

    private void addNewCustomer(HttpServletRequest request, HttpServletResponse response)
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
        List<Customer> listCustomer = customerDAO.findAll();
        request.setAttribute("listCustomer", listCustomer);
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        request.setAttribute("message", "A new customer is added!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
        dispatcher.forward(request, response);
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));
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
        Customer customer = new Customer(customerID, lastName, firstName, gender, dob, mobile, address, email, provinceID, totalOrders, totalAmounts, rankID);
        customerDAO.update(customer);
        List<Customer> listCustomer = customerDAO.findAll();
        request.setAttribute("listCustomer", listCustomer);
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        Customer customer = customerDAO.selectById(customerID);
        customerDAO.delete(customer);
        List<Customer> listCustomer = customerDAO.findAll();
        request.setAttribute("listCustomer", listCustomer);
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/listCustomer.jsp");
        dispatcher.forward(request, response);
    }
}
