CREATE TABLE cartoes_credito (
    id SERIAL PRIMARY KEY,
    descricao TEXT NOT NULL,
    bandeira VARCHAR(30) NOT NULL,
    ultimos_quatro_digitos CHAR(4) NOT NULL,
    vencimento SMALLINT NOT NULL,
    limite_total NUMERIC(10, 2) NOT NULL,
    limite_livre NUMERIC(10, 2) NOT NULL
);