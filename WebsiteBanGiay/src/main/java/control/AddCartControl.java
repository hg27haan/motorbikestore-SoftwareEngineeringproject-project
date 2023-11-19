/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Account;
import entity.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "AddCartControl", urlPatterns = {"/addCart"})
public class AddCartControl extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); 
        int productID = Integer.parseInt(request.getParameter("pid"));
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if(a == null) {
        	response.sendRedirect("login");
        	return;
        }
        int accountID = a.getMaAccount();
        int amount = Integer.parseInt(request.getParameter("quantity"));
        String size = request.getParameter("size");
        
        try {
	        DAO dao = new DAO();
	        Cart cartExisted = dao.checkCartExist(accountID,productID);
	        int amountExisted;
	        String sizeExisted;
	        if(cartExisted != null) {
	        	 amountExisted = cartExisted.getAmount();
	        	 dao.editAmountAndSizeCart(accountID,productID, (amountExisted+amount), size);
	        	 request.setAttribute("mess", "Da tang so luong san pham!");
	        	 request.getRequestDispatcher("managerCart").forward(request, response);
	        }
	        else {
	        	  dao.insertCart(accountID, productID, amount, size);
	        	  request.setAttribute("mess", "Da them san pham vao gio hang!");
	        	  request.getRequestDispatcher("managerCart").forward(request, response);
	        }
        }catch(Exception ex) {
	        	request.getRequestDispatcher("Error_Account.jsp").forward(request, response);
	    }
	    System.out.print("Text");
      
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
