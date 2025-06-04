package com.mycompany.petmanagerdesktop.visao;

import javax.swing.*;
import java.awt.BorderLayout;

public class TelaCadastroServico extends JFrame {
    public TelaCadastroServico() {
        setTitle("Cadastro de Serviço");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Tela de Cadastro de Serviço (em construção)", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        add(panel);
    }
}