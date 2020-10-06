package dao;

import model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements BaseDAO<User> {
    HelperDAO helper = new HelperDAO();

    public boolean login(User user) {
        boolean status = false;
        String query = "{CALL login(?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, user.getUsername());
            callableStatement.setString(2, user.getPassword());
            ResultSet rs = callableStatement.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return status;
    }

    @Override
    public List findAll() {
        String query = "{CALL get_all_users()}";
        List<User> users = new ArrayList<>();
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                int customerID = rs.getInt("customerID");
                boolean admin = rs.getBoolean("admin");
                users.add(new User(username, password, customerID, admin));
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return users;
    }

    @Override
    public void add(User user) {
        String query = "{CALL add_new_user(?,?,?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, user.getUsername());
            callableStatement.setString(2, user.getPassword());
            callableStatement.setInt(3, user.getCustomerID());
            callableStatement.setBoolean(4, user.isAdmin());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
    }

    @Override
    public boolean delete(User user) {
        String query = "{CALL delete_user(?)}";
        boolean rowDeleted = false;
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, user.getUsername());
            callableStatement.executeUpdate();
            rowDeleted = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean update(User user) {
        boolean rowUpdated = false;
        String query = "{CALL update_user(?,?,?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, user.getUsername());
            callableStatement.setString(2, user.getPassword());
            callableStatement.setInt(3, user.getCustomerID());
            callableStatement.setBoolean(4, user.isAdmin());
            callableStatement.executeUpdate();
            rowUpdated = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public User selectById(int customerID) {
        User user = null;
        String query = "{CALL get_user_by_id(?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, customerID);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                customerID = rs.getInt("customerID");
                boolean admin = rs.getBoolean("admin");
                user = new User(username, password, customerID, admin);
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return user;
    }
}
