 drop schema if exists ex04luiz;
 create schema  if not exists ex04luiz;
 create table pessoa(
 id_pessoa long primary key auto_increment,
 nome varchar(100) not null,
 cpf varchar(15) not null unique,
 email varchar(100) not null,
 telefone varchar(13) not null,
 id_endereco long ,
 constraint fk_endereco foreign key pessoa(id_endereco) references endereco(id_endereco) on delete set null on update cascade
 );
 /*1 para 1*/
 create table cliente(
 id_cliente long primary key auto_increment,
 matricula varchar(16) not null unique,
 
 id_pessoa long not null,
 constraint fk_pessoa foreign key funcionario(id_pessoa) references pessoa(id_pessoa) on delete cascade on update cascade
 );
  /*1 para 1*/
 create table funcionario(
 id_funcionario long primary key auto_increment,
 matricula varchar(16) not null unique,
 id_pessoa long not null,
 constraint fk_pessoa foreign key funcionario(id_pessoa) references pessoa(id_pessoa) on delete cascade on update cascade
 );
  /*1 para n*/
 create table endereco(
logradouro varchar(100) not null,
cidade varchar(100) not null,
estado varchar(100) not null,
 pais varchar(100) not null
 );