package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.model.dto.Bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BankDAO implements DAO<Bank>{

    private Boolean willCloseConnection = true;

    public BankDAO() {
    }
    public AccountDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }


    @Override
    public ArrayList<Bank> findAll() {
        String sql = "SELECT * FROM Bank";

        ArrayList<Account> bancos = new ArrayList<>();
        try{
        Connection connection = DBConection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
            }
}
