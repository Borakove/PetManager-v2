package com.mycompany.petmanagerdesktop.visao;

import com.mycompany.petmanagermodelo.dto.Animal;
import javax.swing.*;
import javax.swing.table.DefaultTableModel; 
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaConsultaAnimal extends JFrame {
    private JTable animalTable;
    private DefaultTableModel tableModel;
    private JButton btnAtualizar; // reload de lista

    public TelaConsultaAnimal() {
        setTitle("Consulta de Animais");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"ID", "Nome", "Espécie", "Raça", "Idade", "Cor"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        animalTable = new JTable(tableModel);

        // tebalea + rolagem 
        JScrollPane scrollPane = new JScrollPane(animalTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAtualizar = new JButton("Atualizar Lista");
        buttonPanel.add(btnAtualizar);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    public void popularTabela(List<Animal> animais) {
        tableModel.setRowCount(0);
        if (animais != null) {
            for (Animal animal : animais) {
                Object[] rowData = {
                    animal.getId(),
                    animal.getNome(),
                    animal.getEspecie(),
                    animal.getRaca(),
                    animal.getIdade(),
                    animal.getCor()
                };
                tableModel.addRow(rowData);
            }
        }
    }
    
    public void addAtualizarListener(ActionListener listener) {
        btnAtualizar.addActionListener(listener);
    }

    public JButton getBtnAtualizar() {
        return btnAtualizar;
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}