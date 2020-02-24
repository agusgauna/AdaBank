package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.Client;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements DAO<Client> {
    private Boolean willCloseConnection = true;

    public ClientDAO() { }

    public ClientDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
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
                Client client = new Client(rs.getInt("id"), rs.getString("name"), rs.getString("last_name"), rs.getString("type_doc"), rs.getInt("doc"));
                clientes.add(client);
            }
            connection.close();
        } catch (Exception e) {
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
                cliente = new Client(rs.getInt(id), rs.getString("name"), rs.getString("last_name"), rs.getString("type_doc"), rs.getInt("doc"));

            if (willCloseConnection) ;
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return cliente;
    }

    public Client findByDoc(Integer doc) {
        String sql = "SELECT * FROM Client where doc = ?";
        Client client = null;

        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, doc);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                client = new Client(rs.getInt("id"), rs.getString("name"), rs.getString("last_name"), rs.getString("type_doc"), rs.getInt("doc"));
            if (willCloseConnection)
                connection.close();
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return client;

    }

    @Override
    public Boolean save(Client client) {
        String sql = "INSERT INTO Client (name, last_name, type_doc, doc) VALUES (?, ?, ?, ?)";
        int hasSave = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setString(3,client.getType_doc());
            preparedStatement.setInt(4, client.getDoc());
            hasSave = preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasSave == 1;
    }

    @Override
    public Boolean update(Client client, Integer id) {
        String sql = "UPDATE Client set name = ? where id? ? ";
        int hasUpdate = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setInt(2, id);
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Client WHERE id = ?";
        int hasDelete = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            hasDelete = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasDelete == 1;
    }


    public List<Client> findAll(int limit, int offset) {
        String sql = "SELECT * FROM Client LIMIT ? OFFSET ?";
        List<Client> clientes = new ArrayList<>();
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Client client = new Client(rs.getInt("id"), rs.getString("name"), rs.getString("last_name"), rs.getString("type_doc"), rs.getInt("doc"));
                clientes.add(client);
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR:" + e.getMessage());
        }
        return clientes;
    }

    public int getTotalClients() {
        String sql = "SELECT COUNT(*) AS total FROM Client";
        int total = 0;
        try {
            Connection connection = DBConection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next())  total = rs.getInt("total");
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return total;
    }
    public Client findByName(String name, String lastName) {
        String sql = "SELECT * FROM Client WHERE nombre = ? y apellido = ?";
        Client client = null;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                client = new Client(rs.getString("name"), rs.getString("last_name"));

            if (willCloseConnection)
                connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return client;
    }
}
