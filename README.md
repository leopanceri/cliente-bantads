create table endereco (
	id serial primary key,
	cep varchar(225),
	logradouro varchar(225),
	numero int,
	complemento varchar(225),
	cidade varchar(225),
	uf varchar(225)
);


CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) not null,
    cpf VARCHAR(255) not null unique,
    salario NUMERIC(10,2),
    email VARCHAR(255),
    telefone VARCHAR(255),
    status VARCHAR(225),
    statusSet TIMESTAMP,
    motivo VARCHAR(225),
    endereco_id SERIAL,
    foreign key (endereco_id) references endereco (id)
);

select * from clientes.cliente;

select * from clientes.endereco;


delete from clientes.endereco;
delete from clientes.cliente;