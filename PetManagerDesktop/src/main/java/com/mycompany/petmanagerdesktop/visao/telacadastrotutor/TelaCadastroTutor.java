package com.mycompany.petmanagerdesktop.visao;

import javax.swing.*;
import java.awt.BorderLayout;

public class TelaCadastroTutor extends JFrame {
    public TelaCadastroTutor() {
        setTitle("Cadastro de Tutor");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Tela de Cadastro de Tutor (em construção)", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        add(panel);
    }
}