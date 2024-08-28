CREATE TABLE professores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INT NOT NULL,
    materia   VARCHAR(50),
    cpf varchar(14) not null unique,
    ativo tinyint DEFAULT 1

);