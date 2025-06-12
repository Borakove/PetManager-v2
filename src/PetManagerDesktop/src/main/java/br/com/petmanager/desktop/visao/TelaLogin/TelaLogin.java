package br.com.petmanager.desktop.visao.TelaLogin;

import javax.swing.*;
import java.awt.*; // Importa a classe Font
import java.awt.event.ActionListener; // Importa ActionListener

public class TelaLogin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin; // Declarado aqui

    public TelaLogin() {
        setTitle("Login - PetManager");
        setSize(300, 250); // Aumenta a altura para acomodar o título
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel); // Garante que placeComponents é chamado e o botão é inicializado
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null); // Usando layout nulo para posicionamento manual

        // --- Título PetManager ---
        JLabel appTitleLabel = new JLabel("PetManager");
        appTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        appTitleLabel.setForeground(new Color(60, 140, 220));
        appTitleLabel.setBounds(65, 20, 200, 30);
        panel.add(appTitleLabel);
        // ------------------------------

        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setBounds(10, 70, 80, 25);
        panel.add(userLabel);

        txtUsuario = new JTextField(20);
        txtUsuario.setBounds(100, 70, 165, 25);
        panel.add(txtUsuario);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(10, 100, 80, 25);
        panel.add(passwordLabel);

        txtSenha = new JPasswordField(20);
        txtSenha.setBounds(100, 100, 165, 25);
        panel.add(txtSenha);

        // *** AQUI É ONDE O BOTÃO btnLogin É INICIALIZADO ***
        btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 140, 80, 25);
        panel.add(btnLogin);
    }

    // Métodos para o Controller acessar os componentes
    public String getUsuario() {
        return txtUsuario.getText();
    }

    public char[] getSenha() {
        return txtSenha.getPassword();
    }

    // Método para o Controller poder acessar o botão (getter)
    public JButton getLoginButton() {
        return btnLogin;
    }

    // Método para adicionar o listener do botão
    public void addLoginListener(ActionListener listener) {
        // Neste ponto, btnLogin DEVE estar inicializado, pois placeComponents é chamado no construtor.
        if (btnLogin != null) { // Adicionando uma verificação extra para segurança, mas não deveria ser necessário com o código correto.
            btnLogin.addActionListener(listener);
        } else {
            System.err.println("Erro: Botão de Login não inicializado ao tentar adicionar listener.");
        }
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    public void fechar() {
        this.dispose();
    }
}