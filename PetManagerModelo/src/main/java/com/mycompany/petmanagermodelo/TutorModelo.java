package com.mycompany.petmanagermodelo;

import com.mycompany.petmanagermodelo.dao.TutorDAO;
import com.mycompany.petmanagermodelo.dto.Tutor;   
import java.util.List;

public class TutorModelo {
    private TutorDAO tutorDAO; 

    public TutorModelo() {
        this.tutorDAO = new TutorDAO();
    }

    public Tutor salvarTutor(Tutor tutor) {
        if (tutor.getNome() == null || tutor.getNome().trim().isEmpty()) {
            System.err.println("Erro de negócio: O nome do tutor não pode ser vazio.");
            return null;
        }
        
        return tutorDAO.salvar(tutor); 
    }

    public Tutor buscarTutorPorId(int id) {
        if (id <= 0) {
            System.err.println("Erro de negócio: ID para busca de tutor deve ser positivo.");
            return null;
        }
        return tutorDAO.buscarPorId(id);
    }

    public List<Tutor> listarTodosTutores() {
        return tutorDAO.listarTodos();
    }

    public boolean excluirTutor(int id) {
        if (id <= 0) {
            System.err.println("Erro de negócio: ID para exclusão de tutor deve ser positivo.");
            return false;
        }
 
        if (tutorDAO.buscarPorId(id) == null) {
            System.err.println("Erro de negócio: Tutor com ID " + id + " não encontrado para exclusão.");
            return false;
        }
        
        return tutorDAO.excluir(id);
    }
}