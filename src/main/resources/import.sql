-- 1. Inserindo um Livro Impresso
INSERT INTO livros (titulo, autores, editora, preco) VALUES ('Java Efetivo', 'Joshua Bloch', 'Alta Books', 120.0);
INSERT INTO impresso (id, frete, estoque) VALUES ((SELECT MAX(id) FROM livros), 15.0, 10);
-- 2. Inserindo um Livro Eletrônico
INSERT INTO livros (titulo, autores, editora, preco) VALUES ('Clean Code', 'Robert Martin', 'Prentice Hall', 95.0);
INSERT INTO eletronico (id, tamanho) VALUES ((SELECT MAX(id) FROM livros), 2048);
-- inserindo venda
INSERT INTO vendas (cliente, valor) VALUES ('joão', 215.0);
INSERT INTO venda_livros (numero_venda, livro_id) VALUES (1, 1);
INSERT INTO venda_livros (numero_venda, livro_id) VALUES (1, 2);