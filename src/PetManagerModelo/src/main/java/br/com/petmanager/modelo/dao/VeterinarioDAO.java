package br.com.petmanager.modelo.dao;

import br.com.petmanager.modelo.dto.Veterinario; // Importa o DTO Veterinario
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/petshop?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root"; 
    private static final String SENHA = ""; 

    public VeterinarioDAO() {
        // construtor não precisa de lógica, a conexão é feita nos métodos crud
    }

    public Veterinario salvar(Veterinario veterinario) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);

            if (veterinario.getId() == 0) { 
                String sql = "INSERT INTO veterinarios (nome, especialidade, telefone, endereco) VALUES (?, ?, ?, ?)";
                pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, veterinario.getNome());
                pstmt.setString(2, veterinario.getEspecialidade());
                pstmt.setString(3, veterinario.getTelefone());
                pstmt.setString(4, veterinario.getEndereco());
                pstmt.executeUpdate();

                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    veterinario.setId(rs.getInt(1)); 
                }
                System.out.println("Veterinário salvo no banco de dados: " + veterinario.getNome() + " (ID: " + veterinario.getId() + ")");
            } else { 
                String sql = "UPDATE veterinarios SET nome = ?, especialidade = ?, telefone = ?, endereco = ? WHERE id = ?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, veterinario.getNome());
                pstmt.setString(2, veterinario.getEspecialidade());
                pstmt.setString(3, veterinario.getTelefone());
                pstmt.setString(4, veterinario.getEndereco());
                pstmt.setInt(5, veterinario.getId());
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Veterinário atualizado no banco de dados: " + veterinario.getNome() + " (ID: " + veterinario.getId() + ")");
                } else {
                    System.err.println("Erro ao atualizar veterinário com ID: " + veterinario.getId() + ". Veterinário não encontrado.");
                    return null;
                }
            }
            return veterinario;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao salvar/atualizar veterinário: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { System.err.println("Erro ao fechar ResultSet: " + e.getMessage()); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage()); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { System.err.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    public Veterinario buscarPorId(int id) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Veterinario veterinario = null;

        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            String sql = "SELECT id, nome, especialidade, telefone, endereco FROM veterinarios WHERE id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                veterinario = new Veterinario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especialidade"),
                        rs.getString("telefone"),
                        rs.getString("endereco")
                );
            }
            return veterinario;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao buscar veterinário por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { System.err.println("Erro ao fechar ResultSet: " + e.getMessage()); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage()); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { System.err.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    public List<Veterinario> listarTodos() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Veterinario> veterinarios = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            String sql = "SELECT id, nome, especialidade, telefone, endereco FROM veterinarios";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Veterinario veterinario = new Veterinario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especialidade"),
                        rs.getString("telefone"),
                        rs.getString("endereco")
                );
                veterinarios.add(veterinario);
            }
            return veterinarios;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao listar todos os veterinários: " + e.getMessage());
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
            String sql = "DELETE FROM veterinarios WHERE id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao excluir veterinário: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage()); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { System.err.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }
}