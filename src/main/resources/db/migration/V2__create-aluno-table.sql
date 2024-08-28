-- Criação da tabela alunos
CREATE TABLE alunos (
    id bigint not null auto_increment,
    ativo TINYINT DEFAULT 1,
     nome varchar(100) not null,
    idade int not null,
    matricula  varchar(100) unique,
    turma_id bigint ,
     cpf varchar(14) unique,
    primary key(id),
    FOREIGN KEY (turma_id) REFERENCES turma(id) ON DELETE SET NULL


);
