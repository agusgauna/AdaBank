package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements DAO <Client> {
    private int id;
    private String name;
    private String lastName;
    private int document_number;
    private int type_document;

    public ClientDAO() {
    }

    public ClientDAO(Boolean willCloseConnection) {
    }

    @Override

    public List<Client> findAll() {
        String sql = "SELECT * FROM Client";
        List<Client> clientes = new ArrayList<>();

        Connection connection = DBConection.getConnection();
        Statement statement = connection.createStatement();



    }
}
