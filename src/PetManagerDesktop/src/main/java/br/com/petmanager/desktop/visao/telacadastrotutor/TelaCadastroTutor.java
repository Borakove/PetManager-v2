package br.com.petmanager.desktop.visao.telacadastrotutor;

import br.com.petmanager.modelo.dto.Tutor; // <--- Importante!
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TelaCadastroTutor extends JFrame {
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtTelefone;
    private JTextField txtEndereco;

    private JButton btnNovo;
    private JButton btnSalvar;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private JButton btnBuscar; // Para buscar por ID

    public TelaCadastroTutor() {
        setTitle("Cadastro e Gestão de Tutores");
        setSize(550, 450); // Aumenta o tamanho para os campos
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel para os campos de formulário (GridBagLayout para alinhamento)
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Componentes de ID
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

        // Nome
        JLabel lblNome = new JLabel("Nome:");
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(lblNome, gbc);
        txtNome = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2;
        formPanel.add(txtNome, gbc);

        // Telefone
        JLabel lblTelefone = new JLabel("Telefone:");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(lblTelefone, gbc);
        txtTelefone = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2;
        formPanel.add(txtTelefone, gbc);

        // Endereço
        JLabel lblEndereco = new JLabel("Endereço:");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(lblEndereco, gbc);
        txtEndereco = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 2;
        formPanel.add(txtEndereco, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        // Painel para os botões
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

        // Configuração inicial dos botões
        limparCampos();
        habilitarBotoes(true, false, false, false);
    }

    // Métodos para obter os dados dos campos
    public int getIdTutor() {
        try {
            return Integer.parseInt(txtId.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getNomeTutor() {
        return txtNome.getText();
    }

    public String getTelefoneTutor() {
        return txtTelefone.getText();
    }

    public String getEnderecoTutor() {
        return txtEndereco.getText();
    }

    // Métodos para preencher os campos com dados de um Tutor
    public void setDadosTutor(Tutor tutor) {
        if (tutor != null) {
            txtId.setText(String.valueOf(tutor.getId()));
            txtNome.setText(tutor.getNome());
            txtTelefone.setText(tutor.getTelefone());
            txtEndereco.setText(tutor.getEndereco());
            habilitarBotoes(false, true, true, true);
        } else {
            limparCampos();
            habilitarBotoes(true, false, false, false);
        }
    }

    // Método para limpar todos os campos
    public void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtTelefone.setText("");
        txtEndereco.setText("");
    }

    // Método para habilitar/desabilitar botões
    public void habilitarBotoes(boolean novo, boolean salvar, boolean editar, boolean excluir) {
        btnNovo.setEnabled(novo);
        btnSalvar.setEnabled(salvar);
        btnEditar.setEnabled(editar);
        btnExcluir.setEnabled(excluir);
    }

    // Métodos para adicionar Listeners aos botões
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
        return JOptionPane.showInputDialog(this, "Digite o ID do tutor para buscar:");
    }

    // GETTERS PARA OS BOTÕES (ESSENCIAIS PARA O CONTROLLER)
    public JButton getBtnNovo() { return btnNovo; }
    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnEditar() { return btnEditar; }
    public JButton getBtnExcluir() { return btnExcluir; }
    public JButton getBtnLimpar() { return btnLimpar; }
    public JButton getBtnBuscar() { return btnBuscar; }
}