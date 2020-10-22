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
                int customerID = rs.getInt("customerID");
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                int gender = rs.getInt("gender");
                String dob = rs.getString("dob");
                String mobile = rs.getString("mobile");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int provinceID = rs.getInt("provinceID");
                int totalOrders = rs.getInt("totalOrders");
                double totalAmounts = rs.getDouble("totalAmounts");
                int rankID = rs.getInt("rankID");
                customers.add(new Customer(customerID, lastName, firstName, gender, dob, mobile, address, email, provinceID, totalOrders, totalAmounts, rankID));
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return customers;
    }

    @Override
    public void add(Customer customer) {
        String query = "{CALL add_new_customer(?,?,?,?,?,?,?,?,?,?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, customer.getLastName());
            callableStatement.setString(2, customer.getFirstName());
            callableStatement.setInt(3, customer.getGender());
            callableStatement.setString(4, customer.getDob());
            callableStatement.setString(5, customer.getMobile());
            callableStatement.setString(6, customer.getAddress());
            callableStatement.setString(7, customer.getEmail());
            callableStatement.setInt(8, customer.getProvinceID());
            callableStatement.setInt(9, customer.getTotalOrders());
            callableStatement.setDouble(10, customer.getTotalAmounts());
            callableStatement.setInt(11, customer.getRankID());
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
            callableStatement.setInt(1, customer.getCustomerID());
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
            callableStatement.setInt(1, customer.getCustomerID());
            callableStatement.setString(2, customer.getLastName());
            callableStatement.setString(3, customer.getFirstName());
            callableStatement.setInt(4, customer.getGender());
            callableStatement.setString(5, customer.getDob());
            callableStatement.setString(6, customer.getMobile());
            callableStatement.setString(7, customer.getAddress());
            callableStatement.setString(8, customer.getEmail());
            callableStatement.setInt(9, customer.getProvinceID());
            callableStatement.setInt(10, customer.getTotalOrders());
            callableStatement.setDouble(11, customer.getTotalAmounts());
            callableStatement.setInt(12, customer.getRankID());
            callableStatement.executeUpdate();
            rowUpdated = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public Customer selectById(int customerID) {
        Customer customer = null;
        String query = "{CALL get_customer_by_id(?)}";
        try (Connection connection = helper.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, customerID);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                customerID = rs.getInt("customerID");
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                int gender = rs.getInt("gender");
                String dob = rs.getString("dob");
                String mobile = rs.getString("mobile");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int provinceID = rs.getInt("provinceID");
                int totalOrders = rs.getInt("totalOrders");
                double totalAmounts = rs.getDouble("totalAmounts");
                int rankID = rs.getInt("rankID");
                customer = new Customer(customerID, lastName, firstName, gender, dob, mobile, address, email, provinceID, totalOrders, totalAmounts, rankID);
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> selectByName(String search) {
        List<Customer> customers = new ArrayList<>();
        String query = "{CALL get_customer_by_name(?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, search);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("customerID");
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                int gender = rs.getInt("gender");
                String dob = rs.getString("dob");
                String mobile = rs.getString("mobile");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int provinceID = rs.getInt("provinceID");
                int totalOrders = rs.getInt("totalOrders");
                double totalAmounts = rs.getDouble("totalAmounts");
                int rankID = rs.getInt("rankID");
                customers.add(new Customer(customerID, lastName, firstName, gender, dob, mobile, address, email, provinceID, totalOrders, totalAmounts, rankID));
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return customers;
    }

    @Override
    public List<Customer> selectByRank(int rankID) {
        List<Customer> customers = new ArrayList<>();
        String query = "{CALL get_customer_by_rank(?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, rankID);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("customerID");
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                int gender = rs.getInt("gender");
                String dob = rs.getString("dob");
                String mobile = rs.getString("mobile");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int provinceID = rs.getInt("provinceID");
                int totalOrders = rs.getInt("totalOrders");
                double totalAmounts = rs.getDouble("totalAmounts");
                rankID = rs.getInt("rankID");
                customers.add(new Customer(customerID, lastName, firstName, gender, dob, mobile, address, email, provinceID, totalOrders, totalAmounts, rankID));
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return customers;
    }

    @Override
    public List<Customer> selectByProvince(int provinceID) {
        List<Customer> customers = new ArrayList<>();
        String query = "{CALL get_customer_by_province(?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, provinceID);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("customerID");
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                int gender = rs.getInt("gender");
                String dob = rs.getString("dob");
                String mobile = rs.getString("mobile");
                String address = rs.getString("address");
                String email = rs.getString("email");
                provinceID = rs.getInt("provinceID");
                int totalOrders = rs.getInt("totalOrders");
                double totalAmounts = rs.getDouble("totalAmounts");
                int rankID = rs.getInt("rankID");
                customers.add(new Customer(customerID, lastName, firstName, gender, dob, mobile, address, email, provinceID, totalOrders, totalAmounts, rankID));
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return customers;
    }

    public Customer getNewCustomer() {
        Customer customer = null;
        String query = "{CALL get_new_customer()}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("customerID");
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                int gender = rs.getInt("gender");
                String dob = rs.getString("dob");
                String mobile = rs.getString("mobile");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int provinceID = rs.getInt("provinceID");
                int totalOrders = rs.getInt("totalOrders");
                double totalAmounts = rs.getDouble("totalAmounts");
                int rankID = rs.getInt("rankID");
                customer = new Customer(customerID, lastName, firstName, gender, dob, mobile, address, email, provinceID, totalOrders, totalAmounts, rankID);
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return customer;
    }

    public List<Customer> sortByName() {
        String query = "{CALL sort_customer_by_name()}";
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("customerID");
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                int gender = rs.getInt("gender");
                String dob = rs.getString("dob");
                String mobile = rs.getString("mobile");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int provinceID = rs.getInt("provinceID");
                int totalOrders = rs.getInt("totalOrders");
                double totalAmounts = rs.getDouble("totalAmounts");
                int rankID = rs.getInt("rankID");
                customers.add(new Customer(customerID, lastName, firstName, gender, dob, mobile, address, email, provinceID, totalOrders, totalAmounts, rankID));
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return customers;
    }

    @Override
    public boolean updateCustomerByRank(int currentID, int newID) {
        boolean rowUpdated = false;
        String query = "{CALL update_customer_by_rank(?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, currentID);
            callableStatement.setInt(2, newID);
            callableStatement.executeUpdate();
            rowUpdated = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteCustomerByRank(int rankID) {
        String query = "{CALL delete_customer_by_rank(?)}";
        boolean rowDeleted = false;
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, rankID);
            callableStatement.executeUpdate();
            rowDeleted = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean updateCustomerByProvince(int currentID, int newID) {
        boolean rowUpdated = false;
        String query = "{CALL update_customer_by_province(?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, currentID);
            callableStatement.setInt(2, newID);
            callableStatement.executeUpdate();
            rowUpdated = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteCustomerByProvince(int provinceID) {
        String query = "{CALL delete_customer_by_province(?)}";
        boolean rowDeleted = false;
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, provinceID);
            callableStatement.executeUpdate();
            rowDeleted = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rowDeleted;
    }
}
