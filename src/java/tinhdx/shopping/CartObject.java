/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhdx.shopping;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import tinhdx.orderdetail.OrdersDetailDAO;
import tinhdx.orderdetail.OrdersDetailDTO;
import tinhdx.orders.OrdersDAO;
import tinhdx.products.ProductDAO;
import tinhdx.products.ProductDTO;

/**
 *
 * @author TINH
 */
public class CartObject {
    private Map <String, ProductDTO> cart;

    public CartObject() {
    }

    public CartObject(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }
    
    public void addBooks(ProductDTO productDTO){
        if(this.cart == null) {
            this.cart = new HashMap<>();    
        }
        if(this.cart.containsKey(productDTO.getProductID())){
            int currentQuantity = this.cart.get(productDTO.getProductID()).getQuantity();
            productDTO.setQuantity(currentQuantity + productDTO.getQuantity());
        }
        cart.put(productDTO.getProductID(), productDTO);
    }
    
    public void deleteBooks(String id) {
        if(this.cart == null) {
            return;
        }
        if(this.cart.containsKey(id)){
            this.cart.remove(id);
        }
    }
    
    public void updateBooks(String id, ProductDTO newBooks){
        if(this.cart == null) {
            return;
        }
        if(this.cart.containsKey(id)){
            this.cart.replace(id, newBooks);
        }
    }
    
    public Map <String, ProductDTO> showCheckedItems(String[] ID) 
        throws SQLException, NamingException{
        if (this.cart == null) {
            return null;
        }
        
        Map <String, ProductDTO> list = new HashMap<>();
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = new ProductDTO();
        for (String id : ID) {
            dto = dao.getProductByProductID(id);
            list.put(id, cart.get(id));
        }
        return list;
    }
    //Tịnh
    public int checkOutItemsOfCart(String name, String address, String total, 
            Map <String, ProductDTO> checkedItems) 
        throws SQLException, NamingException{
        if (checkedItems == null) {
            return -1;
        }
        
        OrdersDAO ordersDAO = new OrdersDAO();
        int orderID = ordersDAO.createNewOrder(name, address, total);
        
        if (orderID > 0) {
            OrdersDetailDAO orderDetailsDAO = new OrdersDetailDAO();
            boolean result = orderDetailsDAO.createOrderDetails(orderID, checkedItems);
            if (result) {
                return orderID;
            }
        }
        return -1;
    }
    
     public List<OrdersDetailDTO> addItemsToOrderDetailsDTO
        (Map <String, ProductDTO> checkedItems, int orderID) {
        List<OrdersDetailDTO> list = new ArrayList<>();
        
        for (String id : checkedItems.keySet()) {
            String productID = checkedItems.get(id).getProductID();
            String name = checkedItems.get(id).getName();
            Double price = checkedItems.get(id).getPrice();
            int quantity = checkedItems.get(id).getQuantity();
            Double total;
            total = price*quantity;
            
            OrdersDetailDTO orderDetailsDTO =  new OrdersDetailDTO(productID, orderID, name, price, quantity, total);
            
            list.add(orderDetailsDTO);
        }
        
        return list;
    }
    
            
    public void removeItemsForCheckOut(String dto) {
        if (this.cart == null) {
            return;
        }
        
        if (this.cart.containsKey(dto)) {
            this.cart.remove(dto);
            if (this.cart.isEmpty()) {
                this.cart = null;
            }
        }
    }
}
