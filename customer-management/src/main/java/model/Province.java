package model;

import java.util.List;

public class Province {
    private int provinceID;
    private String provinceName;
    private String provinceCode;
    private List<Customer> customers;

    public Province() {
    }

    public Province(String provinceName, String provinceCode) {
        this.provinceName = provinceName;
        this.provinceCode = provinceCode;
    }

    public Province(int provinceID, String provinceName, String provinceCode) {
        this.provinceID = provinceID;
        this.provinceName = provinceName;
        this.provinceCode = provinceCode;
    }

    public int getProvinceID() {
        return provinceID;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
