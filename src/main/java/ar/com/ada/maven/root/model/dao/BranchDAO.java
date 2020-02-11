package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.Bank;
import ar.com.ada.maven.root.model.dto.Branch;
import java.sql.*;
import java.util.ArrayList;

public class BranchDAO implements DAO<Branch> {
    private BankDAO bankDAO = new BankDAO(false);
    private Boolean willCloseConnection = true;

    public BranchDAO() { }

    public BranchDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    @Override
    public ArrayList<Branch> findAll() {
        String sql = "SELECT * FROM Branch";
        ArrayList<Branch> sucursales = new ArrayList<>();
        try {
            Connection connection = DBConection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Bank bank = bankDAO.findById(rs.getInt("bank_id"));
                Branch sucursal = new Branch(rs.getInt("id"), rs.getString("name"), rs.getInt("code"), bank);
                sucursales.add(sucursal);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return sucursales;
    }

    @Override
    public Branch findById(Integer id) {
        String sql = "SELECT * FROM Branch WHERE id = ?";
        Branch branch = null;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Bank banco = bankDAO.findById(rs.getInt("bank_id"));
                branch = new Branch(rs.getInt("id"), rs.getString("name"), rs.getInt("code"), banco);
            }
            if (willCloseConnection)
                connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return branch;
    }

    @Override
    public Boolean save(Branch branch) {
        String sql = "INSERT INTO Branch (nombre) VALUES (?)";
        int hasSave = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, branch.getName());
            hasSave = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasSave == 1;
    }

    @Override
    public Boolean update(Branch branch, Integer id) {
        String sql = "UPDATE Branch set nombre = ? where id = ?";
        int hasUpdate = 0;
        try {
            Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, branch.getName());
            preparedStatement.setInt(2, id);
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Branch where id = ?";
        int hasDelete = 0;
        Connection connection = null;
        try {
            connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            hasDelete = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasDelete == 1;
    }
}








