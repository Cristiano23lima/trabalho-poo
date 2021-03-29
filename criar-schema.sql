create database if not exists sorveteria;

use sorveteria;

create table cliente(
    id int primary key NOT NULL AUTO_INCREMENT,
    nome varchar(100) NOT NULL,
    cpf varchar(100) NOT NULL,
    telefone varchar(100) NOT NULL,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP);

