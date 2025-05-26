package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagermodel.dao.ServicoDAO;
import com.mycompany.petmanagermodel.model.Servico;
import java.util.List;

public class TelaCadastroServico {

    private ServicoDAO servicoDAO;

    public TelaCadastroServico() {
        servicoDAO = new ServicoDAO();
    }

    public boolean salvarServico(String nome, String descricao, double preco, int duracao) {
        Servico servico = new Servico();
        servico.setNome(nome);
        servico.setDescricao(descricao);
        servico.setPreco(preco);
        servico.setDuracaoMinutos(duracao);
        return servicoDAO.inserir(servico);
    }

    public List<Servico> listarServicos() {
        return servicoDAO.listarTodos();
    }

    public boolean atualizarServico(int id, String nome, String descricao, double preco, int duracao) {
        Servico servico = new Servico();
        servico.setId(id);
        servico.setNome(nome);
        servico.setDescricao(descricao);
        servico.setPreco(preco);
        servico.setDuracaoMinutos(duracao);
        return servicoDAO.atualizar(servico);
    }

    public boolean excluirServico(int id) {
        return servicoDAO.excluir(id);
    }

    public Servico buscarServicoPorId(int id) {
        return servicoDAO.buscarPorId(id);
    }
}
