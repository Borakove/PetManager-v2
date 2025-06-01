package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagerdesktop.visao.telacadastroconsulta.TelaCadastroConsulta;

public class ConsultaController {

    private TelaCadastroConsulta view;

    public ConsultaController(TelaCadastroConsulta view) {
        this.view = view;
    }

    public void salvar() {
        System.out.println("Salvar consulta...");
    }

    public void atualizar() {
        System.out.println("Atualizar consulta...");
    }

    public void excluir() {
        System.out.println("Excluir consulta...");
    }
}
