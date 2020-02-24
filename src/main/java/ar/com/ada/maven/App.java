package ar.com.ada.maven;

import ar.com.ada.maven.root.controller.MainController;
import ar.com.ada.maven.root.model.DBConection;
import ar.com.ada.maven.root.model.dao.DAO;
import ar.com.ada.maven.root.model.dto.Client;
import ar.com.ada.maven.root.view.ClientView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        MainController.run();
    }
}

