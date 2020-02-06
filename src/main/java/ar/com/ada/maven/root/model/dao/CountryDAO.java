package ar.com.ada.maven.root.model.dao;

import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dto.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class CountryDAO implements DAO<Country> {

        private Boolean willCloseConnection = true;
        public Integer id;
        public String name;
        public int code;
        private Country country;

        public CountryDAO() {}


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
            return null;
        }

        @Override
        public Boolean update(Country country, Integer id) {
            return null;
        }

        @Override
        public Boolean delete(Integer id) {
            return null;
        }

    }

