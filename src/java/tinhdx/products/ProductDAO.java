/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhdx.products;

import java.io.Serializable;
import java.math.BigDecimal;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tinhdx.untils.DBUtils;

/**
 *
 * @author TINH
 */
public class ProductDAO implements Serializable {
    
    private List<ProductDTO> productList;
    private static String SELECT = "SELECT productID, name, price, quantity FROM tblProducts ";
    private static String SELECTID = "SELECT name, price, quantity FROM tblProducts WHERE productID = ?";
    private static String UPDATEQT = "UPDATE tblProducts SET productID = ?, name = ?,  price = ?, quantity = ? WHERE productID = ?";
    private static String SEARCH = "SELECT productID, name, price, quantity FROM tblProducts WHERE name like ?";
    
    private static String UPDATEQUANTITY = "UPDATE tblProducts SET quantity = ? WHERE productID = ?";
    
    
    
    public List<ProductDTO> searchProduct(String name) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + name + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    ProductDTO dto = new ProductDTO();
                    dto.setProductID(rs.getString("productID"));
                    dto.setName(rs.getString("name"));
                    dto.setPrice(rs.getDouble("price"));
                    dto.setQuantity(rs.getInt("quantity"));
                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    public void showBookList() 
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareCall(SELECT);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");                  
                    ProductDTO dto = new ProductDTO(productID, name, price, quantity);                   
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public ProductDTO getProductByProductID(String productID) 
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(SELECTID);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    ProductDTO result = new ProductDTO(productID, name, price, quantity);
                    return result;
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
    
    // Update a product
    public void updateProduct(ProductDTO product) throws NamingException, SQLException {
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATEQT)) {
            preparedStatement.setString(1, product.getProductID());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }
    
    //Update quantity of product
    public void updateQuantityOfProduct(ProductDTO product,int quantity) throws NamingException, SQLException {
        Connection connection = DBUtils.getConnection();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATEQUANTITY)) {
            preparedStatement.setInt(1, product.getQuantity() - quantity);
            preparedStatement.setString(2, product.getProductID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }
}
