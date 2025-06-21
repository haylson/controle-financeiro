CREATE TABLE categoria (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    categoria_pai_id BIGINT,
    FOREIGN KEY (categoria_pai_id) REFERENCES categoria(id)
);