package controller;

import dao.CustomerDAO;
import dao.ProvinceDAO;
import dao.RankDAO;
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
import java.util.List;

@WebServlet(name = "ProvinceServlet", urlPatterns = "/provinces")
public class ProvinceServlet extends HttpServlet {
    private ProvinceDAO provinceDAO = new ProvinceDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private RankDAO rankDAO = new RankDAO();

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
                    addNewProvince(request, response);
                    break;
                case "update":
                    updateProvince(request, response);
                    break;
                case "showDeleteForm":
                    takeActionBeforeDelete(request, response);
                    break;
                case "updateCustomersAndDeleteProvince":
                    updateCustomersAndDeleteProvince(request, response);
                    break;
                case "deleteCustomersAndDeleteProvince":
                    deleteCustomersAndDeleteProvince(request, response);
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
                case "showDeleteForm":
                    showDeleteForm(request, response);
                    break;
                default:
                    listProvince(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProvince(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                dispatcher = request.getRequestDispatcher("province/listProvince.jsp");
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
                dispatcher = request.getRequestDispatcher("province/add.jsp");
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
                int provinceID = Integer.parseInt(request.getParameter("provinceID"));
                Province province = provinceDAO.selectById(provinceID);
                request.setAttribute("province", province);
                dispatcher = request.getRequestDispatcher("province/update.jsp");
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
                int provinceID = Integer.parseInt(request.getParameter("provinceID"));
                Province province = provinceDAO.selectById(provinceID);
                request.setAttribute("province", province);
                dispatcher = request.getRequestDispatcher("province/deleteForm.jsp");
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

    private void takeActionBeforeDelete(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                int provinceID = Integer.parseInt(request.getParameter("provinceID"));
                Province province = provinceDAO.selectById(provinceID);
                request.setAttribute("province", province);
                List<Customer> customers = customerDAO.selectByProvince(provinceID);
                request.setAttribute("listCustomer", customers);
                List<Province> provinces = provinceDAO.listAfterDelete(provinceID);
                request.setAttribute("listProvince", provinces);
                List<Rank> ranks = rankDAO.findAll();
                request.setAttribute("listRank", ranks);
                int actionCode = Integer.parseInt(request.getParameter("actionCode"));
                switch (actionCode) {
                    case 1:
                        dispatcher = request.getRequestDispatcher("province/updateCustomers.jsp");
                        break;
                    case 2:
                        dispatcher = request.getRequestDispatcher("province/deleteCustomers.jsp");
                        break;
                    default:
                        request.setAttribute("message", "Please select action!");
                        dispatcher = request.getRequestDispatcher("province/deleteForm.jsp");
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

    private void addNewProvince(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                String provinceName = request.getParameter("provinceName");
                String provinceCode = request.getParameter("provinceCode");
                List<Province> provinces = provinceDAO.findAll();
                if (provinceDAO.checkProvinceName(provinceName, provinces) || provinceDAO.checkProvinceCode(provinceCode, provinces)) {
                    if (provinceDAO.checkProvinceName(provinceName, provinces)) {
                        request.setAttribute("existedName", "This province is existed!");
                        request.setAttribute("provinceName", provinceName);
                    }
                    if (provinceDAO.checkProvinceCode(provinceCode, provinces)) {
                        request.setAttribute("existedCode", "This code is existed!");
                        request.setAttribute("provinceCode", provinceCode);
                    }
                    dispatcher = request.getRequestDispatcher("province/add.jsp");
                } else {
                    Province province = new Province(provinceName, provinceCode);
                    provinceDAO.add(province);
                    List<Province> listProvince = provinceDAO.findAll();
                    request.setAttribute("listProvince", listProvince);
                    request.setAttribute("message", "A new province is added!");
                    dispatcher = request.getRequestDispatcher("province/listProvince.jsp");
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

    private void updateProvince(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                int provinceID = Integer.parseInt(request.getParameter("provinceID"));
                String provinceName = request.getParameter("provinceName");
                String provinceCode = request.getParameter("provinceCode");
                Province province = provinceDAO.selectById(provinceID);
                List<Province> provinces = provinceDAO.listAfterDelete(provinceID);
                if (provinceDAO.checkProvinceName(provinceName, provinces) || provinceDAO.checkProvinceCode(provinceCode, provinces)) {
                    if (provinceDAO.checkProvinceName(provinceName, provinces)) {
                        request.setAttribute("existedName", "This province is existed!");
                        request.setAttribute("province", province);
                    }
                    if (provinceDAO.checkProvinceCode(provinceCode, provinces)) {
                        request.setAttribute("existedCode", "This code is existed!");
                        request.setAttribute("province", province);
                    }
                    dispatcher = request.getRequestDispatcher("province/update.jsp");
                } else {
                    Province newProvince = new Province(provinceID, provinceName, provinceCode);
                    provinceDAO.update(newProvince);
                    List<Province> listProvince = provinceDAO.findAll();
                    request.setAttribute("listProvince", listProvince);
                    request.setAttribute("message", "Information is updated!");
                    dispatcher = request.getRequestDispatcher("province/listProvince.jsp");
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

    private void updateCustomersAndDeleteProvince(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                int currentProvinceID = Integer.parseInt(request.getParameter("currentProvinceID"));
                int newProvinceID = Integer.parseInt(request.getParameter("newProvinceID"));
                customerDAO.updateCustomerByProvince(currentProvinceID, newProvinceID);
                Province province = provinceDAO.selectById(currentProvinceID);
                provinceDAO.delete(province);
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                String message = "Province '" + province.getProvinceName() + "' was deleted";
                request.setAttribute("message", message);
                dispatcher = request.getRequestDispatcher("province/listProvince.jsp");
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

    private void deleteCustomersAndDeleteProvince(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        RequestDispatcher dispatcher;
        if (user != null) {
            if (user.isUserAdmin()) {
                int provinceID = Integer.parseInt(request.getParameter("provinceID"));
                customerDAO.deleteCustomerByProvince(provinceID);
                Province province = provinceDAO.selectById(provinceID);
                provinceDAO.delete(province);
                List<Province> listProvince = provinceDAO.findAll();
                request.setAttribute("listProvince", listProvince);
                String message = "Province '" + province.getProvinceName() + "' and all related customers were deleted";
                request.setAttribute("message", message);
                dispatcher = request.getRequestDispatcher("province/listProvince.jsp");
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
