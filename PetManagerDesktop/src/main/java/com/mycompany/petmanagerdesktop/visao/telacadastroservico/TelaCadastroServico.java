package com.mycompany.petmanagerdesktop.visao;

import com.mycompany.petmanagermodelo.dto.Servico;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TelaCadastroServico extends JFrame {
    private JTextField txtId;
    private JTextField txtDescricao;
    private JTextField txtValor;
    private JButton btnNovo;
    private JButton btnSalvar;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private JButton btnBuscar; 

    public TelaCadastroServico() {
        setTitle("Cadastro e Gestão de Serviços");
        setSize(550, 400); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblId = new JLabel("ID:");
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(lblId, gbc);
        txtId = new JTextField(5);
        txtId.setEditable(false);
        gbc.gridx = 1; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtId, gbc);
        btnBuscar = new JButton("Buscar");
        gbc.gridx = 2; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(btnBuscar, gbc);

        JLabel lblDescricao = new JLabel("Descrição:");
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(lblDescricao, gbc);
        txtDescricao = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2;
        formPanel.add(txtDescricao, gbc);

        JLabel lblValor = new JLabel("Valor:");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(lblValor, gbc);
        txtValor = new JTextField(10); 
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2;
        formPanel.add(txtValor, gbc);
        
        panel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnNovo = new JButton("Novo");
        btnSalvar = new JButton("Salvar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");

        buttonPanel.add(btnNovo);
        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnExcluir);
        buttonPanel.add(btnLimpar);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        limparCampos();
        habilitarBotoes(true, false, false, false);
    }

    public int getIdServico() {
        try {
            return Integer.parseInt(txtId.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getDescricaoServico() {
        return txtDescricao.getText();
    }

    public double getValorServico() {
        try {
            return Double.parseDouble(txtValor.getText().replace(",", ".")); // vírgula para decimal
        } catch (NumberFormatException e) {
            return 0.0; 
        }
    }

    public void setDadosServico(Servico servico) {
        if (servico != null) {
            txtId.setText(String.valueOf(servico.getId()));
            txtDescricao.setText(servico.getDescricao());
            txtValor.setText(String.valueOf(servico.getValor()));
            habilitarBotoes(false, true, true, true);
        } else {
            limparCampos();
            habilitarBotoes(true, false, false, false);
        }
    }

    public void limparCampos() {
        txtId.setText("");
        txtDescricao.setText("");
        txtValor.setText("");
    }

    public void habilitarBotoes(boolean novo, boolean salvar, boolean editar, boolean excluir) {
        btnNovo.setEnabled(novo);
        btnSalvar.setEnabled(salvar);
        btnEditar.setEnabled(editar);
        btnExcluir.setEnabled(excluir);
    }

    public void addNovoListener(ActionListener listener) {
        btnNovo.addActionListener(listener);
    }

    public void addSalvarListener(ActionListener listener) {
        btnSalvar.addActionListener(listener);
    }

    public void addEditarListener(ActionListener listener) {
        btnEditar.addActionListener(listener);
    }

    public void addExcluirListener(ActionListener listener) {
        btnExcluir.addActionListener(listener);
    }

    public void addLimparListener(ActionListener listener) {
        btnLimpar.addActionListener(listener);
    }

    public void addBuscarListener(ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    public String pedirIdParaBusca() {
        return JOptionPane.showInputDialog(this, "Digite o ID do serviço para buscar:");
    }

    public JButton getBtnNovo() { return btnNovo; }
    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnEditar() { return btnEditar; }
    public JButton getBtnExcluir() { return btnExcluir; }
    public JButton getBtnLimpar() { return btnLimpar; }
    public JButton getBtnBuscar() { return btnBuscar; }
}