package com.mycompany.petmanagerdesktop.controle;

import javax.swing.JOptionPane;
import com.mycompany.petmanagermodelo.dao.UsuarioDAO;
import com.mycompany.petmanagermodelo.dto.Usuario;

public class TelaLoginController {

    public static boolean autenticar(String usuario, String senha) {
        Usuario u = UsuarioDAO.autenticar(usuario, senha);
        if (u != null) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
            return false;
        }
    }
}
