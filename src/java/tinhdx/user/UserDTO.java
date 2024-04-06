/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhdx.user;

/**
 *
 * @author TINH
 */
public class UserDTO {
    private String userID;
    private String name;
    private String phone;
    private String address;
    private String roleID;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String userID, String name, String phone, String address, String roleID, String password) {
        this.userID = userID;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.roleID = roleID;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getRoleID() {
        return roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}