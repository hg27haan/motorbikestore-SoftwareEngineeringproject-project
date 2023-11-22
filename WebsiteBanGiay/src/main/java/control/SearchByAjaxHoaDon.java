/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Account;
import entity.HoaDon;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SearchByAjaxHoaDon", urlPatterns = {"/searchAjaxHoaDon"})
public class SearchByAjaxHoaDon extends HttpServlet {

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
        String ngayXuat = request.getParameter("ngayXuat");
        DAO dao = new DAO();
        List<HoaDon> listInvoiceByDate = dao.searchByNgayXuat(ngayXuat);
        List<Account> listAllAccount = dao.getAllAccount();
        PrintWriter out = response.getWriter(); 
        double tongGia;
        for (HoaDon o : listInvoiceByDate) {
        	for (Account a : listAllAccount) {
        		if(o.getMaAccount() == a.getMaAccount()) {	
        			tongGia=Math.round((o.getTongTien()) * 100.0) / 100.0;
        	out.println("<tr>\r\n"
        			+ "                  <th scope=\"row\"></th>\r\n"
        			+ "                  <td>"+o.getMaHoaDon()+"</td>\r\n"
        			+ "                  <td>"+a.getMaAccount()+"</td>\r\n"
        			+ "                  <td>"+String.format("%.0f", tongGia)+" VNĐ</td>\r\n"
        			+ "                  <td>"+o.getNgayThanhToan()+"</td> \r\n"
        			+ "                </tr>");
        		}
        		}
        }
        
        
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
