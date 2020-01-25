package ar.com.ada.maven;

import ar.com.ada.maven.root.model.DBConection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Connection connection = DBConection.getConnection();

        String sql= "SELECT * FROM client";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet rs = preparedStatement.executeQuery();

        rs.next();

        if (rs.next()){
            System.out.println("Hay listado");
        } else {
            System.out.println("No hay datos");
        }
    }
}
