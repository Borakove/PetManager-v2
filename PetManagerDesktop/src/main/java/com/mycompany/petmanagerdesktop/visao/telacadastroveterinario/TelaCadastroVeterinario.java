package com.mycompany.petmanagerdesktop.visao;

import javax.swing.*;
import java.awt.BorderLayout;

public class TelaCadastroVeterinario extends JFrame {
    public TelaCadastroVeterinario() {
        setTitle("Cadastro de Veterinário");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Tela de Cadastro de Veterinário (em construção)", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        add(panel);
    }
}