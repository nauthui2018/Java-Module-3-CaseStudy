package dao;

import model.Province;
import model.Rank;

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
                int provinceID = rs.getInt("provinceID");
                String provinceName = rs.getString("provinceName");
                String provinceCode = rs.getString("provinceCode");
                provinces.add(new Province(provinceID, provinceName, provinceCode));
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return provinces;
    }

    @Override
    public void add(Province province) {
        String query = "{CALL add_new_province(?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, province.getProvinceName());
            callableStatement.setString(2, province.getProvinceCode());
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
            callableStatement.setInt(1, province.getProvinceID());
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
            callableStatement.setInt(1, province.getProvinceID());
            callableStatement.setString(2, province.getProvinceName());
            callableStatement.setString(3, province.getProvinceCode());
            callableStatement.executeUpdate();
            rowUpdated = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public Province selectById(int provinceID) {
        Province province = null;
        String query = "{CALL get_province_by_id(?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, provinceID);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                provinceID = rs.getInt("provinceID");
                String provinceName = rs.getString("provinceName");
                String provinceCode = rs.getString("provinceCode");
                province = new Province(provinceID, provinceName, provinceCode);
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return province;
    }

    public List<Province> listAfterDelete(int id) {
        String query = "{CALL get_remain_provinces(?)}";
        List<Province> provinces = new ArrayList<>();
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int provinceID = rs.getInt("provinceID");
                String provinceName = rs.getString("provinceName");
                String provinceCode = rs.getString("provinceCode");
                provinces.add(new Province(provinceID, provinceName, provinceCode));
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return provinces;
    }

    public boolean checkProvinceName(String provinceName, List<Province> provinces) {
        boolean isExisted = false;
        for (Province item: provinces) {
            if (item.getProvinceName().equals(provinceName)) {
                isExisted = true;
                break;
            }
        }
        return isExisted;
    }

    public boolean checkProvinceCode(String provinceCode, List<Province> provinces) {
        boolean isExisted = false;
        for (Province item: provinces) {
            if (item.getProvinceCode().equals(provinceCode)) {
                isExisted = true;
                break;
            }
        }
        return isExisted;
    }
}
