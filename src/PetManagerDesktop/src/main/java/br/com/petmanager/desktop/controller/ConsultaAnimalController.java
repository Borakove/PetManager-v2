package br.com.petmanager.desktop.controller;

import br.com.petmanager.desktop.visao.telacadastroconsulta.TelaConsultaAnimal;
import br.com.petmanager.modelo.AnimalModelo;
import br.com.petmanager.modelo.dto.Animal;  

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultaAnimalController implements ActionListener {
    private TelaConsultaAnimal telaConsultaAnimal;
    private AnimalModelo animalModelo; 

    public ConsultaAnimalController(TelaConsultaAnimal telaConsultaAnimal) {
        this.telaConsultaAnimal = telaConsultaAnimal;
        this.animalModelo = new AnimalModelo(); 

        this.telaConsultaAnimal.addAtualizarListener(this);

        carregarAnimaisNaTabela();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == telaConsultaAnimal.getBtnAtualizar()) {
            carregarAnimaisNaTabela(); // reload de tabela quando o btn é clicado
            telaConsultaAnimal.exibirMensagem("Lista de animais atualizada.");
        }
    }

    // buscar os animais e encher a tabela
    public void carregarAnimaisNaTabela() {
        List<Animal> animais = animalModelo.listarTodosAnimais(); // busca animais
        telaConsultaAnimal.popularTabela(animais); 
        if (animais.isEmpty()) {
            telaConsultaAnimal.exibirMensagem("Nenhum animal cadastrado.");
        }
    }
}