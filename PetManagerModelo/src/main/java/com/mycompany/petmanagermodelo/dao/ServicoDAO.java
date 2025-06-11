package com.mycompany.petmanagermodelo.dao;

import com.mycompany.petmanagermodelo.dto.Servico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/petshop?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root"; 
    private static final String SENHA = ""; 

    public ServicoDAO() {
        // construtor não precisa de lógica, é tudo feito no crud
    }

    public Servico salvar(Servico servico) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);

            if (servico.getId() == 0) { //
                String sql = "INSERT INTO servicos (descricao, valor) VALUES (?, ?)";
                pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, servico.getDescricao());
                pstmt.setDouble(2, servico.getValor()); // setDouble para o tipo double
                pstmt.executeUpdate();

                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    servico.setId(rs.getInt(1)); 
                }
                System.out.println("Serviço salvo no banco de dados: " + servico.getDescricao() + " (ID: " + servico.getId() + ")");
            } else { 
                String sql = "UPDATE servicos SET descricao = ?, valor = ? WHERE id = ?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, servico.getDescricao());
                pstmt.setDouble(2, servico.getValor());
                pstmt.setInt(3, servico.getId());
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Serviço atualizado no banco de dados: " + servico.getDescricao() + " (ID: " + servico.getId() + ")");
                } else {
                    System.err.println("Erro ao atualizar serviço com ID: " + servico.getId() + ". Serviço não encontrado.");
                    return null;
                }
            }
            return servico;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao salvar/atualizar serviço: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { System.err.println("Erro ao fechar ResultSet: " + e.getMessage()); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage()); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { System.err.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    public Servico buscarPorId(int id) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Servico servico = null;

        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            String sql = "SELECT id, descricao, valor FROM servicos WHERE id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                servico = new Servico(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("valor") // getDouble para o tipo double
                );
            }
            return servico;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao buscar serviço por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { System.err.println("Erro ao fechar ResultSet: " + e.getMessage()); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage()); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { System.err.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    public List<Servico> listarTodos() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Servico> servicos = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            String sql = "SELECT id, descricao, valor FROM servicos";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Servico servico = new Servico(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("valor")
                );
                servicos.add(servico);
            }
            return servicos;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao listar todos os serviços: " + e.getMessage());
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
            String sql = "DELETE FROM servicos WHERE id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao excluir serviço: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage()); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { System.err.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }
}