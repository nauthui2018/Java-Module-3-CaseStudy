package dao;

import model.Province;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDAO implements BaseDAO<Province> {
    HelperDAO helper = new HelperDAO();

    @Override
    public List findAll() {
        String query = "{CALL get_all_provinces()}";
        List<Province> provinces = new ArrayList<>();
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                provinces.add(new Province(id, name, code));
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return provinces;
    }

    @Override
    public void add(Province province) {
        String query = "{CALL add_new_province(?,?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, province.getId());
            callableStatement.setString(2, province.getName());
            callableStatement.setString(3, province.getCode());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
    }

    @Override
    public boolean delete(Province province) {
        String query = "{CALL delete_province(?)}";
        boolean rowDeleted = false;
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, province.getId());
            callableStatement.executeUpdate();
            rowDeleted = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Province province) {
        boolean rowUpdated = false;
        String query = "{CALL update_province(?,?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, province.getId());
            callableStatement.setString(2, province.getName());
            callableStatement.setString(3, province.getCode());
            callableStatement.executeUpdate();
            rowUpdated = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public Province selectById(int id) {
        Province province = null;
        String query = "{CALL get_province_by_id(?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                province = new Province(id, name, code);
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return province;
    }
}
