package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagerdesktop.visao.telacadastroservico.TelaCadastroServico;

public class ServicoController {

    private TelaCadastroServico view;

    public ServicoController(TelaCadastroServico view) {
        this.view = view;
    }

    public void salvar() {
        System.out.println("Salvar serviço...");
    }

    public void atualizar() {
        System.out.println("Atualizar serviço...");
    }

    public void excluir() {
        System.out.println("Excluir serviço...");
    }
}
