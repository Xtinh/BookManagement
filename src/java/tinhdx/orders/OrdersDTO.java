/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhdx.orders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author TINH
 */
public class OrdersDTO implements Serializable{
    private int orderID;
    private String name;
    private String address;
    private Timestamp date;
    private BigDecimal total;

    public OrdersDTO() {
    }

    public OrdersDTO(int orderID, String name, String address, Timestamp date, BigDecimal total) {
        this.orderID = orderID;
        this.name = name;
        this.address = address;
        this.date = date;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Timestamp getDate() {
        return date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
}
