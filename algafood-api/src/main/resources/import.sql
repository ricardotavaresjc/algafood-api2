INSERT INTO tab_cozinhas(id,nome) values(1,"Tailandeza");
INSERT INTO tab_cozinhas(id,nome) values(2,"Chinesa");

INSERT INTO restaurante(nome,taxa_frete,cozinha_id) values("Hunsen",10.20,1);
INSERT INTO restaurante(nome,taxa_frete,cozinha_id) values("China", 1.99,1);
INSERT INTO restaurante(nome,taxa_frete,cozinha_id) values("Muua", 5.99,2);

INSERT INTO estado(nome) values("Minas Gerais");
INSERT INTO estado(nome) values("Goias");

INSERT INTO cidade(nome, estado_id) values("Uberlandia",1);

INSERT INTO forma_pagamento(descricao) values("Cartão Débito");
INSERT INTO forma_pagamento(descricao) values("Cartão Crédito");
INSERT INTO forma_pagamento(descricao) values("Dinheiro");

INSERT INTO permissao(nome,descricao) values("Administrador","Administrador do Sistema");
INSERT INTO permissao(nome,descricao) values("Usuarios","Usuario do sistema");