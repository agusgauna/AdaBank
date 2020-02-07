package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.Country;
import sun.security.pkcs11.Secmod;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class CountryDAO implements DAO<Country> {
        private Boolean willCloseConnection = true;

        public CountryDAO() {}

        public CountryDAO(Boolean willCloseConnection) {
            this.willCloseConnection = willCloseConnection;
        }

        @Override
        public List<Country> findAll() {
            String sql = "SELECT * FROM Country";
            ArrayList<Country> country = new ArrayList<>();

            try {
                Connection connection = DBConection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    Country country1 = new Country(rs.getInt("id"), rs.getString("name"), rs.getInt("code"));
                    country.add(country1);
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println("CONNECTION ERROR: " + e.getMessage());
            }
            return country;
        }

        @Override
        public Country findById(Integer id) {
            String sql = "SELECT * FROM Country WHERE Id = ?";
            Country country = null;
            try {
                Connection connection = DBConection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    country = new Country(rs.getInt("id"), rs.getString("name"), rs.getInt("code"));
                }
                if (willCloseConnection)
                    connection.close();
            } catch (Exception e) {
                System.out.println("CONNECTION ERROR: " + e.getMessage());
            }
            return country;
        }


        @Override
        public Boolean save(Country country) {
            String sql = "INSERT INTO Country (name,code) values (?,?)";
            int newCountry = 0;
            try {
                Connection connection = DBConection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, country.getName());
                newCountry = preparedStatement.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                System.out.println("CONNECTION ERROR: " + e.getMessage());
            }
            return newCountry == 1;
        }

        @Override
        public Boolean update(Country country, Integer id) {
            String sql = "UPDATE Country SET country = ? WHERE Id = ?";
            int hasUpdate = 0;
            //para comparar un objeto que quiero  debo actualizar con la base de datos.
            Country countryDB = findById(id);

            try {
                Connection connection = DBConection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, country.getName());
                preparedStatement.setInt(2, id);

                if (!country.getName().equals(countryDB.getName()))
                    hasUpdate = preparedStatement.executeUpdate();
                connection.close();
            } catch (Exception e) {
                System.out.println("CONNECTION ERROR: " + e.getMessage());
            }
            return hasUpdate == 1;
        }

        @Override
        public Boolean delete(Integer id) {
            String sql = "DELETE FROM Country WHERE Id = ?";
            int hasDelete = 0;
            try {
                Connection connection = DBConection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                hasDelete = preparedStatement.executeUpdate();

            } catch (Exception e) {
                System.out.println("CONNECTION ERROR: " + e.getMessage());
            }
            return hasDelete == 1;
        }

    }

