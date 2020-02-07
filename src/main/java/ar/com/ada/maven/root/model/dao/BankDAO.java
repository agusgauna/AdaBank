package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.model.dto.Bank;
import ar.com.ada.maven.root.model.dto.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankDAO implements DAO<Bank> {
    private CountryDAO countryDAO = new CountryDAO(false);
    private Boolean willCloseConnection = true;

    public BankDAO() { }

    public BankDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    @Override
    public ArrayList<Bank> findAll() {
        String sql = "SELECT * FROM Bank";
        ArrayList<Bank> bancos = new ArrayList<>();

        try {
            Connection connection = DBConection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Country country = countryDAO.findById(rs.getInt("country_Id"));
                Bank bank = new Bank(rs.getInt("id"), rs.getString("name"), rs.getInt("iban"), country);
                bancos.add(bank);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());

        }
        return bancos;
    }

    @Override
    public Bank findById(Integer id) {
        String sql = "SELECT * FROM Bank WHERE id = ?";
        Bank bank = null;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Country country = countryDAO.findById(rs.getInt("country_Id"));
                bank = new Bank(rs.getInt("id"), rs.getString("name"), rs.getInt("iban"), country);
            }
                if (willCloseConnection)
                connection.close();

        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return bank;
    }

    public Boolean save(Bank bank) {
        String sql = "INSERT INTO Bank (id, name, iban) VALUES (?, ?, ?)";
        int hasSave = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bank.getId());
            preparedStatement.setString(2, bank.getName());
            preparedStatement.setInt(3, bank.getIban());
            hasSave = preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());

        }
        return hasSave == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Bank WHERE id = ?";
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
    public Boolean update(Bank bank, Integer id) {
        String sql = "UPDATE Bank set name = ? where id? ? ";
        int hasUpdate = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bank.getName());
            preparedStatement.setInt(2, bank.getId());
        } catch (SQLException e) {
            System.out.println("\"CONNECTION ERROR: \" + e.getMessage()");
        }
        return hasUpdate == 1;
    }

    public List<Bank> findAll(int limit, int offset) {
        String sql = "SELECT FROM Bank LIMIT ? OFFSET ?";
        List<Bank> bancos = new ArrayList<>();
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Country country = countryDAO.findById(rs.getInt("country_Id"));
                Bank bank = new Bank (rs.getInt("id"), rs.getString("name"), rs.getInt("iban"), country);
                bancos.add(bank);
            } connection.close();
        } catch (SQLException e) {
            System.out.println("\"CONNECTION ERROR: \" + e.getMessage()");
        }
        return bancos;

    }

}
