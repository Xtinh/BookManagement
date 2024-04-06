/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhdx.orderdetail;

import java.math.BigDecimal;

/**
 *
 * @author TINH
 */
public class OrdersDetailDTO {

    private String productID;
    private int oderID;
    private String name;
    private double price;
    private int quantity;
    private double total;

    public OrdersDetailDTO() {
    }

    public OrdersDetailDTO(String productID, int oderID, String name, double price, int quantity, double total) {
        this.productID = productID;
        this.oderID = oderID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductID() {
        return productID;
    }

    public int getOderID() {
        return oderID;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setOderID(int oderID) {
        this.oderID = oderID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
