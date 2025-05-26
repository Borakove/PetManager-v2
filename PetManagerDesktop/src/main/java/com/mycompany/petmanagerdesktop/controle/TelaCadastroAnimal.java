package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagermodelo.dao.AnimalDAO;
import com.mycompany.petmanagermodelo.dto.Animal;
import javax.swing.JOptionPane;

public class TelaCadastroAnimal {
    public void salvarAnimal(String nome, String especie, int idade, String tutor) {
        try {
            Animal animal = new Animal();
            animal.setNome(nome);
            animal.setEspecie(especie);
            animal.setIdade(idade);
            animal.setTutor(tutor);

            AnimalDAO dao = new AnimalDAO();
            dao.inserir(animal);

            JOptionPane.showMessageDialog(null, "Animal cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar animal: " + e.getMessage());
        }
    }
}
