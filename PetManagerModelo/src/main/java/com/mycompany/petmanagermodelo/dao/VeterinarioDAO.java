package com.mycompany.petmanagermodel.dao;

import com.mycompany.petmanagermodel.model.Veterinario;
import com.mycompany.petmanagermodelo.dao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {

    public boolean inserir(Veterinario veterinario) {
        String sql = "INSERT INTO veterinario (nome, crmv, telefone, especialidade) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veterinario.getNome());
            stmt.setString(2, veterinario.getCrmv());
            stmt.setString(3, veterinario.getTelefone());
            stmt.setString(4, veterinario.getEspecialidade());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir veterinário: " + e.getMessage());
            return false;
        }
    }

    public List<Veterinario> listarTodos() {
        List<Veterinario> lista = new ArrayList<>();
        String sql = "SELECT * FROM veterinario";

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Veterinario v = new Veterinario();
                v.setId(rs.getInt("id"));
                v.setNome(rs.getString("nome"));
                v.setCrmv(rs.getString("crmv"));
                v.setTelefone(rs.getString("telefone"));
                v.setEspecialidade(rs.getString("especialidade"));
                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar veterinários: " + e.getMessage());
        }

        return lista;
    }

    public boolean atualizar(Veterinario veterinario) {
        String sql = "UPDATE veterinario SET nome = ?, crmv = ?, telefone = ?, especialidade = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veterinario.getNome());
            stmt.setString(2, veterinario.getCrmv());
            stmt.setString(3, veterinario.getTelefone());
            stmt.setString(4, veterinario.getEspecialidade());
            stmt.setInt(5, veterinario.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar veterinário: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM veterinario WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir veterinário: " + e.getMessage());
            return false;
        }
    }

    public Veterinario buscarPorId(int id) {
        String sql = "SELECT * FROM veterinario WHERE id = ?";
        Veterinario v = null;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                v = new Veterinario();
                v.setId(rs.getInt("id"));
                v.setNome(rs.getString("nome"));
                v.setCrmv(rs.getString("crmv"));
                v.setTelefone(rs.getString("telefone"));
                v.setEspecialidade(rs.getString("especialidade"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar veterinário por ID: " + e.getMessage());
        }

        return v;
    }
}
