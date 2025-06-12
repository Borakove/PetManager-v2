package br.com.petmanager.desktop.telaconsultaservico;

import br.com.petmanager.modelo.dto.Servico;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale; // br

public class TelaConsultaServico extends JFrame {
    private JPanel servicesContainerPanel;
    private JButton btnAtualizar;

    public TelaConsultaServico() {
        setTitle("Consulta de Serviços");
        setSize(850, 650); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        servicesContainerPanel = new JPanel();
        servicesContainerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15)); 
        JScrollPane scrollPane = new JScrollPane(servicesContainerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAtualizar = new JButton("Atualizar Lista");
        buttonPanel.add(btnAtualizar);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createServiceBlock(Servico servico) {
        JPanel serviceBlock = new JPanel();
        serviceBlock.setLayout(new BorderLayout(5, 5));
        serviceBlock.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)   
        ));
        serviceBlock.setPreferredSize(new Dimension(250, 100));
        serviceBlock.setBackground(Color.WHITE);

        JLabel descriptionLabel = new JLabel(servico.getDescricao(), SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        serviceBlock.add(descriptionLabel, BorderLayout.NORTH);

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        JLabel valueLabel = new JLabel(currencyFormatter.format(servico.getValor()), SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        valueLabel.setForeground(new Color(34, 139, 34)); // verde para valor
        serviceBlock.add(valueLabel, BorderLayout.CENTER);
        
        return serviceBlock;
    }

    public void popularServicos(List<Servico> servicos) {
        servicesContainerPanel.removeAll(); // da clean nopainel antes de adicionar novos blocos
        if (servicos != null && !servicos.isEmpty()) {
            for (Servico servico : servicos) {
                servicesContainerPanel.add(createServiceBlock(servico)); // Adiciona cada bloco
            }
        } else {
            JLabel noServicesLabel = new JLabel("Nenhum serviço cadastrado.", SwingConstants.CENTER);
            noServicesLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            noServicesLabel.setForeground(Color.GRAY);
            servicesContainerPanel.add(noServicesLabel); 
        }
        servicesContainerPanel.revalidate(); 
        servicesContainerPanel.repaint(); 
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