package br.com.petmanager.desktop.controller;

import br.com.petmanager.desktop.visao.telacadastroanimal.TelaCadastroAnimal;
import br.com.petmanager.modelo.AnimalModelo;
import br.com.petmanager.modelo.dto.Animal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CadastroAnimalController implements ActionListener {
    private TelaCadastroAnimal telaCadastroAnimal;
    private AnimalModelo animalModelo; // instancia modelo

    public CadastroAnimalController(TelaCadastroAnimal telaCadastroAnimal) {
        this.telaCadastroAnimal = telaCadastroAnimal;
        this.animalModelo = new AnimalModelo(); // instancia o modelo

        // isso registra o controller como listener para todos os botoes da tela
        this.telaCadastroAnimal.addNovoListener(this);
        this.telaCadastroAnimal.addSalvarListener(this);
        this.telaCadastroAnimal.addEditarListener(this);
        this.telaCadastroAnimal.addExcluirListener(this);
        this.telaCadastroAnimal.addLimparListener(this);
        this.telaCadastroAnimal.addBuscarListener(this);

        //  estado em q starta a tela inicial 
        this.telaCadastroAnimal.limparCampos();
        this.telaCadastroAnimal.habilitarBotoes(true, false, false, false); // nv e lpr ativos
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // verif qual botão foi clicado usando os gettrs da tela cadastro animal
        if (e.getSource() == telaCadastroAnimal.getBtnNovo()) {
            acaoNovo();
        } else if (e.getSource() == telaCadastroAnimal.getBtnSalvar()) {
            acaoSalvar();
        } else if (e.getSource() == telaCadastroAnimal.getBtnEditar()) {
            acaoEditar();
        } else if (e.getSource() == telaCadastroAnimal.getBtnExcluir()) {
            acaoExcluir();
        } else if (e.getSource() == telaCadastroAnimal.getBtnLimpar()) {
            acaoLimpar();
        } else if (e.getSource() == telaCadastroAnimal.getBtnBuscar()) {
            acaoBuscar();
        }
    }

    //  ação p cada botão
    private void acaoNovo() {
        telaCadastroAnimal.limparCampos();
        telaCadastroAnimal.habilitarBotoes(false, true, false, false); // Apenas Salvar ativo
    }

    private void acaoSalvar() {
        // pega os dados da visao
        int id = telaCadastroAnimal.getIdAnimal();
        String nome = telaCadastroAnimal.getNomeAnimal();
        String especie = telaCadastroAnimal.getEspecieAnimal();
        String raca = telaCadastroAnimal.getRacaAnimal();
        int idade = telaCadastroAnimal.getIdadeAnimal();
        String cor = telaCadastroAnimal.getCorAnimal();

        // validação dos campos da tela (preciso att futuramente p ser mais robusta)
        if (nome.trim().isEmpty() || especie.trim().isEmpty() || raca.trim().isEmpty() || idade <= 0) {
            telaCadastroAnimal.exibirMensagem("Por favor, preencha todos os campos obrigatórios (Nome, Espécie, Raça, Idade) corretamente.");
            return;
        }

        // obj animal dto 
        Animal animal;
        if (id == 0) { // aq vai o novo animal 
            animal = new Animal(nome, especie, raca, idade, cor);
        } else { // aq fica oo animal disponivel p att
            animal = new Animal(id, nome, especie, raca, idade, cor);
        }

        // modelo para salvar
        Animal animalSalvo = animalModelo.salvarAnimal(animal);

        if (animalSalvo != null) {
            telaCadastroAnimal.exibirMensagem("Animal salvo com sucesso! ID: " + animalSalvo.getId());
            telaCadastroAnimal.setDadosAnimal(animalSalvo); // Atualiza a tela com o ID gerado (se for novo)
        } else {
            telaCadastroAnimal.exibirMensagem("Erro ao salvar animal. Verifique os dados.");
        }
    }

    private void acaoEditar() {
        // id na tela = foi buscado ou cadastrado
        if (telaCadastroAnimal.getIdAnimal() != 0) {
            telaCadastroAnimal.habilitarBotoes(false, true, false, false); // apertar salvar para fazer as mudnaças
            telaCadastroAnimal.exibirMensagem("Agora você pode editar os campos e clicar em Salvar.");
        } else {
            telaCadastroAnimal.exibirMensagem("Nenhum animal selecionado para edição. Busque um animal primeiro.");
        }
    }

    private void acaoExcluir() {
        int id = telaCadastroAnimal.getIdAnimal();
        if (id == 0) {
            telaCadastroAnimal.exibirMensagem("Nenhum animal selecionado para exclusão.");
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(telaCadastroAnimal,
                "Tem certeza que deseja excluir o animal com ID " + id + "?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            if (animalModelo.excluirAnimal(id)) {
                telaCadastroAnimal.exibirMensagem("Animal excluído com sucesso!");
                telaCadastroAnimal.limparCampos();
                telaCadastroAnimal.habilitarBotoes(true, false, false, false); // estado inicial (nv, lmpr e atvs)
            } else {
                telaCadastroAnimal.exibirMensagem("Erro ao excluir animal. Ele pode não existir ou há uma restrição.");
            }
        }
    }

    private void acaoLimpar() {
        telaCadastroAnimal.limparCampos();
        telaCadastroAnimal.habilitarBotoes(true, false, false, false); 
    }

    private void acaoBuscar() {
        String idStr = telaCadastroAnimal.pedirIdParaBusca();
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Animal animalEncontrado = animalModelo.buscarAnimalPorId(id);
                if (animalEncontrado != null) {
                    telaCadastroAnimal.setDadosAnimal(animalEncontrado); 
                    telaCadastroAnimal.exibirMensagem("Animal encontrado: " + animalEncontrado.getNome());
                    telaCadastroAnimal.habilitarBotoes(true, true, true, true); // ativa os bts de novo
                } else {
                    telaCadastroAnimal.exibirMensagem("Animal com ID " + id + " não encontrado.");
                    telaCadastroAnimal.limparCampos();
                    telaCadastroAnimal.habilitarBotoes(true, false, false, false); 
                }
            } catch (NumberFormatException e) {
                telaCadastroAnimal.exibirMensagem("Por favor, digite um ID numérico válido para a busca.");
            }
        } else {
            telaCadastroAnimal.exibirMensagem("ID de busca não pode ser vazio.");
        }
    }
}