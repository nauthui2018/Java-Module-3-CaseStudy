package model;

import service.ValidateHelper;

import java.util.HashMap;
import java.util.List;

public class Customer {
    private int customerID;
    private String lastName;
    private String firstName;
    private int gender;
    private String dob;
    private String mobile;
    private String address;
    private String email;
    private int provinceID;
    private int totalOrders = 0;
    private double totalAmounts = 0;
    private int rankID;
    private ValidateHelper validateHelper = new ValidateHelper();

    public Customer() {}

    public HashMap<String, String> validationCustomer(String lastName, String firstName, String gender, String dob, String mobile, String address, String email, String provinceID, String totalOrders, String totalAmounts, String rankID) {
        HashMap<String, String> validationResult = new HashMap<>();
        if (validateHelper.validateFirstName(firstName)) {
            setFirstName(firstName);
        } else {
            validationResult.put("firstName", "'" + firstName + "' - Invalid first name");
        }
        if (validateHelper.validateLastName(lastName)) {
            setLastName(lastName);
        } else {
            validationResult.put("lastName", "'" + lastName + "' - Invalid last name");
        }
        if (validateHelper.validateGender(gender)) {
            setGender(Integer.parseInt(gender));
        } else {
            setGender(Integer.parseInt(gender));
            validationResult.put("gender", "Please select gender");
        }
        if (validateHelper.validateDate(dob)) {
            setDob(dob);
        } else {
            validationResult.put("dob", "Please set your DOB");
        }
        if (validateHelper.validateMobile(mobile)) {
            setMobile(mobile);
        } else {
            validationResult.put("mobile", "'" + mobile + "' - Invalid mobile number");
        }
        if (validateHelper.validateAddress(address)) {
            setAddress(address);
        } else {
            validationResult.put("address", "'" + address + "' - Invalid address");
        }
        if (validateHelper.validateEmail(email)) {
            setEmail(email);
        } else {
            validationResult.put("email", "'" + email + "' - Invalid email address");
        }
        if (validateHelper.validateIntegerNumber(provinceID)) {
            setProvinceID(Integer.parseInt(provinceID));
        } else {
            validationResult.put("provinceID", "Please select your province");
        }
        if (validateHelper.validateIntegerNumber(totalOrders)) {
            setTotalOrders(Integer.parseInt(totalOrders));
        } else {
            validationResult.put("totalOrders", "'" + totalOrders + "' - Invalid integer number");
        }
        if (validateHelper.validateDoubleNumber(totalAmounts)) {
            setTotalAmounts(Double.parseDouble(totalAmounts));
        } else {
            validationResult.put("totalAmounts", "'" + totalAmounts + "' - Invalid double number");
        }
        if (validateHelper.validateIntegerNumber(rankID)) {
            setRankID(Integer.parseInt(rankID));
        } else {
            validationResult.put("rankID", "Please select rank");
        }
        return validationResult;
    }

    public Customer(int customerID, String lastName, String firstName, int gender, String dob, String mobile, String address, String email, int provinceID, int totalOrders, double totalAmounts, int rankID) {
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

    public Customer(String lastName, String firstName, int gender, String dob, String mobile, String address, String email, int provinceID, int totalOrders, double totalAmounts, int rankID) {
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

    public int getGender() {
        return gender;
    }

    public String viewGender(int gender) {
        switch (gender) {
            case 0:
                return "Female";
            case 1:
                return "Male";
            case 2:
                return "LGBT";
            default:
                return "-- Please select --";
        }
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

    public void setGender(int gender) {
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
