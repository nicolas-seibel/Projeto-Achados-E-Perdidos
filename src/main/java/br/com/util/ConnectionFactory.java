package br.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/Achados_E_Perdidos";
    private static final String USER = "Achados_E_Perdidos";
    private static final String PASSWORD = "6958";
    
    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do MySQL não encontrado.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}