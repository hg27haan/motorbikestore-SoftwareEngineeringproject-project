/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * author: H.M.Duc
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


@WebServlet(name = "AddAccountControl", urlPatterns = {"/addAccount"})
public class AddAccountControl extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String isAdmin = request.getParameter("isAdmin");
        String email = request.getParameter("email");
        System.out.print(pass);
        System.out.print(user);
        System.out.print(isAdmin);
        System.out.print(email);
        try {
        	DAO dao = new DAO();
            dao.insertAccount(user, pass, isAdmin, email);
            request.setAttribute("mess", "Account Added!");
            //request.getRequestDispatcher("managerAccount").forward(request, response);
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
