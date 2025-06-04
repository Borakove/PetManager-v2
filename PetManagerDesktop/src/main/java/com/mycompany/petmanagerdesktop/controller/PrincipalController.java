package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagerdesktop.visao.*;
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
            ConsultaAnimalController consultaAnimalController = new ConsultaAnimalController(telaConsultaAnimal); //  passa de visao p controle
            telaConsultaAnimal.setVisible(true);

        } else if (e.getSource() == telaPrincipal.getMenuItemCadastrarServico()) {
            System.out.println("Clicou em Cadastrar Serviço.");
            new TelaCadastroServico().setVisible(true);
        } else if (e.getSource() == telaPrincipal.getMenuItemConsultarServico()) {
            System.out.println("Clicou em Consultar Serviço.");
            new TelaConsultaServico().setVisible(true);
        } else if (e.getSource() == telaPrincipal.getMenuItemCadastrarTutor()) {
            System.out.println("Clicou em Cadastrar Tutor.");
            new TelaCadastroTutor().setVisible(true);
        } else if (e.getSource() == telaPrincipal.getMenuItemConsultarTutor()) {
            System.out.println("Clicou em Consultar Tutor.");
            new TelaConsultaTutor().setVisible(true);
        } else if (e.getSource() == telaPrincipal.getMenuItemCadastrarVeterinario()) {
            System.out.println("Clicou em Cadastrar Veterinário.");
            new TelaCadastroVeterinario().setVisible(true);
        } else if (e.getSource() == telaPrincipal.getMenuItemConsultarVeterinario()) {
            System.out.println("Clicou em Consultar Veterinário.");
            new TelaConsultaVeterinario().setVisible(true);
        } else if (e.getSource() == telaPrincipal.getMenuItemSair()) {
            System.out.println("Clicou em Sair.");
            System.exit(0);
        }
    }
}