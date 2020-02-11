package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.model.dto.Account_type;
import ar.com.ada.maven.root.model.dto.Branch;
import ar.com.ada.maven.root.model.dto.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements DAO<Account> {
    private ClientDAO clientDAO = new ClientDAO(false);
    private Account_typeDAO account_typeDAO = new Account_typeDAO(false);
    private BranchDAO branchDAO = new BranchDAO(false);
    private Boolean willCloseConnection = true;

    public AccountDAO() { }

    public AccountDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    @Override
    public ArrayList<Account> findAll() {
        String sql = "SELECT * FROM Account";
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            Connection connection = DBConection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Client client = clientDAO.findById(rs.getInt("client_id"));
                Account_type account_type = account_typeDAO.findById(rs.getInt("account_type_id"));
                Branch branch = branchDAO.findById(rs.getInt("branch_id"));
                Account account = new Account(rs.getInt("id"), rs.getString("currency"), rs.getInt("accountNumber"), rs.getDouble("balance"), rs.getString("controlNumber"), client, account_type, branch);
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());

        }
        return accounts;
    }

    @Override
    public Account findById ( Integer id) {
        String sql = "SELECT * FROM Account WHERE id = ?";
        Account account = null;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Client cliente = clientDAO.findById(rs.getInt("client_id"));
                Account_type account_type = account_typeDAO.findById(rs.getInt("account_type"));
                Branch branch = branchDAO.findById(rs.getInt("branch_id"));
                account = new Account(rs.getInt("id"), rs.getString("currency"), rs.getInt("accountNumber"), rs.getDouble("balance"), rs.getString("controlNumber"), cliente, account_type, branch);
                if (willCloseConnection) ;
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());

        }
        return account;
    }

    @Override
    public Boolean save(Account account) {
        String sql = "INSERT INTO Account (id, currency, accountNumber, balance, controlNumber, client) VALUES (?, ?, ?, ?, ?, ?)";
        int hasSave = 0;
        try{
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getId());
            preparedStatement.setString(2, account.getCurrency());
            preparedStatement.setInt(3, account.getNumber());
            preparedStatement.setDouble(4, account.getBalance());
            preparedStatement.setString(5, account.getControlNumber());
            preparedStatement.setInt(6, account.getClient().getId());
            hasSave = preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasSave == 1;
        }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Account WHERE id = ?";
        int hasDelete = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            hasDelete = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("\"CONNECTION ERROR: \" + e.getMessage()");
        }
        return hasDelete == 1;
    }

    @Override
    public Boolean update(Account account, Integer id) {
        String sql = "UPDATE Account set accountNumber = ? where id? ? ";
        int hasUpdate = 0;
        try{
        Connection connection = DBConection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account.getNumber());
        preparedStatement.setInt(2, id);
        } catch (SQLException e) {
            System.out.println("\"CONNECTION ERROR: \" + e.getMessage()");
        }
        return hasUpdate == 1;
    }




    public List<Account> findAll(int limit, int offset) {
        String sql = "SELECT FROM Account LIMIT ? OFFSET ?";
        List<Account> cuentas = new ArrayList<>();
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Client cliente = clientDAO.findById(rs.getInt("client_id"));
                Account_type account_type = account_typeDAO.findById(rs.getInt("account_type"));
                Branch branch = branchDAO.findById(rs.getInt("branch_id"));
                Account account = new Account(rs.getInt("id"), rs.getString("currency"), rs.getInt("number"),
                        rs.getDouble("balance"), rs.getString("controlNumber"), cliente, account_type, branch);
                cuentas.add(account);
            }
            connection.close();
        } catch(SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return cuentas;
    }
        }


