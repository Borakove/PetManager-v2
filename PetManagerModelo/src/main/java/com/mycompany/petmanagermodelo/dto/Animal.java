package com.mycompany.petmanagermodelo.dto;

public class Animal {
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private String cor;

    public Animal(int id, String nome, String especie, String raca, int idade, String cor) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.cor = cor;
    }

    public Animal(String nome, String especie, String raca, int idade, String cor) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.cor = cor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }

    public String getCor() {
        return cor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Espécie: " + especie + ", Raça: " + raca + ", Idade: " + idade + ", Cor: " + cor;
    }
}