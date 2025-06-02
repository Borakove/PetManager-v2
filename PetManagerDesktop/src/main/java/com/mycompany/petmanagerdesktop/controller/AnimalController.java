package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagermodelo.dao.AnimalDAO;
import com.mycompany.petmanagermodelo.dto.Animal;
import com.mycompany.petmanagerdesktop.visao.telacadastroanimal.TelaCadastroAnimal;

public class AnimalController {
    private final TelaCadastroAnimal tela;

    public AnimalController(TelaCadastroAnimal tela) {
        this.tela = tela;
    }

    public void salvar() {
        Animal dto = new Animal();
        dto.setNome(tela.getTxtNomeAnimal().getText());
        dto.setEspecie(tela.getTxtEspecieAnimal().getText());
        dto.setIdade(Integer.parseInt(tela.getTxtIdIdade().getText()));
        dto.setTemTutor("sim".equals(tela.getCbTutorAnimal().getSelectedItem()));

        new AnimalDAO().salvar(dto);
    }

    public void atualizar() {
        Animal dto = new Animal();
        dto.setId(Integer.parseInt(tela.getTxtIdAnimal().getText()));
        dto.setNome(tela.getTxtNomeAnimal().getText());
        dto.setEspecie(tela.getTxtEspecieAnimal().getText());
        dto.setIdade(Integer.parseInt(tela.getTxtIdIdade().getText()));
        dto.setTemTutor("sim".equals(tela.getCbTutorAnimal().getSelectedItem()));

        new AnimalDAO().atualizar(dto);
    }

    public void excluir() {
        int id = Integer.parseInt(tela.getTxtIdAnimal().getText());
        new AnimalDAO().excluir(id);
    }
}
