package com.mycompany.petmanagermodelo.dto;

public class Veterinario {
    private int id;
    private String nome;
    private String especialidade;
    private String telefone;
    private String endereco;

    public Veterinario(int id, String nome, String especialidade, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Veterinario(String nome, String especialidade, String telefone, String endereco) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Especialidade: " + especialidade + ", Telefone: " + telefone + ", Endereço: " + endereco;
    }
}