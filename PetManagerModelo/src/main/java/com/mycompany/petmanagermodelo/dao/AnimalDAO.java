package com.mycompany.petmanagermodelo.dao;

import com.mycompany.petmanagermodelo.dto.Animal;
import java.sql.*;
import java.util.*;

public class AnimalDAO {
    public void inserir(Animal animal) throws Exception {
        String sql = "INSERT INTO animal (nome, especie, idade, tutor) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setInt(3, animal.getIdade());
            stmt.setString(4, animal.getTutor());
            stmt.executeUpdate();
        }
    }

    public List<Animal> listar() throws Exception {
        String sql = "SELECT * FROM animal";
        List<Animal> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setNome(rs.getString("nome"));
                animal.setEspecie(rs.getString("especie"));
                animal.setIdade(rs.getInt("idade"));
                animal.setTutor(rs.getString("tutor"));
                lista.add(animal);
            }
        }
        return lista;
    }
}
