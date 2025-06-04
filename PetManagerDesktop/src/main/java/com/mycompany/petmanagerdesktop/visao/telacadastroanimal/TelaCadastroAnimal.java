package com.mycompany.petmanagerdesktop.visao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import com.mycompany.petmanagermodelo.dto.Animal;

public class TelaCadastroAnimal extends JFrame {
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtEspecie;
    private JTextField txtRaca;
    private JTextField txtIdade;
    private JTextField txtCor;

    private JButton btnNovo;
    private JButton btnSalvar;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private JButton btnBuscar; // search por id

    public TelaCadastroAnimal() {
        setTitle("Cadastro e Gestão de Animais");
        setSize(550, 450); // tamanho de campos
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dispose fecha a janela nao a aplicação toda 

        JPanel panel = new JPanel(new BorderLayout(10, 10)); 
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // margem interna

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

        JLabel lblNome = new JLabel("Nome:");
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(lblNome, gbc);
        txtNome = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2; 
        formPanel.add(txtNome, gbc);

        JLabel lblEspecie = new JLabel("Espécie:");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1; 
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(lblEspecie, gbc);
        txtEspecie = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2;
        formPanel.add(txtEspecie, gbc);

        JLabel lblRaca = new JLabel("Raça:");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(lblRaca, gbc);
        txtRaca = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 2;
        formPanel.add(txtRaca, gbc);

        JLabel lblIdade = new JLabel("Idade (anos):");
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(lblIdade, gbc);
        txtIdade = new JTextField(5);
        gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 2;
        formPanel.add(txtIdade, gbc);

        JLabel lblCor = new JLabel("Cor:");
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(lblCor, gbc);
        txtCor = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 5; gbc.gridwidth = 2;
        formPanel.add(txtCor, gbc);

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

        add(panel); // painel principal ao JFrame

        limparCampos();
        habilitarBotoes(true, false, false, false);
    }

    // posso ter os dados dos campos
    public int getIdAnimal() {
        try {
            return Integer.parseInt(txtId.getText());
        } catch (NumberFormatException e) {
            return 0; // id de numero nao valido 
        }
    }

    public String getNomeAnimal() {
        return txtNome.getText();
    }

    public String getEspecieAnimal() {
        return txtEspecie.getText();
    }

    public String getRacaAnimal() {
        return txtRaca.getText();
    }

    public int getIdadeAnimal() {
        try {
            return Integer.parseInt(txtIdade.getText());
        } catch (NumberFormatException e) {
            return 0; 
        }
    }

    public String getCorAnimal() {
        return txtCor.getText();
    }

    // preencher os campos com dados
    public void setDadosAnimal(Animal animal) {
        if (animal != null) {
            txtId.setText(String.valueOf(animal.getId()));
            txtNome.setText(animal.getNome());
            txtEspecie.setText(animal.getEspecie());
            txtRaca.setText(animal.getRaca());
            txtIdade.setText(String.valueOf(animal.getIdade()));
            txtCor.setText(animal.getCor());
            habilitarBotoes(false, true, true, true); 
        } else {
            limparCampos();
            habilitarBotoes(true, false, false, false); 
        }
    }

    // limpar todos os campos
    public void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtEspecie.setText("");
        txtRaca.setText("");
        txtIdade.setText("");
        txtCor.setText("");
    }

    // habilitar/desabilitar botoes
    public void habilitarBotoes(boolean novo, boolean salvar, boolean editar, boolean excluir) {
        btnNovo.setEnabled(novo);
        btnSalvar.setEnabled(salvar);
        btnEditar.setEnabled(editar);
        btnExcluir.setEnabled(excluir);
    }

    // adicionar listeners aos botões 
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
        return JOptionPane.showInputDialog(this, "Digite o ID do animal para buscar:");
    }

    public JButton getBtnNovo() { return btnNovo; }
    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnEditar() { return btnEditar; }
    public JButton getBtnExcluir() { return btnExcluir; }
    public JButton getBtnLimpar() { return btnLimpar; }
    public JButton getBtnBuscar() { return btnBuscar; }
}