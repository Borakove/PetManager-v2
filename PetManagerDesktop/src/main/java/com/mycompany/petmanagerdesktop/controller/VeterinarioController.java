package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagermodelo.dao.VeterinarioDAO;
import com.mycompany.petmanagermodelo.dto.Veterinario;
import com.mycompany.petmanagerdesktop.visao.telacadastroeterinario.TelaCadastroVeterinario;

import javax.swing.JOptionPane;

public class VeterinarioController {
    private final TelaCadastroVeterinario tela;

    public VeterinarioController(TelaCadastroVeterinario tela) {
        this.tela = tela;
    }

    public void salvar() {
        Veterinario dto = new Veterinario();
        dto.setNome(tela.getTxtNomeVet().getText());
        dto.setCrmv(tela.getTxtCRMV().getText());

        new VeterinarioDAO().salvar(dto);
        JOptionPane.showMessageDialog(null, "Veterinário salvo com sucesso!");
    }

    public void atualizar() {
        Veterinario dto = new Veterinario();
        dto.setNome(tela.getTxtNomeVet().getText());
        dto.setCrmv(tela.getTxtCRMV().getText());

        new VeterinarioDAO().atualizarPorCrmv(dto);
        JOptionPane.showMessageDialog(null, "Veterinário atualizado com sucesso!");
    }

    public void excluir() {
        String crmv = tela.getTxtCRMV().getText();

        new VeterinarioDAO().excluirPorCrmv(crmv);
        JOptionPane.showMessageDialog(null, "Veterinário excluído com sucesso!");
    }

    public void voltar() {
        tela.dispose();
    }
}
