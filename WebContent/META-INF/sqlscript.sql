-- Exercício 02 CRUD Luís
-- Rio 17/03/2021
-- Autor: Felipe Alheiro
create database exercicio_luis_02;
use exercicio_luis_02;
-- A dependÊncia tem de estar em endereço. Se o contato for apagado o endereço vai junto e a relação só me parece ter um sentido Luís.
create table contato(
id_contato bigint auto_increment primary key,
nome varchar(50),
sobrenome varchar(150) default null,
cpf varchar(15) not null unique,
dt_nascimento date default null,
email varchar(100) not null
);

-- Relação 1 contato para * telefones e 1 telefone para 1 contato
create table telefone(
id_telefone bigint auto_increment,
ddi varchar(3) default null,
ddd varchar(3) default null,
numero_telefone varchar(10) default null,
id_cliente_associado bigint,
constraint cliente_fk foreign key telefone(id_cliente_associado) references contato(id_contato) on delete cascade on update cascade,
constraint id_tel_pk primary key telefone(id_telefone) 
);

-- Relação 1 contato para 1 endereço e 1 endereco para 1 contato
create table endereco(
id_endereco bigint primary key auto_increment,
tipo_logradouro varchar(50) not null,
logradouro varchar(150) not null,
complemento varchar(150) not null,
bairro varchar(50) not null,
cidade varchar(50) not null,
estado varchar(50) not null,
pais varchar(50) not null,
id_contato_residente bigint,
constraint residente_fk foreign key endereco(id_contato_residente) references contato(id_contato) on delete cascade on update cascade
);
-- Para um futuro esquema de login e senha Luís
create table usuario(
id_usuario bigint primary key auto_increment,
login varchar(100) not null unique, 
senha varchar(50) not null
);