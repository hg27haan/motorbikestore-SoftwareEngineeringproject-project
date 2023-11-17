/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "EditControl", urlPatterns = {"/edit"})
public class EditControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String pmaXe = request.getParameter("maXe");
        String ptenXe = request.getParameter("tenXe");
        String phinhAnh1 = request.getParameter("hinhAnh1");
        
        String phinhAnh2 = request.getParameter("hinhAnh2");
        String phinhAnh3 = request.getParameter("hinhAnh3");
        String phinhAnh4 = request.getParameter("hinhAnh4");
        
        String pkhoiLuong = request.getParameter("khoiLuong");
        String pdaixRongxCao = request.getParameter("daixRongxCao");
        String pdungTichXiLanh = request.getParameter("dungTichXiLanh");
        String ptiSoNen = request.getParameter("tiSoNen");
        String pdungTichBinhXang = request.getParameter("dungTichBinhXang");
        
        String pgiaTien = request.getParameter("giaTien");
        String ptitle = request.getParameter("title");
        String pgioiThieu = request.getParameter("gioiThieu");
        String pdanhMuc = request.getParameter("danhMuc");
        DAO dao = new DAO();
        dao.editProduct(ptenXe, phinhAnh1, pgiaTien, ptitle, pgioiThieu, pdanhMuc, pkhoiLuong, 
        		pdaixRongxCao, pdungTichXiLanh, ptiSoNen, pdungTichBinhXang, phinhAnh2, phinhAnh3, phinhAnh4, pmaXe);
       request.setAttribute("mess", "Edited!");
       request.getRequestDispatcher("manager").forward(request, response);
//        response.sendRedirect("manager");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
