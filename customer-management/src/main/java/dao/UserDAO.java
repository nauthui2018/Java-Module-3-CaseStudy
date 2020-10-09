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

    public boolean login(String userUsername, String userPassword) {
        boolean status = false;
        String query = "{CALL login(?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, userUsername);
            callableStatement.setString(2, userPassword);
            ResultSet rs = callableStatement.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return status;
    }

    public User selectByUsername(String userUsername) {
        User user = null;
        String query = "{CALL get_user_by_username(?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, userUsername);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                userUsername = rs.getString("userUsername");
                String userPassword = rs.getString("userPassword");
                int customerID = rs.getInt("customerID");
                boolean userAdmin = rs.getBoolean("userAdmin");
                user = new User(userUsername, userPassword, customerID, userAdmin);
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return user;
    }

    @Override
    public List findAll() {
        String query = "{CALL get_all_users()}";
        List<User> users = new ArrayList<>();
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                String userUsername = rs.getString("userUsername");
                String userPassword = rs.getString("userPassword");
                int customerID = rs.getInt("customerID");
                boolean userAdmin = rs.getBoolean("userAdmin");
                users.add(new User(userUsername, userPassword, customerID, userAdmin));
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return users;
    }

    public boolean checkUsername(String username) {
        List<User> users = findAll();
        boolean isExisted = false;
        for (User item: users) {
            if (item.getUserUsername().equals(username)) {
                isExisted = true;
                break;
            }
        }
        return isExisted;
    }

    @Override
    public void add(User user) {
        String query = "{CALL add_new_user(?,?,?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, user.getUserUsername());
            callableStatement.setString(2, user.getUserPassword());
            callableStatement.setInt(3, user.getCustomerID());
            callableStatement.setBoolean(4, user.isUserAdmin());
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
            callableStatement.setString(1, user.getUserUsername());
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
        String query = "{CALL update_password(?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, user.getUserUsername());
            callableStatement.setString(2, user.getUserPassword());
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
                String userUsername = rs.getString("userUsername");
                String userPassword = rs.getString("userPassword");
                customerID = rs.getInt("customerID");
                boolean userAdmin = rs.getBoolean("userAdmin");
                user = new User(userUsername, userPassword, customerID, userAdmin);
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return user;
    }
}
