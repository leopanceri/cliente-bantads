CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(255),
    salario NUMERIC(7,2),
    email VARCHAR(255),
    cep VARCHAR(225),
    endereco VARCHAR(255),
    telefone VARCHAR(255),
    status VARCHAR(225)
);
