package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.model.dto.Movement;
import ar.com.ada.maven.root.model.dto.Movement_type;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

    public class MovementDAO implements DAO<Movement> {
    private AccountDAO accountDAO = new AccountDAO(false);
    private Movement_typeDAO movement_typeDAO = new Movement_typeDAO(false);
    private Boolean willCloseConnection = true;

    public MovementDAO() {}

    public MovementDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    public List<Movement> findAll() {
        String sql = "SELECT * FROM Movement";
        ArrayList<Movement> movements = new ArrayList<>();

        try {
            Connection connection = DBConection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Account account = accountDAO.findById(rs.getInt("id"));
                Movement_type movement_type = movement_typeDAO.findById(rs.getInt("id"));
                Movement movement = new Movement(rs.getInt("id"), rs.getDate("date"),
                        rs.getDouble("amount"), rs.getString("description"), account, movement_type);
                movements.add(movement);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return movements;
    }

    public Movement findById(Integer id) {
        String sql = "SELECT * FROM Movement WHERE id = ?";
        Movement movement = null;

        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Account account = accountDAO.findById(rs.getInt("account_id"));
            Movement_type movement_type = movement_typeDAO.findById(rs.getInt("movement_type_id"));
            movement = new Movement(rs.getInt("id"), rs.getDate("date"), rs.getDouble("amount"),
                    rs.getString("description"), account, movement_type);
            }
            if (willCloseConnection)
                connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return movement;
    }
    public Boolean save (Movement movement) {
        String sql = "INSERT INTO Movement (id, date, amount, description, account, movement_type) VALUES (?,?,?,?,?,?)";
        int hasInsert = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, movement.getDescription());
            hasInsert = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasInsert == 1;
    }

    @Override
    public Boolean update(Movement movement, Integer id) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

}
