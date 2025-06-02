package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagerdesktop.visao.telacadastrotutor.TelaCadastroTutor;
import com.mycompany.petmanagermodelo.dao.TutorDAO;
import com.mycompany.petmanagermodelo.dto.Tutor;

public class TutorController {
    private final TelaCadastroTutor view;

    public TutorController(TelaCadastroTutor view) {
        this.view = view;
    }

    public void salvarTutor() throws Exception {
  
        String nome = view.getTxtNomeTutor().getText();
        String telefone = view.getTxtTelefoneTutor().getText();
        String email = view.getTxtEmailTutor().getText();
        String endereco = view.getTxtEnderecoTutor().getText();
        String cpf = view.getTxtCpfTutor().getText();

        Tutor tutor = new Tutor();
        tutor.setNome(nome);
        tutor.setTelefone(telefone);
        tutor.setEmail(email);
        tutor.setEndereco(endereco);
        tutor.setCpf(cpf);

        TutorDAO tutorDAO = new TutorDAO();
        tutorDAO.inserir(tutor);
    }
}
