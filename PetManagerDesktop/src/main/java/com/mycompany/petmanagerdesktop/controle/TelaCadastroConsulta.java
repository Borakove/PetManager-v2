package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagermodelo.dao.ConsultaDAO;
import com.mycompany.petmanagermodelo.dto.Consulta;
import javax.swing.JOptionPane;

public class TelaCadastroConsulta {
    public void salvarConsulta(String dataConsulta, String animal, String veterinario, String servico) {
        try {
            Consulta consulta = new Consulta();
            consulta.setDataConsulta(dataConsulta);
            consulta.setAnimal(animal);
            consulta.setVeterinario(veterinario);
            consulta.setServico(servico);

            ConsultaDAO dao = new ConsultaDAO();
            dao.inserir(consulta);

            JOptionPane.showMessageDialog(null, "Consulta cadastrada com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar consulta: " + e.getMessage());
        }
    }
}
