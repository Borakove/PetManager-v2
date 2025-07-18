package br.com.petmanager.desktop.controller;

import br.com.petmanager.desktop.telaconsultaservico.TelaConsultaServico;
import br.com.petmanager.modelo.ServicoModelo;
import br.com.petmanager.modelo.dto.Servico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultaServicoController implements ActionListener {
    private TelaConsultaServico telaConsultaServico;
    private ServicoModelo servicoModelo; 

    public ConsultaServicoController(TelaConsultaServico telaConsultaServico) {
        this.telaConsultaServico = telaConsultaServico;
        this.servicoModelo = new ServicoModelo();

        this.telaConsultaServico.addAtualizarListener(this);

        carregarServicosNosBlocos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == telaConsultaServico.getBtnAtualizar()) {
            carregarServicosNosBlocos(); 
            telaConsultaServico.exibirMensagem("Lista de serviços atualizada.");
        }
    }

    public void carregarServicosNosBlocos() {
        List<Servico> servicos = servicoModelo.listarTodosServicos(); // busca todos os serviços no Modelo
        telaConsultaServico.popularServicos(servicos); 
    }
}