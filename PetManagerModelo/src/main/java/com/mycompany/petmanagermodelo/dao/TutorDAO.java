package com.mycompany.petmanagermodelo.dao;

import com.mycompany.petmanagermodelo.dto.Tutor; // Importa o DTO Tutor
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TutorDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/petshop?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root"; 
    private static final String SENHA = ""; 

    public TutorDAO() {
  
    }

    public Tutor salvar(Tutor tutor) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);

            if (tutor.getId() == 0) { 
                String sql = "INSERT INTO tutores (nome, telefone, endereco) VALUES (?, ?, ?)";
                pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, tutor.getNome());
                pstmt.setString(2, tutor.getTelefone());
                pstmt.setString(3, tutor.getEndereco());
                pstmt.executeUpdate();

                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    tutor.setId(rs.getInt(1)); 
                }
                System.out.println("Tutor salvo no banco de dados: " + tutor.getNome() + " (ID: " + tutor.getId() + ")");
            } else { 
                String sql = "UPDATE tutores SET nome = ?, telefone = ?, endereco = ? WHERE id = ?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, tutor.getNome());
                pstmt.setString(2, tutor.getTelefone());
                pstmt.setString(3, tutor.getEndereco());
                pstmt.setInt(4, tutor.getId());
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Tutor atualizado no banco de dados: " + tutor.getNome() + " (ID: " + tutor.getId() + ")");
                } else {
                    System.err.println("Erro ao atualizar tutor com ID: " + tutor.getId() + ". Tutor não encontrado.");
                    return null;
                }
            }
            return tutor;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao salvar/atualizar tutor: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { System.err.println("Erro ao fechar ResultSet: " + e.getMessage()); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage()); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { System.err.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    public Tutor buscarPorId(int id) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Tutor tutor = null;

        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            String sql = "SELECT id, nome, telefone, endereco FROM tutores WHERE id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                tutor = new Tutor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("endereco")
                );
            }
            return tutor;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao buscar tutor por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { System.err.println("Erro ao fechar ResultSet: " + e.getMessage()); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage()); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { System.err.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    public List<Tutor> listarTodos() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Tutor> tutores = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            String sql = "SELECT id, nome, telefone, endereco FROM tutores";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Tutor tutor = new Tutor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("endereco")
                );
                tutores.add(tutor);
            }
            return tutores;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao listar todos os tutores: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { System.err.println("Erro ao fechar ResultSet: " + e.getMessage()); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { System.err.println("Erro ao fechar Statement: " + e.getMessage()); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { System.err.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    public boolean excluir(int id) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            String sql = "DELETE FROM tutores WHERE id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao excluir tutor: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage()); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { System.err.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }
}