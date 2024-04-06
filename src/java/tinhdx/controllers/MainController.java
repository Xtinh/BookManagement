/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** Đây là về Controller
 * @param  Controller nhận yêu cầu từ người dùng sử dụng model và view để xử lí và trả kết quả cho người dùng
*/
/** Đây là về Model
 * @param Model là lớp chứa đựng thông tin chứa đựng dữ liệu, tương tác database, chịu trách nhiệm chính trong mô hình hóa dữ liệu (DTO, DAO)
 */
/** Đây là View
 * @param View là giao diện của hệ thống tương tác trực tiếp với người dùng, là nơi để hiện thị bên phía server trả về (HTML, JSP, xml)
 */

/** Cách chạy mô hình
 * @param Quy_trình
 *  Đàu tiên 1 user gửi 1 request đến Controller để thực hiện 1 công việc, sau đó Controller tương tác với Models sau đó Models sẽ xử lí sau đó trả về kết quả cập nhật trên
 *  giao diện Views sẽ cho người dùng thấy kết quả
 */
package tinhdx.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TINH
 */
public class MainController extends HttpServlet {
    
    private static final String WELCOME_PAGE = "login.html";
    
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String SEARCH = "Search";
    private static final String SEARCH_CONTROLLER = "SearchController";
    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteController";
    private static final String UPDATE = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String CREATE_PAGE = "Create_Page";
    private static final String CREATE_PAGE_VIEW = "create.html";
    private static final String CREATE = "Create";
    private static final String CREATE_CONTROLLER = "CreateController";
    //SHOPPING
    private static final String SHOPPING = "Shopping_Page";
    private static final String SHOPPING_PAGE_VIEW = "ShowBookController";
    //SearchBook
     private static final String SEARCH_BOOK = "SearchBook";
    private static final String SEARCH_BOOK_CONTROLLER = "SearchBookController";
    //SHOPPINGCART
    private static final String ADD = "Add";
    private static final String ADD_TO_CART_CONTROLLER = "AddBookToCartController";
    
    private static final String VIEW = "View Your Cart";
    private static final String VIEW_TO_CART_CONTROLLER = "viewCart.jsp";
    private static final String REFRESH = "Refresh";
    private static final String REFESH_CONTROLLER = "RefreshController";
    
    private static final String REMOVE = "Remove";
    private static final String REMOVE_CONTROLLER = "RemoveController";
    
    private static final String REMOVEITEMS = "Remove Selected Books";
    private static final String REMOVEITEMS_CONTROLLER = "RemoveBookFromCartController";
    private static final String CHECKITEMS = "Check Out Selected Books";
    private static final String CHECKITEMS_CONTROLLER = "CheckOutOrderController";
    //CheckOUT success
    private static final String CHECKOUT = "Check Out";
    private static final String CHECKOUT_CONTROLLER = "CheckOutOrderSuccessController";
    //Total
    private static final String TOTAL = "Total";
    private static final String TOTAL_CONTROLLER = "TotalController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELCOME_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null){
                url = WELCOME_PAGE;
            }else if(LOGIN.equals(action)){
                url = LOGIN_CONTROLLER;
            }else if(SEARCH.equals(action)){
                url = SEARCH_CONTROLLER;
            }else if(DELETE.equals(action)){
                url = DELETE_CONTROLLER;
            }else if(UPDATE.equals(action)){
                url = UPDATE_CONTROLLER;
            }else if(LOGOUT.equals(action)){
                url = LOGOUT_CONTROLLER;
            }else if(ADD.equals(action)){
                url = ADD_TO_CART_CONTROLLER;
            }else if(VIEW.equals(action)){
                url = VIEW_TO_CART_CONTROLLER;
            }else if(REFRESH.equals(action)){
                url = REFESH_CONTROLLER;
            }else if(REMOVE.equals(action)){
                url = REMOVE_CONTROLLER;
            }else if(CREATE.equals(action)){
                url = CREATE_CONTROLLER;
            }else if(CREATE_PAGE.equals(action)){
                url = CREATE_PAGE_VIEW;
            }else if(SHOPPING.equals(action)){
                url = SHOPPING_PAGE_VIEW;
            }else if(REMOVEITEMS.equals(action)){
                url = REMOVEITEMS_CONTROLLER;
            }else if(CHECKITEMS.equals(action)){
                url = CHECKITEMS_CONTROLLER;
            }else if(CHECKOUT.equals(action)){
                url = CHECKOUT_CONTROLLER;
            }else if(SEARCH_BOOK.equals(action)){
                url = SEARCH_BOOK_CONTROLLER;
            }else if(TOTAL.equals(action)){
                url = TOTAL_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at mainController" + e.toString());
        }finally {
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
