package br.com.petmanager.desktop.visao.telaprincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    private JMenuItem menuItemCadastrarAnimal;
    private JMenuItem menuItemConsultarAnimal;
    private JMenuItem menuItemCadastrarServico; 
    private JMenuItem menuItemConsultarServico;   
    private JMenuItem menuItemCadastrarTutor;
    private JMenuItem menuItemConsultarTutor;
    private JMenuItem menuItemCadastrarVeterinario;
    private JMenuItem menuItemConsultarVeterinario;
    private JMenuItem menuItemSair;

    public TelaPrincipal() {
        setTitle("PetManager - Menu Principal");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        // Menu Cadastros
        JMenu menuCadastros = new JMenu("Cadastros");
        menuItemCadastrarAnimal = new JMenuItem("Cadastrar Animal");
        menuItemCadastrarServico = new JMenuItem("Cadastrar Serviço"); // Criação do item
        menuItemCadastrarTutor = new JMenuItem("Cadastrar Tutor");
        menuItemCadastrarVeterinario = new JMenuItem("Cadastrar Veterinário");

        menuCadastros.add(menuItemCadastrarAnimal);
        menuCadastros.add(menuItemCadastrarServico);
        menuCadastros.add(menuItemCadastrarTutor);
        menuCadastros.add(menuItemCadastrarVeterinario);
        menuBar.add(menuCadastros);

        // Menu Consultas
        JMenu menuConsultas = new JMenu("Consultas");
        menuItemConsultarAnimal = new JMenuItem("Consultar Animal");
        menuItemConsultarServico = new JMenuItem("Consultar Serviço"); // Criação do item
        menuItemConsultarTutor = new JMenuItem("Consultar Tutor");
        menuItemConsultarVeterinario = new JMenuItem("Consultar Veterinário");

        menuConsultas.add(menuItemConsultarAnimal);
        menuConsultas.add(menuItemConsultarServico); 
        menuConsultas.add(menuItemConsultarTutor);
        menuConsultas.add(menuItemConsultarVeterinario);
        menuBar.add(menuConsultas);

        JMenu menuArquivo = new JMenu("Arquivo");
        menuItemSair = new JMenuItem("Sair");
        menuArquivo.add(menuItemSair);
        menuBar.add(menuArquivo);

        setJMenuBar(menuBar);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Bem-vindo ao PetManager! Escolha uma opção no menu.", SwingConstants.CENTER);
        contentPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(contentPanel);
    }

    public void addCadastrarAnimalListener(ActionListener listener) {
        menuItemCadastrarAnimal.addActionListener(listener);
    }

    public void addConsultarAnimalListener(ActionListener listener) {
        menuItemConsultarAnimal.addActionListener(listener);
    }

    public void addCadastrarServicoListener(ActionListener listener) {
        menuItemCadastrarServico.addActionListener(listener);
    }

    public void addConsultarServicoListener(ActionListener listener) {
        menuItemConsultarServico.addActionListener(listener);
    }

    public void addCadastrarTutorListener(ActionListener listener) {
        menuItemCadastrarTutor.addActionListener(listener);
    }

    public void addConsultarTutorListener(ActionListener listener) {
        menuItemConsultarTutor.addActionListener(listener);
    }

    public void addCadastrarVeterinarioListener(ActionListener listener) {
        menuItemCadastrarVeterinario.addActionListener(listener);
    }

    public void addConsultarVeterinarioListener(ActionListener listener) {
        menuItemConsultarVeterinario.addActionListener(listener);
    }

    public void addSairListener(ActionListener listener) {
        menuItemSair.addActionListener(listener);
    }

    public JMenuItem getMenuItemCadastrarAnimal() {
        return menuItemCadastrarAnimal;
    }

    public JMenuItem getMenuItemConsultarAnimal() {
        return menuItemConsultarAnimal;
    }

    public JMenuItem getMenuItemCadastrarServico() {
        return menuItemCadastrarServico;
    }

    public JMenuItem getMenuItemConsultarServico() {
        return menuItemConsultarServico;
    }

    public JMenuItem getMenuItemCadastrarTutor() {
        return menuItemCadastrarTutor;
    }

    public JMenuItem getMenuItemConsultarTutor() {
        return menuItemConsultarTutor;
    }

    public JMenuItem getMenuItemCadastrarVeterinario() {
        return menuItemCadastrarVeterinario;
    }

    public JMenuItem getMenuItemConsultarVeterinario() {
        return menuItemConsultarVeterinario;
    }

    public JMenuItem getMenuItemSair() {
        return menuItemSair;
    }
}