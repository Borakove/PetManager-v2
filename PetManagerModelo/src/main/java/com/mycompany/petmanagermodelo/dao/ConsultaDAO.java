package com.mycompany.petmanagermodelo.dao;

import com.mycompany.petmanagermodelo.dto.Consulta;
import java.sql.*;
import java.util.*;

public class ConsultaDAO {
    public void inserir(Consulta consulta) throws Exception {
        String sql = "INSERT INTO consulta (data_consulta, animal, veterinario, servico) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, consulta.getDataConsulta());
            stmt.setString(2, consulta.getAnimal());
            stmt.setString(3, consulta.getVeterinario());
            stmt.setString(4, consulta.getServico());
            stmt.executeUpdate();
        }
    }

    public List<Consulta> listar() throws Exception {
        String sql = "SELECT * FROM consulta";
        List<Consulta> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));
                consulta.setDataConsulta(rs.getString("data_consulta"));
                consulta.setAnimal(rs.getString("animal"));
                consulta.setVeterinario(rs.getString("veterinario"));
                consulta.setServico(rs.getString("servico"));
                lista.add(consulta);
            }
        }
        return lista;
    }
}
