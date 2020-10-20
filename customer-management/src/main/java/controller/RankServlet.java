package controller;

import dao.CustomerDAO;
import dao.ProvinceDAO;
import dao.RankDAO;
import model.Customer;
import model.Province;
import model.Rank;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RankServlet", urlPatterns = "/ranks")
public class RankServlet extends HttpServlet {
    private RankDAO rankDAO = new RankDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
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
                case "add":
                    addNewRank(request, response);
                    break;
                case "update":
                    updateRank(request, response);
                    break;
                case "showDeleteForm":
                    takeActionBeforeDelete(request, response);
                    break;
                case "updateCustomersAndDeleteRank":
                    updateCustomersAndDeleteRank(request, response);
                    break;
                case "deleteCustomersAndDeleteRank":
                    deleteCustomersAndDeleteRank(request, response);
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
                    listRank(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listRank(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rank/listRank.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("rank/add.jsp");
        dispatcher.forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int rankID = Integer.parseInt(request.getParameter("rankID"));
        Rank rank = rankDAO.selectById(rankID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rank/update.jsp");
        request.setAttribute("rank", rank);
        dispatcher.forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int rankID = Integer.parseInt(request.getParameter("rankID"));
        Rank rank = rankDAO.selectById(rankID);
        request.setAttribute("rank", rank);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rank/deleteForm.jsp");
        dispatcher.forward(request, response);
    }

    private void takeActionBeforeDelete(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int rankID = Integer.parseInt(request.getParameter("rankID"));
        Rank rank = rankDAO.selectById(rankID);
        request.setAttribute("rank", rank);
        List<Customer> customers = customerDAO.selectByRank(rankID);
        request.setAttribute("listCustomer", customers);
        List<Rank> ranks = rankDAO.listAfterDelete(rankID);
        request.setAttribute("listRank", ranks);
        List<Province> provinces = provinceDAO.findAll();
        request.setAttribute("listProvince", provinces);
        int actionCode = Integer.parseInt(request.getParameter("actionCode"));
        RequestDispatcher dispatcher;
        switch (actionCode) {
            case 1:
                dispatcher = request.getRequestDispatcher("rank/updateCustomers.jsp");
                break;
            case 2:
                dispatcher = request.getRequestDispatcher("rank/deleteCustomers.jsp");
                break;
            default:
                request.setAttribute("message", "Please select action!");
                dispatcher = request.getRequestDispatcher("rank/deleteForm.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void addNewRank(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String rankName = request.getParameter("rankName");
        RequestDispatcher dispatcher;
        if (rankDAO.checkRankName(rankName)) {
            request.setAttribute("message", "This rank is existed!");
            request.setAttribute("rankName", rankName);
            dispatcher = request.getRequestDispatcher("rank/add.jsp");
        } else {
            Rank rank = new Rank(rankName);
            rankDAO.add(rank);
            List<Rank> listRank = rankDAO.findAll();
            request.setAttribute("listRank", listRank);
            request.setAttribute("message", "A new rank is added!");
            dispatcher = request.getRequestDispatcher("rank/listRank.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void updateRank(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int rankID = Integer.parseInt(request.getParameter("rankID"));
        Rank rank = rankDAO.selectById(rankID);
        String rankName = request.getParameter("rankName");
        RequestDispatcher dispatcher;
        if (rankDAO.checkRankName(rankName)) {
            request.setAttribute("message", "This rank is existed!");
            request.setAttribute("rank", rank);
            request.setAttribute("rankName", rankName);
            dispatcher = request.getRequestDispatcher("rank/update.jsp");
        } else {
            Rank newRank = new Rank(rankID, rankName);
            rankDAO.update(newRank);
            List<Rank> listRank = rankDAO.findAll();
            request.setAttribute("listRank", listRank);
            request.setAttribute("message", "Rank name is updated!");
            dispatcher = request.getRequestDispatcher("rank/listRank.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void updateCustomersAndDeleteRank(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int currentRankID = Integer.parseInt(request.getParameter("currentRankID"));
        int newRankID = Integer.parseInt(request.getParameter("newRankID"));
        customerDAO.updateCustomerByRank(currentRankID, newRankID);
        Rank rank = rankDAO.selectById(currentRankID);
        rankDAO.delete(rank);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        String message = "Rank '" + rank.getRankName() + "' was deleted";
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rank/listRank.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteCustomersAndDeleteRank(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int rankID = Integer.parseInt(request.getParameter("rankID"));
        customerDAO.deleteCustomerByRank(rankID);
        Rank rank = rankDAO.selectById(rankID);
        rankDAO.delete(rank);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        String message = "Rank '" + rank.getRankName() + "' and all related customers were deleted";
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rank/listRank.jsp");
        dispatcher.forward(request, response);
    }
}
