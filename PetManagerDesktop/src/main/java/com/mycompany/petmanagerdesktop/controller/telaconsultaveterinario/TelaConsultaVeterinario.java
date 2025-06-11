package com.mycompany.petmanagerdesktop.visao;

import com.mycompany.petmanagermodelo.dto.Veterinario; // Importa o DTO Veterinario
import javax.swing.*;
import javax.swing.table.DefaultTableModel; // Para manipular os dados da tabela
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaConsultaVeterinario extends JFrame {
    private JTable veterinarioTable;
    private DefaultTableModel tableModel;
    private JButton btnAtualizar; // Botão para recarregar a lista

    public TelaConsultaVeterinario() {
        setTitle("Consulta de Veterinários");
        setSize(800, 600); // Tamanho adequado para a tabela
        setLocationRelativeTo(null); // Centraliza a janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Define as colunas da tabela
        String[] columnNames = {"ID", "Nome", "Especialidade", "Telefone", "Endereço"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Torna as células da tabela não editáveis
            }
        };
        veterinarioTable = new JTable(tableModel);

        // Adiciona a tabela a um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(veterinarioTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Painel para botões (ex: Atualizar)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAtualizar = new JButton("Atualizar Lista");
        buttonPanel.add(btnAtualizar);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    // Método para preencher a tabela com uma lista de veterinários
    public void popularTabela(List<Veterinario> veterinarios) {
        tableModel.setRowCount(0); // Limpa a tabela antes de adicionar novos dados
        if (veterinarios != null) {
            for (Veterinario veterinario : veterinarios) {
                Object[] rowData = {
                    veterinario.getId(),
                    veterinario.getNome(),
                    veterinario.getEspecialidade(),
                    veterinario.getTelefone(),
                    veterinario.getEndereco()
                };
                tableModel.addRow(rowData);
            }
        }
    }
    
    // Método para adicionar listener ao botão de atualização
    public void addAtualizarListener(ActionListener listener) {
        btnAtualizar.addActionListener(listener);
    }

    // Getter para o botão de atualização (para o Controller)
    public JButton getBtnAtualizar() {
        return btnAtualizar;
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}