package com.mycompany.petmanagermodelo.dao;

import com.mycompany.petmanagermodelo.dto.Animal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/petshop?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public AnimalDAO() {
        // retirada de mem temp 
    }

    public Animal salvar(Animal animal) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);

            if (animal.getId() == 0) {
                String sql = "INSERT INTO animais (nome, especie, raca, idade, cor) VALUES (?, ?, ?, ?, ?)";
                pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, animal.getNome());
                pstmt.setString(2, animal.getEspecie());
                pstmt.setString(3, animal.getRaca());
                pstmt.setInt(4, animal.getIdade());
                pstmt.setString(5, animal.getCor());
                pstmt.executeUpdate();

                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    animal.setId(rs.getInt(1)); 
                }
                System.out.println("Animal salvo no banco de dados: " + animal.getNome() + " (ID: " + animal.getId() + ")");
            } else { // Atualizar animal existente
                String sql = "UPDATE animais SET nome = ?, especie = ?, raca = ?, idade = ?, cor = ? WHERE id = ?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, animal.getNome());
                pstmt.setString(2, animal.getEspecie());
                pstmt.setString(3, animal.getRaca());
                pstmt.setInt(4, animal.getIdade());
                pstmt.setString(5, animal.getCor());
                pstmt.setInt(6, animal.getId());
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Animal atualizado no banco de dados: " + animal.getNome() + " (ID: " + animal.getId() + ")");
                } else {
                    System.err.println("Erro ao atualizar animal com ID: " + animal.getId() + ". Animal não encontrado.");
                    return null;
                }
            }
            return animal;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao salvar/atualizar animal: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { System.err.println("Erro ao fechar ResultSet: " + e.getMessage()); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage()); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { System.err.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    public Animal buscarPorId(int id) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Animal animal = null;

        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            String sql = "SELECT id, nome, especie, raca, idade, cor FROM animais WHERE id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                animal = new Animal(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especie"),
                        rs.getString("raca"),
                        rs.getInt("idade"),
                        rs.getString("cor")
                );
            }
            return animal;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao buscar animal por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { System.err.println("Erro ao fechar ResultSet: " + e.getMessage()); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage()); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { System.err.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }

    public List<Animal> listarTodos() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Animal> animais = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            String sql = "SELECT id, nome, especie, raca, idade, cor FROM animais";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql); 

            while (rs.next()) { 
                Animal animal = new Animal(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especie"),
                        rs.getString("raca"),
                        rs.getInt("idade"),
                        rs.getString("cor")
                );
                animais.add(animal);
            }
            return animais;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao listar todos os animais: " + e.getMessage());
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
            String sql = "DELETE FROM animais WHERE id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Erro de SQL ao excluir animal: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage()); }
            try { if (connection != null) connection.close(); } catch (SQLException e) { System.err.println("Erro ao fechar conexão: " + e.getMessage()); }
        }
    }
}