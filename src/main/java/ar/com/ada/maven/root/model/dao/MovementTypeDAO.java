package ar.com.ada.maven.root.model.dao;


import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.MovementType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovementTypeDAO implements DAO<MovementType>{
    private Boolean willCloseConnection = true;

    public MovementTypeDAO() { }

    public MovementTypeDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    @Override
    public List<MovementType> findAll() {
        String sql = "SELECT * FROM Movement_type";
        List<MovementType> movementTypes = new ArrayList<>();

        try {
            Connection connection = DBConection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                MovementType movement_type = new MovementType(rs.getInt("id"), rs.getString("type"));
                movementTypes.add(movement_type);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return movementTypes;
    }

    @Override
    public MovementType findById(Integer id) {
        String sql = "SELECT * FROM Movement_type WHERE id = ?";
        MovementType movementType = null;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                movementType = new MovementType(rs.getInt("id"), rs.getString("type"));
            if (willCloseConnection)
                connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return movementType;
    }

    @Override
    public Boolean save(MovementType movementTypeId) {
        String sql = "INSERT INTO Movement_type (id, type) VALUES (?,?)";
        int hasInsert = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,movementTypeId.getId());
            preparedStatement.setString(2,movementTypeId.getType());
            hasInsert = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasInsert == 1;
    }

    @Override
    public Boolean update(MovementType movement_type, Integer id) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

        public MovementType findByType(String type) {
        String sql = "SELECT * FROM Movement_type WHERE type = ?";
        MovementType movementType = null;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, type);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                movementType = new MovementType(rs.getInt("id"), rs.getString("type"));
            if (willCloseConnection)
                connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return movementType;
    }


}
