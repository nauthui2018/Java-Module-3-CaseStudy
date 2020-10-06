package model;

import java.io.Serializable;

public class User implements Serializable {
    String username;
    String password;
    int customerID;
    boolean admin;

    public User() {
    }

    public User(String username, String password, int customerID, boolean admin) {
        this.username = username;
        this.password = password;
        this.customerID = customerID;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getCustomerID() {
        return customerID;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
