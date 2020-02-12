package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO implements DAO<Contact> {
    private ClientDAO clientDAO = new ClientDAO(false);
    private Boolean willCloseConnection = true;

    public ContactDAO() {
    }

    public ContactDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    public List<Contact> findAll() {
        String sql = "SELECT * FROM Contact";
        ArrayList<Contact> contacts = new ArrayList<>();
        try {
            Connection connection = DBConection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Client client = clientDAO.findById(rs.getInt("client_id"));
                Contact contact = new Contact(rs.getInt("id"), rs.getString("mail"), rs.getInt("telephone"), client);
                contacts.add(contact);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return contacts;
    }

    public Contact findById(Integer id) {
        String sql = "SELECT * FROM Contact WHERE id = ?";
        Contact contact = null;

        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Client client = clientDAO.findById(rs.getInt("client_id"));
                contact = new Contact(rs.getInt("id"), rs.getString("mail"), rs.getInt("telephone"), client);
            }
            if (willCloseConnection)
                connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return contact;
    }


    @Override
    public Boolean save(Contact contact) {
        String sql = "INSERT INTO Contact (mail,telephone,client) values (?,?,>)";
        int newContact = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, contact.getMail());
            preparedStatement.setInt(2, contact.getTelephone());
            preparedStatement.setInt(3, contact.getClient().getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newContact == 1;
    }

    @Override
    public Boolean update(Contact contact, Integer id) {
        String sql = "UPDATE Contact SET contact = ? WHERE Id = ?";
        int hasUpdate = 0;
        Contact contactDB = findById(id);
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, contact.getMail());
            preparedStatement.setInt(2, contact.getTelephone());
            preparedStatement.setInt(3, contact.getClient().getId());
            hasUpdate = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Contact WHERE Id = ?";
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