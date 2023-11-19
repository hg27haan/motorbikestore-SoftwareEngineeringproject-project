/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CategoryShopControl", urlPatterns = {"/categoryShop"})
public class CategoryShopControl extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String cateID = request.getParameter("cid");
        //da lay dc category id ve roi
        DAO dao = new DAO();
        List<Product> list = dao.getProductByCID(cateID);
        //in list p day
        PrintWriter out = response.getWriter();
        for(Product o : list) {
        	out.println("  <!-- Grid column -->\r\n"
        			+ "              <div class=\"col-md-4 mb-5\">\r\n"
        			+ "\r\n"
        			+ "                <!-- Card -->\r\n"
        			+ "                <div class=\"\">\r\n"
        			+ "\r\n"
        			+ "                  <div class=\"view zoom overlay rounded z-depth-2\">\r\n"
        			+ "                    <img class=\"img-fluid w-100\"\r\n"
        			+ "                      src=\""+o.getImage()+"\" alt=\"Sample\">\r\n"
        			+ "                    <a href=\"detail?pid="+o.getId()+"\">\r\n"
        			+ "                      <div class=\"mask\">\r\n"
        			+ "                        <img class=\"img-fluid w-100\"\r\n"
        			+ "                          src=\""+o.getImage()+"\">\r\n"
        			+ "                        <div class=\"mask rgba-black-slight\"></div>\r\n"
        			+ "                      </div>\r\n"
        			+ "                    </a>\r\n"
        			+ "                  </div>\r\n"
        			+ "\r\n"
        			+ "                  <div class=\"text-center pt-4\">\r\n"
        			+ "\r\n"
        			+ "                    <h5>"+o.getName()+"</h5>\r\n"
        			+ "                    <p><span class=\"mr-1\"><strong>"+o.getPrice()+"$</strong></span></p>\r\n"
        			+ "\r\n"
        			+ "                  </div>\r\n"
        			+ "\r\n"
        			+ "                </div>\r\n"
        			+ "                <!-- Card -->\r\n"
        			+ "\r\n"
        			+ "              </div>\r\n"
        			+ "              <!-- Grid column -->");
        }
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
