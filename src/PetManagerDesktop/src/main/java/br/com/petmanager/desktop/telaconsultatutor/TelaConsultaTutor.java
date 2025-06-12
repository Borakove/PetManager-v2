package br.com.petmanager.desktop.telaconsultatutor;

import br.com.petmanager.modelo.dto.Tutor; // <--- Importante!
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaConsultaTutor extends JFrame {
    private JTable tutorTable;
    private DefaultTableModel tableModel;
    private JButton btnAtualizar;

    public TelaConsultaTutor() {
        setTitle("Consulta de Tutores");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"ID", "Nome", "Telefone", "Endereço"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tutorTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tutorTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAtualizar = new JButton("Atualizar Lista");
        buttonPanel.add(btnAtualizar);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    public void popularTabela(List<Tutor> tutores) {
        tableModel.setRowCount(0);
        if (tutores != null) {
            for (Tutor tutor : tutores) {
                Object[] rowData = {
                    tutor.getId(),
                    tutor.getNome(),
                    tutor.getTelefone(),
                    tutor.getEndereco()
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