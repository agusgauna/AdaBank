package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO implements DAO<Contact> {
    private ContactDAO contactDAO = new ContactDAO(false);
    private ClientDAO clientDAO = new ClientDAO(false);
    private Boolean willCloseConnection = true;

    public Integer id;
    public String mail;
    public int telephone;
    private Client client;

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
                Contact contact = contactDAO.findById(rs.getInt("id"));
                Client client = new Client(rs.getInt("id"), rs.getString("name"),
                        rs.getString("lastName"), rs.getInt("documentNumber"), rs.getString("typeDocument"));
                contacts.add(contact);

            }
            connection.close();
        } catch (SQLException e) {
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
                Contact contact1 = contactDAO.findById(rs.getInt("id"));
                Client client = new Client(rs.getInt("id"), rs.getString("name"),
                        rs.getString("lastName"), rs.getInt("documentNumber"), rs.getString("typeDocument"));
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
        return null;
    }

    @Override
    public Boolean update(Contact contact, Integer id) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }


}