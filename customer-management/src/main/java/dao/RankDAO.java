package dao;

import model.Rank;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RankDAO implements BaseDAO<Rank> {
    HelperDAO helper = new HelperDAO();

    @Override
    public List findAll() {
        String query = "{CALL get_all_ranks()}";
        List<Rank> ranks = new ArrayList<>();
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                ranks.add(new Rank(id, name));
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return ranks;
    }

    @Override
    public void add(Rank rank) {
        String query = "{CALL add_new_rank(?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, rank.getId());
            callableStatement.setString(2, rank.getName());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
    }

    @Override
    public boolean delete(Rank rank) {
        String query = "{CALL delete_rank(?)}";
        boolean rowDeleted = false;
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, rank.getId());
            callableStatement.executeUpdate();
            rowDeleted = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Rank rank) {
        boolean rowUpdated = false;
        String query = "{CALL update_rank(?,?,?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, rank.getId());
            callableStatement.setString(2, rank.getName());
            callableStatement.executeUpdate();
            rowUpdated = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public Rank selectById(int id) {
        Rank rank = null;
        String query = "{CALL get_rank_by_id(?)}";
        try (Connection connection = helper.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                String name = rs.getString("name");
                rank = new Rank(id, name);
            }
        } catch (SQLException e) {
            helper.printSQLException(e);
        }
        return rank;
    }
}
