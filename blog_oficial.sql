Use db_blogpessoal_JuCruz;

INSERT INTO tb_postagens (data,texto,titulo)
VALUES (current_timestamp(), "texto da postagem 1"," Postagem 01");
INSERT INTO tb_postagens (data,texto,titulo)
VALUES (current_timestamp(), "texto da postagem 2"," Postagem 02");

SELECT*FROM tb_postagens;