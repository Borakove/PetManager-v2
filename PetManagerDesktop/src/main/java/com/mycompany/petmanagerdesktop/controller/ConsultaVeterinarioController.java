package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagerdesktop.visao.TelaConsultaVeterinario;
import com.mycompany.petmanagermodelo.VeterinarioModelo; // Importa o Modelo de Negócios
import com.mycompany.petmanagermodelo.dto.Veterinario;   // Importa o DTO Veterinario

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultaVeterinarioController implements ActionListener {
    private TelaConsultaVeterinario telaConsultaVeterinario;
    private VeterinarioModelo veterinarioModelo; // Instância do Modelo de Negócios

    public ConsultaVeterinarioController(TelaConsultaVeterinario telaConsultaVeterinario) {
        this.telaConsultaVeterinario = telaConsultaVeterinario;
        this.veterinarioModelo = new VeterinarioModelo(); // Instancia o Modelo

        // Registra o controller como listener para o botão de atualização
        this.telaConsultaVeterinario.addAtualizarListener(this);

        // Carrega os dados na tabela assim que o controller é criado
        carregarVeterinariosNaTabela();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == telaConsultaVeterinario.getBtnAtualizar()) {
            carregarVeterinariosNaTabela(); // Recarrega a tabela quando o botão é clicado
            telaConsultaVeterinario.exibirMensagem("Lista de veterinários atualizada.");
        }
    }

    public void carregarVeterinariosNaTabela() {
        List<Veterinario> veterinarios = veterinarioModelo.listarTodosVeterinarios(); // Busca todos os veterinários no Modelo
        telaConsultaVeterinario.popularTabela(veterinarios); // Passa a lista para a View para popular a tabela
        if (veterinarios != null && veterinarios.isEmpty()) { // Verificação para exibir mensagem se não houver veterinários
            telaConsultaVeterinario.exibirMensagem("Nenhum veterinário cadastrado.");
        }
    }
}