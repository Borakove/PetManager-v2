package br.com.petmanager.modelo;

import br.com.petmanager.modelo.dao.VeterinarioDAO;
import br.com.petmanager.modelo.dto.Veterinario;   
import java.util.List;

public class VeterinarioModelo {
    private VeterinarioDAO veterinarioDAO; 

    public VeterinarioModelo() {
        this.veterinarioDAO = new VeterinarioDAO();
    }

    public Veterinario salvarVeterinario(Veterinario veterinario) {
        
        if (veterinario.getNome() == null || veterinario.getNome().trim().isEmpty()) {
            System.err.println("Erro de negócio: O nome do veterinário não pode ser vazio.");

            return null;
        }

        return veterinarioDAO.salvar(veterinario); 
    }

    public Veterinario buscarVeterinarioPorId(int id) {
        if (id <= 0) {
            System.err.println("Erro de negócio: ID para busca de veterinário deve ser positivo.");
            return null;
        }
        return veterinarioDAO.buscarPorId(id);
    }

    public List<Veterinario> listarTodosVeterinarios() {
        return veterinarioDAO.listarTodos();
    }

    public boolean excluirVeterinario(int id) {
        if (id <= 0) {
            System.err.println("Erro de negócio: ID para exclusão de veterinário deve ser positivo.");
            return false;
        }
        
        if (veterinarioDAO.buscarPorId(id) == null) {
            System.err.println("Erro de negócio: Veterinário com ID " + id + " não encontrado para exclusão.");
            return false;
        }

        return veterinarioDAO.excluir(id); 
    }
}