CREATE DATABASE IF NOT EXISTS petmanager;
USE petmanager;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE tutor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(200),
    cpf VARCHAR(14) UNIQUE
);

CREATE TABLE veterinario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    crmv VARCHAR(20) UNIQUE,
    telefone VARCHAR(20),
    especialidade VARCHAR(100)
);

CREATE TABLE servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2),
    duracaoMinutos INT
);

CREATE TABLE animal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(50),
    idade INT,
    tutor_id INT,
    FOREIGN KEY (tutor_id) REFERENCES tutor(id)
);

CREATE TABLE consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dataConsulta DATE NOT NULL,
    animal_id INT,
    veterinario_id INT,
    servico_id INT,
    FOREIGN KEY (animal_id) REFERENCES animal(id),
    FOREIGN KEY (veterinario_id) REFERENCES veterinario(id),
    FOREIGN KEY (servico_id) REFERENCES servico(id)
);
