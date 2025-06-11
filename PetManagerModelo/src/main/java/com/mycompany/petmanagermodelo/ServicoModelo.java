package com.mycompany.petmanagermodelo;

import com.mycompany.petmanagermodelo.dao.ServicoDAO; 
import com.mycompany.petmanagermodelo.dto.Servico;  
import java.util.List;

public class ServicoModelo {
    private ServicoDAO servicoDAO; 

    public ServicoModelo() {
        this.servicoDAO = new ServicoDAO();
    }

    public Servico salvarServico(Servico servico) {
        if (servico.getDescricao() == null || servico.getDescricao().trim().isEmpty()) {
            System.err.println("Erro de negócio: A descrição do serviço não pode ser vazia.");
            return null;
        }
        
        if (servico.getValor() <= 0) {
            System.err.println("Erro de negócio: O valor do serviço deve ser maior que zero.");
            return null;
        }

        return servicoDAO.salvar(servico);
    }

    public Servico buscarServicoPorId(int id) {
        if (id <= 0) {
            System.err.println("Erro de negócio: ID para busca de serviço deve ser positivo.");
            return null;
        }
        return servicoDAO.buscarPorId(id);
    }

    public List<Servico> listarTodosServicos() {
        return servicoDAO.listarTodos();
    }

    public boolean excluirServico(int id) {
        if (id <= 0) {
            System.err.println("Erro de negócio: ID para exclusão de serviço deve ser positivo.");
            return false;
        }
        
        if (servicoDAO.buscarPorId(id) == null) {
            System.err.println("Erro de negócio: Serviço com ID " + id + " não encontrado para exclusão.");
            return false;
        }
        
        return servicoDAO.excluir(id); 
    }
}