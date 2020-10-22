package model;

import java.io.Serializable;

public class User implements Serializable {
    String userUsername;
    String userPassword;
    int customerID;
    boolean userAdmin;

    public User() {
    }

    public User(String userUsername, String userPassword, int customerID, boolean userAdmin) {
        this.userUsername = userUsername;
        this.userPassword = userPassword;
        this.customerID = customerID;
        this.userAdmin = userAdmin;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public int getCustomerID() {
        return customerID;
    }

    public boolean isUserAdmin() {
        return userAdmin;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setUserAdmin(boolean userAdmin) {
        this.userAdmin = userAdmin;
    }
}
