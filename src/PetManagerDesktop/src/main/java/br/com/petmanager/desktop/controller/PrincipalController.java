package br.com.petmanager.desktop.controller;

import br.com.petmanager.desktop.visao.telaprincipal.TelaPrincipal;
import br.com.petmanager.desktop.visao.telacadastroanimal.TelaCadastroAnimal;
import br.com.petmanager.desktop.visao.telacadastroconsulta.TelaConsultaAnimal;
import br.com.petmanager.desktop.visao.telacadastrotutor.TelaCadastroTutor;
import br.com.petmanager.desktop.telaconsultatutor.TelaConsultaTutor;
import br.com.petmanager.desktop.visao.telacadastroservico.TelaCadastroServico;
import br.com.petmanager.desktop.telaconsultaservico.TelaConsultaServico;
import br.com.petmanager.desktop.visao.telacadastroveterinario.TelaCadastroVeterinario;
import br.com.petmanager.desktop.controller.telaconsultaveterinario.TelaConsultaVeterinario;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalController implements ActionListener {
    private TelaPrincipal telaPrincipal;

    public PrincipalController(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
        this.telaPrincipal.addCadastrarAnimalListener(this);
        this.telaPrincipal.addConsultarAnimalListener(this);
        this.telaPrincipal.addCadastrarServicoListener(this);    
        this.telaPrincipal.addConsultarServicoListener(this);     
        this.telaPrincipal.addCadastrarTutorListener(this);
        this.telaPrincipal.addConsultarTutorListener(this);
        this.telaPrincipal.addCadastrarVeterinarioListener(this);
        this.telaPrincipal.addConsultarVeterinarioListener(this);
        this.telaPrincipal.addSairListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Evento de menu recebido. Fonte: " + e.getSource());

        if (e.getSource() == telaPrincipal.getMenuItemCadastrarAnimal()) {
            System.out.println("Clicou em Cadastrar Animal.");
            TelaCadastroAnimal telaCadastroAnimal = new TelaCadastroAnimal();
            CadastroAnimalController cadastroAnimalController = new CadastroAnimalController(telaCadastroAnimal);
            telaCadastroAnimal.setVisible(true);

        } else if (e.getSource() == telaPrincipal.getMenuItemConsultarAnimal()) {
            System.out.println("Clicou em Consultar Animal.");
            TelaConsultaAnimal telaConsultaAnimal = new TelaConsultaAnimal();
            ConsultaAnimalController consultaAnimalController = new ConsultaAnimalController(telaConsultaAnimal);
            telaConsultaAnimal.setVisible(true);

        } else if (e.getSource() == telaPrincipal.getMenuItemCadastrarTutor()) {
            System.out.println("Clicou em Cadastrar Tutor.");
            TelaCadastroTutor telaCadastroTutor = new TelaCadastroTutor();
            CadastroTutorController cadastroTutorController = new CadastroTutorController(telaCadastroTutor);
            telaCadastroTutor.setVisible(true);

        } else if (e.getSource() == telaPrincipal.getMenuItemConsultarTutor()) {
            System.out.println("Clicou em Consultar Tutor.");
            TelaConsultaTutor telaConsultaTutor = new TelaConsultaTutor();
            ConsultaTutorController consultaTutorController = new ConsultaTutorController(telaConsultaTutor);
            telaConsultaTutor.setVisible(true);

        } else if (e.getSource() == telaPrincipal.getMenuItemCadastrarVeterinario()) {
            System.out.println("Clicou em Cadastrar Veterinário.");
            TelaCadastroVeterinario telaCadastroVeterinario = new TelaCadastroVeterinario();
            CadastroVeterinarioController cadastroVeterinarioController = new CadastroVeterinarioController(telaCadastroVeterinario);
            telaCadastroVeterinario.setVisible(true);

        } else if (e.getSource() == telaPrincipal.getMenuItemConsultarVeterinario()) {
            System.out.println("Clicou em Consultar Veterinário.");
            TelaConsultaVeterinario telaConsultaVeterinario = new TelaConsultaVeterinario();
            ConsultaVeterinarioController consultaVeterinarioController = new ConsultaVeterinarioController(telaConsultaVeterinario);
            telaConsultaVeterinario.setVisible(true);

        } else if (e.getSource() == telaPrincipal.getMenuItemCadastrarServico()) { // cadastrar serviço
            System.out.println("Clicou em Cadastrar Serviço.");
            TelaCadastroServico telaCadastroServico = new TelaCadastroServico(); // Cria a View
            CadastroServicoController cadastroServicoController = new CadastroServicoController(telaCadastroServico); // seta  o Controller e passa a View
            telaCadastroServico.setVisible(true);

        } else if (e.getSource() == telaPrincipal.getMenuItemConsultarServico()) { // consulta de serviços
            System.out.println("Clicou em Consultar Serviço.");
            TelaConsultaServico telaConsultaServico = new TelaConsultaServico(); // Cria a View
            ConsultaServicoController consultaServicoController = new ConsultaServicoController(telaConsultaServico); // seta o Controller e passa a View
            telaConsultaServico.setVisible(true);

        } else if (e.getSource() == telaPrincipal.getMenuItemSair()) {
            System.out.println("Clicou em Sair.");
            System.exit(0);
        }
    }
}