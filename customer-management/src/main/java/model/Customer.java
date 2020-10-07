package model;

public class Customer {
    private int customerID;
    private String lastName;
    private String firstName;
    private boolean gender;
    private String dob;
    private String mobile;
    private String address;
    private String email;
    private int provinceID;
    private int totalOrders = 0;
    private double totalAmounts = 0;
    private int rankID;

    public Customer() {}

    public Customer(int customerID, String lastName, String firstName, boolean gender, String dob, String mobile, String address, String email, int provinceID, int totalOrders, double totalAmounts, int rankID) {
        this.customerID = customerID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.dob = dob;
        this.mobile = mobile;
        this.address = address;
        this.email = email;
        this.provinceID = provinceID;
        this.totalOrders = totalOrders;
        this.totalAmounts = totalAmounts;
        this.rankID = rankID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean isGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getProvinceID() {
        return provinceID;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public double getTotalAmounts() {
        return totalAmounts;
    }

    public int getRankID() {
        return rankID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public void setTotalAmounts(double totalAmounts) {
        this.totalAmounts = totalAmounts;
    }

    public void setRankID(int rankID) {
        this.rankID = rankID;
    }
}
