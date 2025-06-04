package com.mycompany.petmanagerdesktop;

import com.mycompany.petmanagerdesktop.controller.LoginController;
import com.mycompany.petmanagerdesktop.visao.TelaLogin;

public class PetManagerDesktop {

    public static void main(String[] args) {

        TelaLogin telaLogin = new TelaLogin();

        LoginController loginController = new LoginController(telaLogin);
        
        telaLogin.setVisible(true);
    }
}