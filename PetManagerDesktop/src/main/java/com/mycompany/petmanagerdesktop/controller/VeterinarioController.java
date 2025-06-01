package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagerdesktop.visao.telacadastroeterinario.TelaCadastroVeterinario;

public class VeterinarioController {

    private TelaCadastroVeterinario view;

    public VeterinarioController(TelaCadastroVeterinario view) {
        this.view = view;
    }

    public void salvar() {
        System.out.println("Salvar veterinário...");
    }

    public void atualizar() {
        System.out.println("Atualizar veterinário...");
    }

    public void excluir() {
        System.out.println("Excluir veterinário...");
    }
}
