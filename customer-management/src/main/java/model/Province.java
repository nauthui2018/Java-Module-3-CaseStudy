package model;

import java.util.List;

public class Province {
    private int id;
    private String name;
    private String code;
    private List<Customer> customers;

    public Province() {
    }

    public Province(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
