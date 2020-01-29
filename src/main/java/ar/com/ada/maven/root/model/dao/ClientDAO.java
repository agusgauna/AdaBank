package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.Client;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements DAO<Client> {
    private int id;
    private String name;
    private String lastName;
    private int documentNumber;
    private String typeDocument;

    private Boolean willCloseConnection = true;

    public ClientDAO() {
    }

    public ClientDAO(Boolean willCloseConnection) {
    }

    @Override

    public List<Client> findAll() {
        String sql = "SELECT * FROM Client";
        List<Client> clientes = new ArrayList<>();


        try {
            Connection connection = DBConection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Client client = new Client(rs.getInt("id"), rs.getString("name"), rs.getString("lastName"), rs.getInt("documentNumber"), rs.getString("typeDocument"));
                clientes.add(client);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public Client findById(Integer id) {
        String sql = "SELECT * FROM Client WHERE ID = ?";
        Client cliente = null;

        try (Connection connection = DBConection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                cliente = new Client(rs.getInt(id), rs.getString("name"), rs.getString("lastName"), rs.getInt("documentNumber"), rs.getString("typeDocument"))

            if (willCloseConnection) ;
            connection.close();

        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return cliente;
    }

    public Client findByName(String name, String lastName) {
        String sql = "SELECT * FROM Client where name = ? AND lastname = ?";
        Client client = null;

        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                client = new Client(rs.getInt(id), rs.getString("name"), rs.getString("lastName"), rs.getInt("documentNumber"), rs.getString("typeDocument"));
            if (willCloseConnection)
                connection.close();
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return client;

    }

    @Override
    public Boolean save(Client client) {
        String sql = "INSERT INTO Client (nombre, lastname) VALUES (?, ?)";
        int hasSave = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getLastName());

            hasSave = preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasSave == 1;
        }

    @Override
    public Boolean update (Client client, Integer id) {
    String sql= "UPDATE Client set name = ? where id? ? ";
    int hasUpdate = 0;
    try {
        Connection connection = DBConection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, client.getName());
        preparedStatement.setInt(2, id);
    }catch(SQLException e) {
        System.out.println("\"CONNECTION ERROR: \" + e.getMessage()");
    }
return hasUpdate == 1;
    }

    @Override
    public Boolean delete () // segir metodo delete.

    }
