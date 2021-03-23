create schema if not exists dbsublog;
use dbsublog;
drop table if exists alunos;
drop table if exists endereco;
create table aluno(
	id_aluno bigint primary key auto_increment,
	matricula varchar(20) unique not null,
	nome varchar(200) not null,
	email varchar(150) not null,
	telefone varchar(15) not null,
    id_endereco bigint, 
    constraint addr_fk foreign key aluno(id_endereco) references endereco(id_endereco) on delete set null on update cascade
);
drop table if exists endereco;
create table endereco(
	id_endereco bigint primary key auto_increment,
	logradouro varchar(200) not null,
	cidade varchar(50) not null,
	estadomail varchar(50) not null,
	pais varchar(50) not null
);