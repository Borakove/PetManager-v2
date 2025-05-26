package com.mycompany.petmanagermodelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/petmanager";
    private static final String USUARIO = "root";
    private static final String SENHA = "";      

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    
public static void main(String[] args) {
    try {
        Connection conn = Conexao.getConnection();
        if (conn != null) {
            System.out.println("Conexão realizada com sucesso!");
            conn.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        }
    }
}
