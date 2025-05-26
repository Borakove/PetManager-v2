package com.mycompany.petmanagerdesktop;


import com.mycompany.petmanagerdesktop.visao.TelaLogin.TelaLogin;

public class PetManagerDesktop {
    public static void main(String[] args) {
       
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // starta a tela de login
        new TelaLogin().setVisible(true);
    }
}
