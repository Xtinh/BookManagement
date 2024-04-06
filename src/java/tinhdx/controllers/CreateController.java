/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhdx.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.chrono.Era;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tinhdx.user.UserDAO;
import tinhdx.user.UserDTO;
import tinhdx.user.UserError;

/**
 *
 * @author TINH
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {
    
    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "login.html";
    
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean check = true;
        UserError userError = new UserError();
        try {
            String userID = request.getParameter("userID");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            UserDAO dao = new UserDAO();
            if(userID.length() < 2 || userID.length() >10) {
                userError.setUserIDError("UserID in range [2,10]");
                check =false;
            }
            if (name.length() < 5 || name.length() >20) {
                userError.setFullnameError("FullName in range [5,20]");
                check = false;
            }
            if (phone.length() == 9) {
                userError.setPhoneError("Phone is 10 numbers");
                check = false;
            }if (address.length() < 0 || address.length() > 50) {
                userError.setAddressError("Address in range [0,50]");
                check = false;
            }
            if (roleID.length() > 5 || roleID.length() < 2) {
                userError.setRoleIDError("RoleID in range [2,5]");
                check = false;
            }
            if (!password.equals(confirm)) {
                userError.setError("Passwords are not the same!");
                check = false;
            }
            if (check) {
//                boolean checkDuplicate = dao.checkDuplicate(userID);
//                if(checkDuplicate) {
//                    userError.setUserIDError("Duplicate UserID: " + userID + "!");
//                    request.setAttribute("USER_ERROR", userError);
//                }else {
//                    boolean checkInsert = dao.insert(user);
//                    if(checkInsert) {
//                        url = SUCCESS;
//                    }else {
//                        userError.setError("Unknow Error! Cannot insert!");
//                        request.setAttribute("USER_ERROR", userError);
//                    }
                boolean checkInsert = dao.insertV2(new UserDTO(userID, name, phone, address, roleID, password));
                if(checkInsert){
                    url=SUCCESS;
                }else {
                    userError.setError("Unknow Error!");
                    request.setAttribute("USER_ERROR", userError);
                }
            }else {
                request.setAttribute("USER_ERROR", userError);
            }
        }catch (Exception e) {
            log("Error at CreateController: " + e.toString());
            if(e.toString().contains("duplicate")) {
                userError.setUserIDError("Duplicate UserID ");
                request.setAttribute("USER_ERROR", userError);
            }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
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
