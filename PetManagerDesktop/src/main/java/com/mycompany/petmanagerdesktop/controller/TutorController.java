package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagerdesktop.controller.TutorController;
import com.mycompany.petmanagerdesktop.visao.telacadastrotutor.TelaCadastroTutor;

public class TutorController {

    private TelaCadastroTutor view;

    public TutorController(TelaCadastroTutor view) {
        this.view = view;
    }

    public void salvar() {
        // Aqui você pega os dados da view e salva
        System.out.println("Salvar tutor...");
        // Exemplo: String nome = view.getTxtNome().getText();
    }

    public void atualizar() {
        System.out.println("Atualizar tutor...");
    }

    public void excluir() {
        System.out.println("Excluir tutor...");
    }
}
