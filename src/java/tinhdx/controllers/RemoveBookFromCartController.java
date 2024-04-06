/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhdx.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "RemoveBookFromCartController", urlPatterns = {"/RemoveBookFromCartController"})
public class RemoveBookFromCartController extends HttpServlet {
    
    private static final String ERROR = "error.html";
    private static final String SUCCESS = "viewCart.jsp";
    
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
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //1. Cust goes to cart place
            HttpSession session = request.getSession(false); 
            //Để đảm bảo rằng vùng nhớ session còn tồn tại ở server
            //Sẽ có trường hợp, giao diện view ở client vẫn còn, nhưng session không còn do time-out
            //=> check false, nếu có session thì remove, không có thì nghỉ
            //Không check true vì nếu không có thì tạo mới làm chi???
            //Muốn remove được thì session phải tồn tại
            if (session != null) {
                //2. Cust takes his/her cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets all items
                    Map <String, ProductDTO> items = cart.getCart();
                    if (items != null) {
                        //4. Cust chooses removing items
                        String[] selectedItem = request.getParameterValues("chkItem");
                        if (selectedItem != null) {
                            //5. remove all selected items from cart
                            for (String productID : selectedItem) {
                                cart.deleteBooks(productID);
                            }
                            //6. update cart to cart place
                            session.setAttribute("CART", cart);
                        }
                    }
                }//end cart has existed
            }//session is existed
            url = SUCCESS;
        } catch (Exception e){
            log("Error at RemoveBookFromCartController: "+ e.toString());
        } finally {
            //7. Refresh viewing cart --> call view cart function again
//            String urlWriting = "DispatchServlet"
//                    + "?btAction=Buy";
//            String urlWriting = "DispatchServlet"
//                    + "?btAction=View Your Cart";
            response.sendRedirect(url);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RemoveBookFromCartController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RemoveBookFromCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RemoveBookFromCartController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RemoveBookFromCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
