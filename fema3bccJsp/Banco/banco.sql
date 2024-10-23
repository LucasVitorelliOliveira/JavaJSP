drop table if exists participante;
drop table if exists evento;
drop table if exists clube;

create table clube ( 
   codigo int not null primary key, 
   nome varchar(100) not null, 
   endereco varchar(100) not null
);
insert into clube (codigo, nome, endereco) values
	(1, 'assis', 'aaaaaa'),
	(2, 'tenis clube', 'bbbbbb');

create table evento (
codigo int not null primary key,
nome varchar(100) not null,
tipo varchar(100) not null
);
insert into evento (codigo, nome, tipo) values
	(1, 'nova assis', 'promotor');

create table participante (
codigo int not null primary key,
nome varchar(11) not null,
idade int not null
);
insert into participante(codigo, nome, idade) values
	(1,'Mateus', 54),
	(2,'Mauricio', 20);
