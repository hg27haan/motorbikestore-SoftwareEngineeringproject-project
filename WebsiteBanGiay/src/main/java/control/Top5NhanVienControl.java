/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
  * author: H.M.Duc
 */
package control;

import dao.DAO;
import entity.Account;
import entity.DanhMuc;
import entity.XeMay;
import entity.TongChiTieuMuaHang;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "Top5NhanVienControl", urlPatterns = {"/top5nhanvien"})
public class Top5NhanVienControl extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      
        DAO dao = new DAO();

        List<Account> listAllAccount = dao.getAllAccount();
        List<TongChiTieuMuaHang> listTop5NhanVien = dao.getTop5NhanVien();


        request.setAttribute("listAllAccount", listAllAccount);
        request.setAttribute("listTop5NhanVien", listTop5NhanVien);

        request.getRequestDispatcher("Top5NhanVien.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
