package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.Account_type;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Account_typeDAO implements DAO<Account_type> {
    private Boolean willCloseConnection = true;

    private int id;
    private String name;
    private Integer code_control;

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
                Account_type account_type = new Account_type()
            }
        }
        return null;
    }

    @Override
    public Account_type findById(Integer id) {
        return null;
    }

    @Override
    public Boolean save(Account_type account_type) {
        return null;
    }

    @Override
    public Boolean update(Account_type account_type, Integer id) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
