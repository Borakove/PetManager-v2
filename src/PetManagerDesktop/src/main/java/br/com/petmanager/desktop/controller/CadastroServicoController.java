package br.com.petmanager.desktop.controller;

import br.com.petmanager.desktop.visao.telacadastroservico.TelaCadastroServico;
import br.com.petmanager.modelo.ServicoModelo;
import br.com.petmanager.modelo.dto.Servico;   

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane; 

public class CadastroServicoController implements ActionListener {
    private TelaCadastroServico telaCadastroServico;
    private ServicoModelo servicoModelo; 

    public CadastroServicoController(TelaCadastroServico telaCadastroServico) {
        this.telaCadastroServico = telaCadastroServico;
        this.servicoModelo = new ServicoModelo(); 
        this.telaCadastroServico.addNovoListener(this);
        this.telaCadastroServico.addSalvarListener(this);
        this.telaCadastroServico.addEditarListener(this);
        this.telaCadastroServico.addExcluirListener(this);
        this.telaCadastroServico.addLimparListener(this);
        this.telaCadastroServico.addBuscarListener(this);
        this.telaCadastroServico.limparCampos();
        this.telaCadastroServico.habilitarBotoes(true, false, false, false); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == telaCadastroServico.getBtnNovo()) {
            acaoNovo();
        } else if (e.getSource() == telaCadastroServico.getBtnSalvar()) {
            acaoSalvar();
        } else if (e.getSource() == telaCadastroServico.getBtnEditar()) {
            acaoEditar();
        } else if (e.getSource() == telaCadastroServico.getBtnExcluir()) {
            acaoExcluir();
        } else if (e.getSource() == telaCadastroServico.getBtnLimpar()) {
            acaoLimpar();
        } else if (e.getSource() == telaCadastroServico.getBtnBuscar()) {
            acaoBuscar();
        }
    }

    private void acaoNovo() {
        telaCadastroServico.limparCampos();
        telaCadastroServico.habilitarBotoes(false, true, false, false); 
    }

    private void acaoSalvar() {
        int id = telaCadastroServico.getIdServico();
        String descricao = telaCadastroServico.getDescricaoServico();
        double valor = telaCadastroServico.getValorServico();
        if (descricao.trim().isEmpty() || valor <= 0) {
            telaCadastroServico.exibirMensagem("A descrição e o valor do serviço não podem ser vazios/negativos.");
            return;
        }

        Servico servico;
        if (id == 0) { 
            servico = new Servico(descricao, valor);
        } else { 
            servico = new Servico(id, descricao, valor);
        }
        
        Servico servicoSalvo = servicoModelo.salvarServico(servico);

        if (servicoSalvo != null) {
            telaCadastroServico.exibirMensagem("Serviço salvo com sucesso! ID: " + servicoSalvo.getId());
            telaCadastroServico.setDadosServico(servicoSalvo); 
        } else {
            telaCadastroServico.exibirMensagem("Erro ao salvar serviço. Verifique os dados.");
        }
    }

    private void acaoEditar() {
        if (telaCadastroServico.getIdServico() != 0) {
            telaCadastroServico.habilitarBotoes(false, true, false, false); 
            telaCadastroServico.exibirMensagem("Agora você pode editar os campos e clicar em Salvar.");
        } else {
            telaCadastroServico.exibirMensagem("Nenhum serviço selecionado para edição. Busque um serviço primeiro.");
        }
    }

    private void acaoExcluir() {
        int id = telaCadastroServico.getIdServico();
        if (id == 0) {
            telaCadastroServico.exibirMensagem("Nenhum serviço selecionado para exclusão.");
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(telaCadastroServico,
                "Tem certeza que deseja excluir o serviço com ID " + id + "?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            if (servicoModelo.excluirServico(id)) {
                telaCadastroServico.exibirMensagem("Serviço excluído com sucesso!");
                telaCadastroServico.limparCampos();
                telaCadastroServico.habilitarBotoes(true, false, false, false); 
            } else {
                telaCadastroServico.exibirMensagem("Erro ao excluir serviço. Ele pode não existir ou há uma restrição.");
            }
        }
    }

    private void acaoLimpar() {
        telaCadastroServico.limparCampos();
        telaCadastroServico.habilitarBotoes(true, false, false, false); 
    }

    private void acaoBuscar() {
        String idStr = telaCadastroServico.pedirIdParaBusca();
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Servico servicoEncontrado = servicoModelo.buscarServicoPorId(id);
                if (servicoEncontrado != null) {
                    telaCadastroServico.setDadosServico(servicoEncontrado); 
                    telaCadastroServico.exibirMensagem("Serviço encontrado: " + servicoEncontrado.getDescricao());
                    telaCadastroServico.habilitarBotoes(true, true, true, true); 
                } else {
                    telaCadastroServico.exibirMensagem("Serviço com ID " + id + " não encontrado.");
                    telaCadastroServico.limparCampos();
                    telaCadastroServico.habilitarBotoes(true, false, false, false); 
                }
            } catch (NumberFormatException e) {
                telaCadastroServico.exibirMensagem("Por favor, digite um ID numérico válido para a busca.");
            }
        } else {
            telaCadastroServico.exibirMensagem("ID de busca não pode ser vazio.");
        }
    }
}