package com.mycompany.petmanagermodelo.dao;

import com.mycompany.petmanagermodelo.dto.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger; //  esse impot gera ids de forma segura 

public class AnimalDAO {

    // simulação de um bd
    private static List<Animal> animais = new ArrayList<>();
    // gerador de id simples 
    private static AtomicInteger idCounter = new AtomicInteger(0);

    public AnimalDAO() {

        if (animais.isEmpty()) {
            //  aq ocontador de ID começa após o maior id dos dados iniciais
            int maxId = 0;
            salvar(new Animal("Rex", "Cachorro", "Labrador", 3, "Marrom"));
            salvar(new Animal("Miau", "Gato", "Siamês", 2, "Branco e Preto"));
        }
    }

    public Animal salvar(Animal animal) {
        if (animal.getId() == 0) { // se o id é 0, é um novo animal 
            animal.setId(idCounter.incrementAndGet()); // novo id
            animais.add(animal);
            System.out.println("Animal salvo: " + animal.getNome() + " (ID: " + animal.getId() + ")");
        } else { // o contrário, é uma atualização
            // atualizar um animal existente
            boolean encontrado = false;
            for (int i = 0; i < animais.size(); i++) {
                if (animais.get(i).getId() == animal.getId()) {
                    animais.set(i, animal); // Substitui o animal existente
                    encontrado = true;
                    System.out.println("Animal atualizado: " + animal.getNome() + " (ID: " + animal.getId() + ")");
                    break;
                }
            }
            if (!encontrado) {
                System.err.println("Erro: Animal com ID " + animal.getId() + " não encontrado para atualização.");
                return null; // lança uma excessap
            }
        }
        return animal;
    }

    public Animal buscarPorId(int id) {
        for (Animal animal : animais) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null; // n encontrado 
    }

    public List<Animal> listarTodos() {
        return new ArrayList<>(animais); // retorna uma copia p evitar modificações externas
    }

    public boolean excluir(int id) {
        Animal animalParaRemover = null;
        for (Animal animal : animais) {
            if (animal.getId() == id) {
                animalParaRemover = animal;
                break;
            }
        }
        if (animalParaRemover != null) {
            animais.remove(animalParaRemover);
            System.out.println("Animal removido: ID " + id);
            return true;
        }
        System.err.println("Erro: Animal com ID " + id + " não encontrado para exclusão.");
        return false;
    }
}