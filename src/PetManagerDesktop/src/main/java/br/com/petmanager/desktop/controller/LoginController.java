package br.com.petmanager.desktop.controller;

import br.com.petmanager.desktop.visao.TelaLogin.TelaLogin;
import br.com.petmanager.desktop.visao.telaprincipal.TelaPrincipal;
import br.com.petmanager.modelo.LoginModelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginController implements ActionListener {
    private TelaLogin telaLogin;
    private LoginModelo loginModelo;

    public LoginController(TelaLogin telaLogin) {
        this.telaLogin = telaLogin;
        this.loginModelo = new LoginModelo(); // instacia de modelo e login
        this.telaLogin.addLoginListener(this); //  controller regitra botao login 
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == telaLogin.getLoginButton()) {
            String usuario = telaLogin.getUsuario();
            char[] senhaChars = telaLogin.getSenha();


            if (loginModelo.validarLogin(usuario, senhaChars)) {
                telaLogin.exibirMensagem("Login bem-sucedido!");
                telaLogin.fechar(); 

                //prestar atenção nessa parte 
                TelaPrincipal telaPrincipal = new TelaPrincipal(); // cria a instância da tela principal ou seja visao
                PrincipalController principalController = new PrincipalController(telaPrincipal); 

                telaPrincipal.setVisible(true); // faz com q a tela principal fique visivel

            } else {
                telaLogin.exibirMensagem("Usuário ou senha inválidos.");
            }
        }
    }
}