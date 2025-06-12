package br.com.petmanager.desktop;

import br.com.petmanager.desktop.controller.LoginController;
import br.com.petmanager.desktop.visao.TelaLogin.TelaLogin;

public class PetManagerDesktop {

    public static void main(String[] args) {

        TelaLogin telaLogin = new TelaLogin();

        LoginController loginController = new LoginController(telaLogin);
        
        telaLogin.setVisible(true);
    }
}