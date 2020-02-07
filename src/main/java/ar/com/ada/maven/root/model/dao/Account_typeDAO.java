package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.Account_type;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Account_typeDAO implements DAO<Account_type> {
    private Boolean willCloseConnection = true;


    public Account_typeDAO() { }

    public Account_typeDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    @Override
    public Collection<Account_type> findAll() {
        String sql = "SELECT * FROM Account_type";
        List<Account_type> account_types = new ArrayList<>();

        try {
            Connection connection = DBConection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Account_type account_type = new Account_type(rs.getInt("id"), rs.getString("name"), rs.getInt("code_control"));
                account_types.add(account_type);
            }
            connection.close();
        } catch (Exception e){
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return account_types;
    }

    @Override
    public Account_type findById(Integer id) {
        String sql = "SELECT * FROM Account_type WHERE id = ?";
        Account_type account_type = null;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                account_type = new Account_type(rs.getInt("id"), rs.getString("name"), rs.getInt("code_control"));
            if (willCloseConnection)
                connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return account_type;
    }

    @Override
    public Boolean save(Account_type account_type) {
        String sql = "INSERT INTO Account_type (nombre) VALUES (?)";
        int hasInsert = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account_type.getName());
            hasInsert = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasInsert == 1;
    }


    @Override
    public Boolean update(Account_type account_type, Integer id) {
        String sql = "UPDATE Account_type SET nombre = ? WHERE id = ?";
        int hasUpdate = 0;

        Account_type account_typeDB = findById(id);

        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account_type.getName());
            preparedStatement.setInt(2, id);

            if (!account_type.getName().equals(account_typeDB.getName()))
                hasUpdate = preparedStatement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Account_type WHERE id = ?";
        int hasDelete = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            hasDelete = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasDelete == 1;
    }
}
