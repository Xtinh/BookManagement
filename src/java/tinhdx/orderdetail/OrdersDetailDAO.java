/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhdx.orderdetail;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import tinhdx.products.ProductDTO;
import tinhdx.untils.DBUtils;

/**
 *
 * @author TINH
 */
public class OrdersDetailDAO {
    
    private List<OrdersDetailDTO> orderDetailsList;
    private static String INSERT = "INSERT INTO orderDetails (productID, orderID, name, price, quantity, total) VALUES (?, ?, ?, ?, ?, ?)";

    public List<OrdersDetailDTO> getOrderDetailsList() {
        return orderDetailsList;
    }
    
    public boolean createOrderDetails(int orderID, Map <String, ProductDTO> checkedItems)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                con.setAutoCommit(false); //*****
                stm = con.prepareStatement(INSERT);
                Double price;
                Double total;
                int quantity;
//                int i = 0; //*****
                int affectedRows =  0;
                for (String id :  checkedItems.keySet()) {
                    quantity = checkedItems.get(id).getQuantity();
                    price = checkedItems.get(id).getPrice();
                    total = price*quantity;
                    stm.setString(1, checkedItems.get(id).getProductID());
                    stm.setInt(2, orderID);
                    stm.setString(3, checkedItems.get(id).getName());
                    stm.setDouble(4, price);
                    stm.setInt(5, quantity);
                    stm.setDouble(6, total);
                    affectedRows += stm.executeUpdate(); //*****
                }
                
                con.commit(); //*****
                
                if (affectedRows == checkedItems.size()) { //*****
                    return true; 
                }
            }
        } catch (SQLException ex) {
            if (con != null) {
                con.rollback();
            }
        }
        finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}

