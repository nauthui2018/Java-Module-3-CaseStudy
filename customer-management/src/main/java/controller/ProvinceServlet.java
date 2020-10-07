package controller;

import dao.ProvinceDAO;
import model.Province;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProvinceServlet", urlPatterns = "/provinces")
public class ProvinceServlet extends HttpServlet {
    private ProvinceDAO provinceDAO;
    public void init() {
        provinceDAO = new ProvinceDAO();
    }

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
                case "delete":
                    deleteProvince(request, response);
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
                    listProvince(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProvince(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        RequestDispatcher dispatcher = request.getRequestDispatcher("province/listRank.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("province/add.jsp");
        dispatcher.forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int provinceID = Integer.parseInt(request.getParameter("provinceID"));
        Province province = provinceDAO.selectById(provinceID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("province/update.jsp");
        request.setAttribute("province", province);
        dispatcher.forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int provinceID = Integer.parseInt(request.getParameter("provinceID"));
        Province province = provinceDAO.selectById(provinceID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("province/delete.jsp");
        request.setAttribute("province", province);
        dispatcher.forward(request, response);
    }

    private void addNewProvince(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int provinceID = Integer.parseInt(request.getParameter("provinceID"));
        String provinceName = request.getParameter("provinceName");
        String provinceCode = request.getParameter("provinceCode");
        Province province = new Province(provinceID, provinceName, provinceCode);
        provinceDAO.add(province);
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        RequestDispatcher dispatcher = request.getRequestDispatcher("province/listRank.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProvince(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int provinceID = Integer.parseInt(request.getParameter("provinceID"));
        String provinceName = request.getParameter("provinceName");
        String provinceCode = request.getParameter("provinceCode");
        Province province = new Province(provinceID, provinceName, provinceCode);
        provinceDAO.update(province);
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        RequestDispatcher dispatcher = request.getRequestDispatcher("province/listRank.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteProvince(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int provinceID = Integer.parseInt(request.getParameter("provinceID"));
        Province province = provinceDAO.selectById(provinceID);
        provinceDAO.delete(province);
        List<Province> listProvince = provinceDAO.findAll();
        request.setAttribute("listProvince", listProvince);
        RequestDispatcher dispatcher = request.getRequestDispatcher("province/listRank.jsp");
        dispatcher.forward(request, response);
    }
}
