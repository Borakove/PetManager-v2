package com.mycompany.petmanagerdesktop.controller;

// <--- Importa a TelaCadastroTutor, que é a View que este Controller gerencia
import com.mycompany.petmanagerdesktop.visao.TelaCadastroTutor;
import com.mycompany.petmanagermodelo.TutorModelo; // Importa o Modelo de Negócios
import com.mycompany.petmanagermodelo.dto.Tutor;   // Importa o DTO Tutor

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane; // Para exibir mensagens

public class CadastroTutorController implements ActionListener {
    private TelaCadastroTutor telaCadastroTutor;
    private TutorModelo tutorModelo; // Instância do Modelo de Negócios

    public CadastroTutorController(TelaCadastroTutor telaCadastroTutor) {
        this.telaCadastroTutor = telaCadastroTutor;
        this.tutorModelo = new TutorModelo(); // Instancia o Modelo

        // Registra o controller como listener para todos os botões da tela
        this.telaCadastroTutor.addNovoListener(this);
        this.telaCadastroTutor.addSalvarListener(this);
        this.telaCadastroTutor.addEditarListener(this);
        this.telaCadastroTutor.addExcluirListener(this);
        this.telaCadastroTutor.addLimparListener(this);
        this.telaCadastroTutor.addBuscarListener(this);

        // Define o estado inicial da tela
        this.telaCadastroTutor.limparCampos();
        this.telaCadastroTutor.habilitarBotoes(true, false, false, false); // Novo e Limpar ativos
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Verifica qual botão foi clicado usando os getters da TelaCadastroTutor
        if (e.getSource() == telaCadastroTutor.getBtnNovo()) {
            acaoNovo();
        } else if (e.getSource() == telaCadastroTutor.getBtnSalvar()) {
            acaoSalvar();
        } else if (e.getSource() == telaCadastroTutor.getBtnEditar()) {
            acaoEditar();
        } else if (e.getSource() == telaCadastroTutor.getBtnExcluir()) {
            acaoExcluir();
        } else if (e.getSource() == telaCadastroTutor.getBtnLimpar()) {
            acaoLimpar();
        } else if (e.getSource() == telaCadastroTutor.getBtnBuscar()) {
            acaoBuscar();
        }
    }

    // Métodos de ação para cada botão
    private void acaoNovo() {
        telaCadastroTutor.limparCampos();
        telaCadastroTutor.habilitarBotoes(false, true, false, false); // Apenas Salvar ativo
    }

    private void acaoSalvar() {
        // Coleta os dados da View
        int id = telaCadastroTutor.getIdTutor();
        String nome = telaCadastroTutor.getNomeTutor();
        String telefone = telaCadastroTutor.getTelefoneTutor();
        String endereco = telaCadastroTutor.getEnderecoTutor();

        // Validação básica dos campos da tela (pode ser mais robusta)
        if (nome.trim().isEmpty()) {
            telaCadastroTutor.exibirMensagem("O nome do tutor não pode ser vazio.");
            return;
        }

        // Cria o objeto Tutor (DTO)
        Tutor tutor;
        if (id == 0) { // Novo tutor
            tutor = new Tutor(nome, telefone, endereco);
        } else { // Tutor existente para atualização
            tutor = new Tutor(id, nome, telefone, endereco);
        }

        // Delega ao Modelo para salvar
        Tutor tutorSalvo = tutorModelo.salvarTutor(tutor);

        if (tutorSalvo != null) {
            telaCadastroTutor.exibirMensagem("Tutor salvo com sucesso! ID: " + tutorSalvo.getId());
            telaCadastroTutor.setDadosTutor(tutorSalvo); // Atualiza a tela com o ID gerado (se for novo)
        } else {
            telaCadastroTutor.exibirMensagem("Erro ao salvar tutor. Verifique os dados.");
        }
    }

    private void acaoEditar() {
        // Se há um ID na tela, significa que um tutor foi buscado/selecionado
        if (telaCadastroTutor.getIdTutor() != 0) {
            telaCadastroTutor.habilitarBotoes(false, true, false, false); // Habilita o Salvar para fazer as alterações
            telaCadastroTutor.exibirMensagem("Agora você pode editar os campos e clicar em Salvar.");
        } else {
            telaCadastroTutor.exibirMensagem("Nenhum tutor selecionado para edição. Busque um tutor primeiro.");
        }
    }

    private void acaoExcluir() {
        int id = telaCadastroTutor.getIdTutor();
        if (id == 0) {
            telaCadastroTutor.exibirMensagem("Nenhum tutor selecionado para exclusão.");
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(telaCadastroTutor,
                "Tem certeza que deseja excluir o tutor com ID " + id + "?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            if (tutorModelo.excluirTutor(id)) {
                telaCadastroTutor.exibirMensagem("Tutor excluído com sucesso!");
                telaCadastroTutor.limparCampos();
                telaCadastroTutor.habilitarBotoes(true, false, false, false); // Volta para estado inicial (Novo, Limpar ativos)
            } else {
                telaCadastroTutor.exibirMensagem("Erro ao excluir tutor. Ele pode não existir ou há uma restrição.");
            }
        }
    }

    private void acaoLimpar() {
        telaCadastroTutor.limparCampos();
        telaCadastroTutor.habilitarBotoes(true, false, false, false); // Novo e Limpar ativos
    }

    private void acaoBuscar() {
        String idStr = telaCadastroTutor.pedirIdParaBusca();
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Tutor tutorEncontrado = tutorModelo.buscarTutorPorId(id);
                if (tutorEncontrado != null) {
                    telaCadastroTutor.setDadosTutor(tutorEncontrado); // Preenche a tela com os dados
                    telaCadastroTutor.exibirMensagem("Tutor encontrado: " + tutorEncontrado.getNome());
                    telaCadastroTutor.habilitarBotoes(true, true, true, true); // Ativa Salvar, Editar, Excluir, Novo
                } else {
                    telaCadastroTutor.exibirMensagem("Tutor com ID " + id + " não encontrado.");
                    telaCadastroTutor.limparCampos();
                    telaCadastroTutor.habilitarBotoes(true, false, false, false); // Volta para estado inicial
                }
            } catch (NumberFormatException e) {
                telaCadastroTutor.exibirMensagem("Por favor, digite um ID numérico válido para a busca.");
            }
        } else {
            telaCadastroTutor.exibirMensagem("ID de busca não pode ser vazio.");
        }
    }
}