package dao;

import model.Customer;

import java.util.List;

public interface ICustomerDAO extends BaseDAO<Customer> {
    public List<Customer> selectByName(String name);
    public List<Customer> selectByRank(int rankID);
}
