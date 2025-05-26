package com.mycompany.petmanagerdesktop.controle;

import com.mycompany.petmanagermodelo.dao.TutorDAO;
import com.mycompany.petmanagermodelo.dto.Tutor;
import javax.swing.JOptionPane;

public class TelaCadastroTutor {
    public void salvarTutor(String nome, String telefone, String email, String endereco, String cpf) {
        try {
            Tutor tutor = new Tutor();
            tutor.setNome(nome);
            tutor.setTelefone(telefone);
            tutor.setEmail(email);
            tutor.setEndereco(endereco);
            tutor.setCpf(cpf);

            TutorDAO dao = new TutorDAO();
            dao.inserir(tutor);

            JOptionPane.showMessageDialog(null, "Tutor cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar tutor: " + e.getMessage());
        }
    }
}