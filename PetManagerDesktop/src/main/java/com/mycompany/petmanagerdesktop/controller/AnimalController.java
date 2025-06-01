package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagerdesktop.visao.telacadastroanimal.TelaCadastroAnimal;

public class AnimalController {

    private TelaCadastroAnimal view;

    public AnimalController(TelaCadastroAnimal view) {
        this.view = view;
    }

    public void salvar() {
        System.out.println("Salvar animal...");
    }

    public void atualizar() {
        System.out.println("Atualizar animal...");
    }

    public void excluir() {
        System.out.println("Excluir animal...");
    }
}
