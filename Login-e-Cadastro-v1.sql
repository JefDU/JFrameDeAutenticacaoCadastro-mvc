create database projeto_login_cadastro_v1;
use projeto_login_cadastro_v1;

create table usuario(
cpf varchar(11) not null,
nome varchar(50) not null,
login varchar(20) not null,
senha varchar(8) not null,
primary key(cpf)
);

select * from usuario;