package com.mycompany.petmanagermodelo.dao;

import com.mycompany.petmanagermodelo.dto.Tutor;
import java.sql.*;
import java.util.*;

public class TutorDAO {
    public void inserir(Tutor tutor) throws Exception {
        String sql = "INSERT INTO tutor (nome, telefone, email, endereco, cpf) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getTelefone());
            stmt.setString(3, tutor.getEmail());
            stmt.setString(4, tutor.getEndereco());
            stmt.setString(5, tutor.getCpf());
            stmt.executeUpdate();
        }
    }

    public List<Tutor> listar() throws Exception {
        String sql = "SELECT * FROM tutor";
        List<Tutor> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tutor tutor = new Tutor();
                tutor.setId(rs.getInt("id"));
                tutor.setNome(rs.getString("nome"));
                tutor.setTelefone(rs.getString("telefone"));
                tutor.setEmail(rs.getString("email"));
                tutor.setEndereco(rs.getString("endereco"));
                tutor.setCpf(rs.getString("cpf"));
                lista.add(tutor);
            }
        }
        return lista;
    }
}
