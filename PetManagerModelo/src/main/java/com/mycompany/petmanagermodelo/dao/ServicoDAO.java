package com.mycompany.petmanagermodel.dao;

import com.mycompany.petmanagermodel.model.Servico;
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


public class ServicoDAO {

    public boolean inserir(Servico servico) {
        String sql = "INSERT INTO servico (nome, descricao, preco, duracaoMinutos) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getPreco());
            stmt.setInt(4, servico.getDuracaoMinutos());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir serviço: " + e.getMessage());
            return false;
        }
    }

    public List<Servico> listarTodos() {
        List<Servico> lista = new ArrayList<>();
        String sql = "SELECT * FROM servico";

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Servico s = new Servico();
                s.setId(rs.getInt("id"));
                s.setNome(rs.getString("nome"));
                s.setDescricao(rs.getString("descricao"));
                s.setPreco(rs.getDouble("preco"));
                s.setDuracaoMinutos(rs.getInt("duracaoMinutos"));
                lista.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar serviços: " + e.getMessage());
        }

        return lista;
    }

    public boolean atualizar(Servico servico) {
        String sql = "UPDATE servico SET nome = ?, descricao = ?, preco = ?, duracaoMinutos = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getPreco());
            stmt.setInt(4, servico.getDuracaoMinutos());
            stmt.setInt(5, servico.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar serviço: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM servico WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir serviço: " + e.getMessage());
            return false;
        }
    }

    public Servico buscarPorId(int id) {
        String sql = "SELECT * FROM servico WHERE id = ?";
        Servico s = null;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                s = new Servico();
                s.setId(rs.getInt("id"));
                s.setNome(rs.getString("nome"));
                s.setDescricao(rs.getString("descricao"));
                s.setPreco(rs.getDouble("preco"));
                s.setDuracaoMinutos(rs.getInt("duracaoMinutos"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviço por ID: " + e.getMessage());
        }

        return s;
    }
}
