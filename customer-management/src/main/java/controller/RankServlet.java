package controller;

import dao.CustomerDAO;
import dao.RankDAO;
import model.Customer;
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
                case "delete":
                    deleteRank(request, response);
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
        List<Customer> customers = customerDAO.selectByRank(rankID);
        request.setAttribute("listCustomer", customers);
        List<Rank> ranks = rankDAO.findAll();
        request.setAttribute("listRank", ranks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rank/delete.jsp");
        dispatcher.forward(request, response);
    }

    private void addNewRank(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int rankID = Integer.parseInt(request.getParameter("rankID"));
        String rankName = request.getParameter("rankName");
        Rank rank = new Rank(rankID, rankName);
        rankDAO.add(rank);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rank/listRank.jsp");
        dispatcher.forward(request, response);
    }

    private void updateRank(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int rankID = Integer.parseInt(request.getParameter("rankID"));
        String rankName = request.getParameter("rankName");
        Rank rank = new Rank(rankID, rankName);
        rankDAO.update(rank);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rank/listRank.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteRank(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int rankID = Integer.parseInt(request.getParameter("rankID"));
        Rank rank = rankDAO.selectById(rankID);
        rankDAO.delete(rank);
        List<Rank> listRank = rankDAO.findAll();
        request.setAttribute("listRank", listRank);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rank/listRank.jsp");
        dispatcher.forward(request, response);
    }
}
