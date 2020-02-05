package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.Account_type;
import ar.com.ada.maven.root.model.dto.Movement_type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Movement_typeDAO implements DAO<Movement_type>{
    private Boolean willCloseConnection = true;

    public Movement_typeDAO() { }

    public Movement_typeDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    @Override
    public List<Account_type> findAll() {
        String sql = "SELECT * FROM Movement_type";
        List<Movement_type> movement_types = new ArrayList<>();

        try {
            Connection connection = DBConection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Movement_type movement_type = new Movement_type(rs.getInt("id"), rs.getString("debit"), rs.getString("credit"));
                movement_types.add(movement_type);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return movement_types;
    }

    @Override
    public Movement_type findById(Integer id) {
        String sql = "SELECT * FROM Movement_type WHERE id = ?";
        Movement_type movement_type = null;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                movement_type = new Movement_type(rs.getInt("id"), rs.getString("debit"), rs.getString("credit"));
            if (willCloseConnection)
                connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return movement_type;
    }

    @Override
    public Boolean save(Movement_type movement_type) {
        String sql = "INSERT INTO Movement_type (id, debit, credit) VALUES (?,?,?)";
        int hasInsert = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,movement_type.getId());
            preparedStatement.setString(2,movement_type.getDebit());
            preparedStatement.setString(3,movement_type.getCredit());
            hasInsert = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasInsert == 1;
    }

    @Override
    public Boolean update(Movement_type movement_type, Integer id) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
