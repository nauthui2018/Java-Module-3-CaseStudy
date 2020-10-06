package dao;

import model.Customer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {
    HelperDAO helper = new HelperDAO();

    public CustomerDAO() {
    }

    @Override
    public List<Customer> findAll() {
        String query = "{CALL get_all_customers()}";
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String firstName = rs.getString("firstName");
                boolean gender = rs.getBoolean("gender");
                String dob = rs.getString("dob");
                String mobile = rs.getString("mobile");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int provinceID = rs.getInt("provinceID");
                int order = rs.getInt("order");
                double amount = rs.getDouble("amount");
                int rankID = rs.getInt("rankID");
                customers.add(new Customer(id, name, firstName, gender, dob, mobile, address, email, provinceID, order, amount, rankID));
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return customers;
    }

    @Override
    public void add(Customer customer) {
        String query = "{CALL add_new_customer(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, customer.getId());
            callableStatement.setString(2, customer.getName());
            callableStatement.setString(3, customer.getFirstName());
            callableStatement.setBoolean(4, customer.isGender());
            callableStatement.setString(5, customer.getDob());
            callableStatement.setString(6, customer.getMobile());
            callableStatement.setString(7, customer.getAddress());
            callableStatement.setString(8, customer.getEmail());
            callableStatement.setInt(9, customer.getProvinceID());
            callableStatement.setInt(10, customer.getOrder());
            callableStatement.setDouble(11, customer.getAmount());
            callableStatement.setInt(12, customer.getRankID());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
    }

    @Override
    public boolean delete(Customer customer) {
        String query = "{CALL delete_customer(?)}";
        boolean rowDeleted = false;
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, customer.getId());
            callableStatement.executeUpdate();
            rowDeleted = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Customer customer) {
        boolean rowUpdated = false;
        String query = "{CALL update_customer(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, customer.getId());
            callableStatement.setString(2, customer.getName());
            callableStatement.setString(3, customer.getFirstName());
            callableStatement.setBoolean(4, customer.isGender());
            callableStatement.setString(5, customer.getDob());
            callableStatement.setString(6, customer.getMobile());
            callableStatement.setString(7, customer.getAddress());
            callableStatement.setString(8, customer.getEmail());
            callableStatement.setInt(9, customer.getProvinceID());
            callableStatement.setInt(10, customer.getOrder());
            callableStatement.setDouble(11, customer.getAmount());
            callableStatement.setInt(12, customer.getRankID());
            callableStatement.executeUpdate();
            rowUpdated = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public Customer selectById(int id) {
        Customer customer = null;
        String query = "{CALL get_customer_by_id(?)}";
        try (Connection connection = helper.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                String name = rs.getString("name");
                String firstName = rs.getString("firstName");
                boolean gender = rs.getBoolean("gender");
                String dob = rs.getString("dob");
                String mobile = rs.getString("mobile");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int provinceID = rs.getInt("provinceID");
                int order = rs.getInt("order");
                double amount = rs.getDouble("amount");
                int rankID = rs.getInt("rankID");
                customer = new Customer(id, name, firstName, gender, dob, mobile, address, email, provinceID, order, amount, rankID);
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> selectByName(String search) {
        List<Customer> customers = new ArrayList<>();
        String query = "{CALL get_user_by_name(?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, search);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String firstName = rs.getString("firstName");
                boolean gender = rs.getBoolean("gender");
                String dob = rs.getString("dob");
                String mobile = rs.getString("mobile");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int provinceID = rs.getInt("provinceID");
                int order = rs.getInt("order");
                double amount = rs.getDouble("amount");
                int rankID = rs.getInt("rankID");
                customers.add(new Customer(id, name, firstName, gender, dob, mobile, address, email, provinceID, order, amount, rankID));
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return customers;
    }

    @Override
    public List<Customer> selectByRank(int rankID) {
        List<Customer> customers = new ArrayList<>();
        String query = "{CALL get_user_by_rank(?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, rankID);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String firstName = rs.getString("firstName");
                boolean gender = rs.getBoolean("gender");
                String dob = rs.getString("dob");
                String mobile = rs.getString("mobile");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int provinceID = rs.getInt("provinceID");
                int order = rs.getInt("order");
                double amount = rs.getDouble("amount");
                rankID = rs.getInt("rankID");
                customers.add(new Customer(id, name, firstName, gender, dob, mobile, address, email, provinceID, order, amount, rankID));
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return customers;
    }
}
