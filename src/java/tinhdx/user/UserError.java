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
public class UserError {

    private String userIDError;
    private String fullnameError;
    private String roleIDError;
    private String phoneError;
    private String addressError;
    private String passwordError;
    private String confirmError;
    private String error;

    public UserError() {
        this.userIDError = "";
        this.fullnameError = "";
        this.roleIDError = "";
        this.phoneError = "";
        this.addressError = "";
        this.passwordError = "";
        this.confirmError = "";
        this.error = "";
    }

    public UserError(String userIDError, String fullnameError, String roleIDError, String phoneError, String addressError, String passwordError, String confirmError, String error) {
        this.userIDError = userIDError;
        this.fullnameError = fullnameError;
        this.roleIDError = roleIDError;
        this.phoneError = phoneError;
        this.addressError = addressError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.error = error;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public String getAddressError() {
        return addressError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public String getFullnameError() {
        return fullnameError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public void setFullnameError(String fullnameError) {
        this.fullnameError = fullnameError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

    public void setPasswordError(String password) {
        this.passwordError = passwordError;
    }

    public void setConfirmError(String confirm) {
        this.confirmError = confirmError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
