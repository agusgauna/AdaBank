package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MailDAO implements DAO<Mail> {
    private MailDAO mailDAO = new MailDAO(false);
    private ClientDAO clientDAO = new ClientDAO(false);
    private Boolean willCloseConnection = true;

    private char mail;
    private Client client;

    public MailDAO() {
    }

    public MailDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    public List<Mail> findAll() {
        String sql = "SELECT * FROM Mail";
        ArrayList<Mail> mails = new ArrayList<>();

        try {
            Connection connection = DBConection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Mail mail = mailDAO.findById(rs.getInt("id"));
                Client client = new Client(rs.getInt("id"), rs.getString("name"),
                        rs.getString("lastName"), rs.getInt("documentNumber"), rs.getString("typeDocument"));
                Client.add(client);

            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return mails;
    }

    public Mail findById(Integer id) {
        String sql = "SELECT * FROM Mail WHERE id = ?";
        Mail mail = null;

        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Mail mail1 = mailDAO.findById(rs.getInt("id"));
                Client client = new Client(rs.getInt("id"), rs.getString("name"),
                        rs.getString("lastName"), rs.getInt("documentNumber"), rs.getString("typeDocument"));
                Client.add(client);
            }
            if (willCloseConnection)
                connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return mail;
    }

    @Override
    public Boolean save(Mail mail) {
        return null;
    }

    @Override
    public Boolean update(Mail mail, Integer id) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

}