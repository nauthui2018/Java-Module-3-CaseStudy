package dao;

import model.Customer;

import java.util.List;

public interface ICustomerDAO extends BaseDAO<Customer> {
    public List<Customer> selectByName(String name);
    public List<Customer> selectByRank(int rankID);
    public List<Customer> selectByProvince(int provinceID);
    public boolean updateCustomerByRank(int currentID, int newID);
    public boolean deleteCustomerByRank(int rankID);
    public boolean updateCustomerByProvince(int currentID, int newID);
    public boolean deleteCustomerByProvince(int provinceID);
}
