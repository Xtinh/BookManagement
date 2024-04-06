/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhdx.orders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;
import tinhdx.untils.DBUtils;

/**
 *
 * @author TINH
 */
public class OrdersDAO implements Serializable{
    
    private static final String INSERT = "INSERT INTO tblOrders(name, address, total)" + "OUTPUT inserted.orderID, inserted.date" + " VALUES (?, ?, ?)";
    private static final String ORDER = "SELECT name, address, date, total FROM tblOrders WHERE orderID = ?";
    
    public int createNewOrder(String name, String address, String total) 
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(INSERT);
                stm.setString(1, name);
                stm.setString(2, address);
                stm.setBigDecimal(3, new BigDecimal(total));
                rs = stm.executeQuery();
                if (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    return orderID;
                }
            } //end if con connect success
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }
    
    public OrdersDTO getOrders(int orderID) 
        throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(ORDER);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getNString("name");
                    String address = rs.getNString("address");
                    Timestamp date = rs.getTimestamp("date");
                    BigDecimal total = rs.getBigDecimal("total");
                    OrdersDTO dto = new OrdersDTO(orderID, name, address, date, total);
                    return dto;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
}
