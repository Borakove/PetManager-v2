package com.mycompany.petmanagerdesktop.controller;

import com.mycompany.petmanagermodelo.dao.ConsultaDAO;
import com.mycompany.petmanagermodelo.dto.Consulta;
import com.mycompany.petmanagerdesktop.visao.telacadastroconsulta.TelaCadastroConsulta;

public class ConsultaController {

    private final TelaCadastroConsulta tela;
    private int consultaIdAtual = -1;  // para guardar o meu ID (futura atualização)

    public ConsultaController(TelaCadastroConsulta tela) {
        this.tela = tela;
    }

    public void carregarConsultaParaEdicao(Consulta c) {
        consultaIdAtual = c.getId();  // para guardar o meu ID (futura atualização) att/excluir 
        tela.getTxtDataConsulta().setText(c.getData());
        tela.getCbAnimal().setSelectedItem(c.getAnimal());
        tela.getCbVeterinario().setSelectedItem(c.getVeterinario());
        tela.getCbServico().setSelectedItem(c.getServico());
    }

    public void salvar() {
        Consulta dto = new Consulta();
        dto.setData(tela.getTxtDataConsulta().getText());
        dto.setAnimal(tela.getCbAnimal().getSelectedItem().toString());
        dto.setVeterinario(tela.getCbVeterinario().getSelectedItem().toString());
        dto.setServico(tela.getCbServico().getSelectedItem().toString());

        new ConsultaDAO().salvar(dto);

        consultaIdAtual = -1; // reset após salvar
    }

    public void atualizar() {
        if (consultaIdAtual == -1) {
            System.out.println("Nenhuma consulta selecionada para atualização.");
            return;
        }

        Consulta dto = new Consulta();
        dto.setId(consultaIdAtual);
        dto.setData(tela.getTxtDataConsulta().getText());
        dto.setAnimal(tela.getCbAnimal().getSelectedItem().toString());
        dto.setVeterinario(tela.getCbVeterinario().getSelectedItem().toString());
        dto.setServico(tela.getCbServico().getSelectedItem().toString());

        new ConsultaDAO().atualizar(dto);

        consultaIdAtual = -1; 
    }

    public void excluir() {
        if (consultaIdAtual == -1) {
            System.out.println("Nenhuma consulta selecionada para exclusão.");
            return;
        }

        new ConsultaDAO().excluir(consultaIdAtual);

        consultaIdAtual = -1;
    }
}
