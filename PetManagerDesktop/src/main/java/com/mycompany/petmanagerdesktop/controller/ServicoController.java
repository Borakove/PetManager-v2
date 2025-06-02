package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagermodelo.dto.Servico;
import com.mycompany.petmanagermodelo.dao.ServicoDAO;
import com.mycompany.petmanagerdesktop.visao.telacadastroservico.TelaCadastroServico;

public class ServicoController {
    private final TelaCadastroServico tela;
    private final ServicoDAO dao = new ServicoDAO();

    public ServicoController(TelaCadastroServico tela) {
        this.tela = tela;
    }

    public void salvar() {
    Servico dto = new Servico();
    dto.setDescricao(tela.getTxtNomeServico().getText());
    dto.setPreco(Double.parseDouble(tela.getTxtPrecoServico().getText()));
    new ServicoDAO().salvar(dto);
}

public void atualizar() {
    Servico dto = new Servico();
    dto.setDescricao(tela.getTxtNomeServico().getText());
    dto.setPreco(Double.parseDouble(tela.getTxtPrecoServico().getText()));
    new ServicoDAO().atualizar(dto);
}

public void excluir() {
    tela.getTxtNomeServico().setText("");
    tela.getTxtPrecoServico().setText("");
}

public void voltar() {
    tela.dispose();
    
    }
}
