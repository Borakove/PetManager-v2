package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagerdesktop.visao.TelaConsultaTutor; // <--- Importante!
import com.mycompany.petmanagermodelo.TutorModelo; // Importa o Modelo de Negócios
import com.mycompany.petmanagermodelo.dto.Tutor;   // Importa o DTO Tutor

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultaTutorController implements ActionListener {
    private TelaConsultaTutor telaConsultaTutor;
    private TutorModelo tutorModelo; // Instância do Modelo de Negócios

    public ConsultaTutorController(TelaConsultaTutor telaConsultaTutor) {
        this.telaConsultaTutor = telaConsultaTutor;
        this.tutorModelo = new TutorModelo(); // Instancia o Modelo

        // Registra o controller como listener para o botão de atualização
        this.telaConsultaTutor.addAtualizarListener(this);

        // Carrega os dados na tabela assim que o controller é criado
        carregarTutoresNaTabela();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == telaConsultaTutor.getBtnAtualizar()) {
            carregarTutoresNaTabela(); // Recarrega a tabela quando o botão é clicado
            telaConsultaTutor.exibirMensagem("Lista de tutores atualizada.");
        }
    }

    public void carregarTutoresNaTabela() {
        List<Tutor> tutores = tutorModelo.listarTodosTutores(); // Busca todos os tutores no Modelo
        telaConsultaTutor.popularTabela(tutores); // Passa a lista para a View para popular a tabela
        if (tutores != null && tutores.isEmpty()) { // Verificação para exibir mensagem se não houver tutores
            telaConsultaTutor.exibirMensagem("Nenhum tutor cadastrado.");
        }
    }
}