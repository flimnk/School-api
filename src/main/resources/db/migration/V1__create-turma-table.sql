
CREATE TABLE turma (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome   VARCHAR(255) unique,
    ativo tinyint DEFAULT 1
)
