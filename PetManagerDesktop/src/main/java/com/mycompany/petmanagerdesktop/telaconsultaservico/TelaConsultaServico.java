package com.mycompany.petmanagerdesktop.visao;

import javax.swing.*;
import java.awt.BorderLayout;

public class TelaConsultaServico extends JFrame {
    public TelaConsultaServico() {
        setTitle("Consulta de Serviço");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Tela de Consulta de Serviço (em construção)", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        add(panel);
    }
}