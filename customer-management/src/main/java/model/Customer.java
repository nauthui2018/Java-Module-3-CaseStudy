package model;

public class Customer {
    private int id;
    private String name;
    private String firstName;
    private boolean gender;
    private String dob;
    private String mobile;
    private String address;
    private String email;
    private int provinceID;
    private int order = 0;
    private double amount = 0;
    private int rankID;
    private boolean admin = false;

    public Customer() {}

    public Customer(int id, String name, String firstName, boolean gender, String dob, String mobile, String address, String email, int provinceID, int order, double amount, int rankID, boolean admin) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.gender = gender;
        this.dob = dob;
        this.mobile = mobile;
        this.address = address;
        this.email = email;
        this.provinceID = provinceID;
        this.order = order;
        this.amount = amount;
        this.rankID = rankID;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public int getOrder() {
        return order;
    }

    public double getAmount() {
        return amount;
    }

    public int getRankID() {
        return rankID;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setOrder(int order) {
        this.order = order;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setRankID(int rankID) {
        this.rankID = rankID;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
