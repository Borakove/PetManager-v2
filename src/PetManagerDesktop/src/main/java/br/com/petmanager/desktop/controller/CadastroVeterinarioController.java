package br.com.petmanager.desktop.controller;

import br.com.petmanager.desktop.visao.telacadastroveterinario.TelaCadastroVeterinario;
import br.com.petmanager.modelo.VeterinarioModelo; // Importa o Modelo de Negócios
import br.com.petmanager.modelo.dto.Veterinario;   // Importa o DTO Veterinario

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane; // Para exibir mensagens

public class CadastroVeterinarioController implements ActionListener {
    private TelaCadastroVeterinario telaCadastroVeterinario;
    private VeterinarioModelo veterinarioModelo; // Instância do Modelo de Negócios

    public CadastroVeterinarioController(TelaCadastroVeterinario telaCadastroVeterinario) {
        this.telaCadastroVeterinario = telaCadastroVeterinario;
        this.veterinarioModelo = new VeterinarioModelo(); // Instancia o Modelo

        // Registra o controller como listener para todos os botões da tela
        this.telaCadastroVeterinario.addNovoListener(this);
        this.telaCadastroVeterinario.addSalvarListener(this);
        this.telaCadastroVeterinario.addEditarListener(this);
        this.telaCadastroVeterinario.addExcluirListener(this);
        this.telaCadastroVeterinario.addLimparListener(this);
        this.telaCadastroVeterinario.addBuscarListener(this);

        // Define o estado inicial da tela
        this.telaCadastroVeterinario.limparCampos();
        this.telaCadastroVeterinario.habilitarBotoes(true, false, false, false); // Novo e Limpar ativos
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Verifica qual botão foi clicado usando os getters da TelaCadastroVeterinario
        if (e.getSource() == telaCadastroVeterinario.getBtnNovo()) {
            acaoNovo();
        } else if (e.getSource() == telaCadastroVeterinario.getBtnSalvar()) {
            acaoSalvar();
        } else if (e.getSource() == telaCadastroVeterinario.getBtnEditar()) {
            acaoEditar();
        } else if (e.getSource() == telaCadastroVeterinario.getBtnExcluir()) {
            acaoExcluir();
        } else if (e.getSource() == telaCadastroVeterinario.getBtnLimpar()) {
            acaoLimpar();
        } else if (e.getSource() == telaCadastroVeterinario.getBtnBuscar()) {
            acaoBuscar();
        }
    }

    // Métodos de ação para cada botão
    private void acaoNovo() {
        telaCadastroVeterinario.limparCampos();
        telaCadastroVeterinario.habilitarBotoes(false, true, false, false); // Apenas Salvar ativo
    }

    private void acaoSalvar() {
        // Coleta os dados da View
        int id = telaCadastroVeterinario.getIdVeterinario();
        String nome = telaCadastroVeterinario.getNomeVeterinario();
        String especialidade = telaCadastroVeterinario.getEspecialidadeVeterinario();
        String telefone = telaCadastroVeterinario.getTelefoneVeterinario();
        String endereco = telaCadastroVeterinario.getEnderecoVeterinario();

        // Validação básica dos campos da tela (pode ser mais robusta)
        if (nome.trim().isEmpty() || especialidade.trim().isEmpty()) { // Nome e Especialidade são obrigatórios
            telaCadastroVeterinario.exibirMensagem("O nome e a especialidade do veterinário não podem ser vazios.");
            return;
        }

        // Cria o objeto Veterinario (DTO)
        Veterinario veterinario;
        if (id == 0) { // Novo veterinário
            veterinario = new Veterinario(nome, especialidade, telefone, endereco);
        } else { // Veterinário existente para atualização
            veterinario = new Veterinario(id, nome, especialidade, telefone, endereco);
        }

        // Delega ao Modelo para salvar
        Veterinario veterinarioSalvo = veterinarioModelo.salvarVeterinario(veterinario);

        if (veterinarioSalvo != null) {
            telaCadastroVeterinario.exibirMensagem("Veterinário salvo com sucesso! ID: " + veterinarioSalvo.getId());
            telaCadastroVeterinario.setDadosVeterinario(veterinarioSalvo); // Atualiza a tela com o ID gerado (se for novo)
        } else {
            telaCadastroVeterinario.exibirMensagem("Erro ao salvar veterinário. Verifique os dados.");
        }
    }

    private void acaoEditar() {
        // Se há um ID na tela, significa que um veterinário foi buscado/selecionado
        if (telaCadastroVeterinario.getIdVeterinario() != 0) {
            telaCadastroVeterinario.habilitarBotoes(false, true, false, false); // Habilita o Salvar para fazer as alterações
            telaCadastroVeterinario.exibirMensagem("Agora você pode editar os campos e clicar em Salvar.");
        } else {
            telaCadastroVeterinario.exibirMensagem("Nenhum veterinário selecionado para edição. Busque um veterinário primeiro.");
        }
    }

    private void acaoExcluir() {
        int id = telaCadastroVeterinario.getIdVeterinario();
        if (id == 0) {
            telaCadastroVeterinario.exibirMensagem("Nenhum veterinário selecionado para exclusão.");
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(telaCadastroVeterinario,
                "Tem certeza que deseja excluir o veterinário com ID " + id + "?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            if (veterinarioModelo.excluirVeterinario(id)) {
                telaCadastroVeterinario.exibirMensagem("Veterinário excluído com sucesso!");
                telaCadastroVeterinario.limparCampos();
                telaCadastroVeterinario.habilitarBotoes(true, false, false, false); // Volta para estado inicial (Novo, Limpar ativos)
            } else {
                telaCadastroVeterinario.exibirMensagem("Erro ao excluir veterinário. Ele pode não existir ou há uma restrição.");
            }
        }
    }

    private void acaoLimpar() {
        telaCadastroVeterinario.limparCampos();
        telaCadastroVeterinario.habilitarBotoes(true, false, false, false); // Novo e Limpar ativos
    }

    private void acaoBuscar() {
        String idStr = telaCadastroVeterinario.pedirIdParaBusca();
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Veterinario veterinarioEncontrado = veterinarioModelo.buscarVeterinarioPorId(id);
                if (veterinarioEncontrado != null) {
                    telaCadastroVeterinario.setDadosVeterinario(veterinarioEncontrado); // Preenche a tela com os dados
                    telaCadastroVeterinario.exibirMensagem("Veterinário encontrado: " + veterinarioEncontrado.getNome());
                    telaCadastroVeterinario.habilitarBotoes(true, true, true, true); // Ativa Salvar, Editar, Excluir, Novo
                } else {
                    telaCadastroVeterinario.exibirMensagem("Veterinário com ID " + id + " não encontrado.");
                    telaCadastroVeterinario.limparCampos();
                    telaCadastroVeterinario.habilitarBotoes(true, false, false, false); // Volta para estado inicial
                }
            } catch (NumberFormatException e) {
                telaCadastroVeterinario.exibirMensagem("Por favor, digite um ID numérico válido para a busca.");
            }
        } else {
            telaCadastroVeterinario.exibirMensagem("ID de busca não pode ser vazio.");
        }
    }
}