package br.com.petmanager.modelo;

import br.com.petmanager.modelo.dao.AnimalDAO;
import br.com.petmanager.modelo.dto.Animal;
import java.util.List;

public class AnimalModelo {
    private AnimalDAO animalDAO; // usa o dao

    public AnimalModelo() {
        this.animalDAO = new AnimalDAO();
    }

    // slv e att animal
    public Animal salvarAnimal(Animal animal) {
        if (animal.getNome() == null || animal.getNome().trim().isEmpty()) {
            System.err.println("Erro de negócio: O nome do animal não pode ser vazio.");
            return null;
        }
        if (animal.getIdade() < 0) {
            System.err.println("Erro de negócio: A idade do animal não pode ser negativa.");
            return null;
        }

        return animalDAO.salvar(animal);
    }

    // search por id
    public Animal buscarAnimalPorId(int id) {
        if (id <= 0) {
            System.err.println("Erro de negócio: ID para busca deve ser positivo.");
            return null;
        }
        return animalDAO.buscarPorId(id);
    }

    // listar todos os animais
    public List<Animal> listarTodosAnimais() {
        return animalDAO.listarTodos();
    }

    // excluir um aninaml
    public boolean excluirAnimal(int id) {
        if (id <= 0) {
            System.err.println("Erro de negócio: ID para exclusão deve ser positivo.");
            return false;
        }
        // aq verif o animal antes de excluir 
        if (animalDAO.buscarPorId(id) == null) {
            System.err.println("Erro de negócio: Animal com ID " + id + " não encontrado para exclusão.");
            return false;
        }
        // futura regra: "n pode excluir animal com serviços pendentes"
        return animalDAO.excluir(id);
    }
}