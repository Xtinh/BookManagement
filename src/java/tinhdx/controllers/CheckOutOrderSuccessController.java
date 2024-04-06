/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhdx.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tinhdx.orderdetail.OrdersDetailDTO;
import tinhdx.orders.OrdersDAO;
import tinhdx.orders.OrdersDTO;
import tinhdx.products.ProductDAO;
import tinhdx.products.ProductDTO;
import tinhdx.shopping.CartObject;

/**
 *
 * @author TINH
 */
@WebServlet(name = "CheckOutOrderSuccessController", urlPatterns = {"/CheckOutOrderSuccessController"})
public class CheckOutOrderSuccessController extends HttpServlet {
    
    private static final String ERROR = "confirmCheckOut.jsp";
    private static final String SUCCESS = "checkOutSuccess.jsp";

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
        String name = request.getParameter("txtName");
        String address = request.getParameter("txtAdress");
        String total = request.getParameter("txtTotal");
        
        String url = ERROR;
        
        try {
            //1. Cust goes to cart place
            HttpSession session = request.getSession(false);
            //2. Cust takes his/her cart
            if (session != null) {
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets all items
                    Map <String, ProductDTO> items = (Map <String, ProductDTO>)session.getAttribute("CHECK_OUT_ITEMS");
                    boolean isValid = true;
                    if (items != null) {
                        for(ProductDTO dto: items.values()) {
                            ProductDTO getProduct;
                            ProductDAO productDAO = new ProductDAO();
                            getProduct = productDAO.getProductByProductID(dto.getProductID());
                            if(getProduct.getQuantity() < dto.getQuantity()) {
                                request.setAttribute("ERROR", "So luong hang trong kho khong du!");
                                isValid = false;
                                break;
                            }
                        }
                        if(isValid) {
                            for(ProductDTO dto1: items.values()) {
                            ProductDAO productDAO = new ProductDAO();
                            ProductDTO getProduct;
                            getProduct = productDAO.getProductByProductID(dto1.getProductID());
                            
                        int orderID = cart.checkOutItemsOfCart(name, address, total, items);
                        if (orderID > 0) {
                            OrdersDAO ordersDAO = new OrdersDAO();
                            OrdersDTO ordersDTO = ordersDAO.getOrders(orderID);
                            
                            List<OrdersDetailDTO> orderDetailsDTO = 
                                cart.addItemsToOrderDetailsDTO(items, orderID);
                            for (String dto : items.keySet()) {
                                cart.removeItemsForCheckOut(dto);
                            }
                            session.setAttribute("ORDER", ordersDTO);
                            session.setAttribute("LIST_ORDER_DETAILS", orderDetailsDTO);
                            session.setAttribute("CART", cart);
                            session.removeAttribute("CHECK_OUT_ITEMS");
                            url = SUCCESS;
                            }
                            productDAO.updateQuantityOfProduct(getProduct, dto1.getQuantity());
                            }
                        }
                        
                        }
                    }
                }
        } catch (Exception e) {
            log("Error at CheckOutOrderSuccessController: " + e.toString());
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
