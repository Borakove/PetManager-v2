package com.mycompany.petmanagerdesktop.visao;

import javax.swing.*;
import java.awt.BorderLayout;

public class TelaConsultaVeterinario extends JFrame {
    public TelaConsultaVeterinario() {
        setTitle("Consulta de Veterinário");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Tela de Consulta de Veterinário (em construção)", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        add(panel);
    }
}