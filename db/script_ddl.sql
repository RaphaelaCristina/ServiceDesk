CREATE TABLE faq (
id int AUTO_INCREMENT NOT NULL,
titulo char(50) NOT NULL,
descricao varchar(350) NOT NULL,
status char(30) NOT NULL,
dataCriacao date NOT NULL,
resposta varchar(800),
PRIMARY KEY(id)
);