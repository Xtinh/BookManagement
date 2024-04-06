/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhdx.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tinhdx.products.ProductDTO;
import tinhdx.shopping.CartObject;

/**
 *
 * @author TINH
 */
@WebServlet(name = "AddBookToCartController", urlPatterns = {"/AddBookToCartController"})
public class AddBookToCartController extends HttpServlet {
    
    public static final String ERROR = "error.html";
    public static final String SUCCESS = "ShowBookController";
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
        String url = ERROR;
        try {
            String stringbook = request.getParameter("cmbBook");
            String tmp[] = stringbook.split("-");
            String id = tmp[0];
            String name = tmp[1];
            double price = Double.parseDouble(tmp[2]);
            //1. Cust goes to cart place
            HttpSession session = request.getSession();
            //2. Cust takes their --> attributes
            CartObject cart = (CartObject)session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            }//cart is not existed --> create cart
            //3. Cust takes book item --> parameter
//            String productID = request.getParameter("pk");
            //4. Cust drops item down
            ProductDTO productDTO = new ProductDTO(id, name, price, 1);
            cart.addBooks(productDTO);
            //5. Update to cart place
            session.setAttribute("CART", cart);
            //6. Cust goes to shopping
//            url = "DispatchServlet"
//                    + "?btAction=Buy";
            url = SUCCESS;
            String message = "You have chosen " + " " + id + " successfully!";
            request.setAttribute("MESSAGE", message);
        } catch (Exception e) {
            log("ERROR at AddBookToCartController " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
