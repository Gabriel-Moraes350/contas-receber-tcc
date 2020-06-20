INSERT INTO endereco
(id, rua, bairro, cep, numero, uf, cidade, complemento)
VALUES(40, 'Rua Erva do Fogo', 'Jd do Ipês', '08161250', 392, 'SP', 'São Paulo', '');

INSERT INTO cliente
(id, cnpj, nome_fantasia, email, ddd_telefone, telefone, bloqueado, dat_ult_bloqueio, tipo, razao_social, inscricao_estadual, delete_date, endereco_id)
VALUES(100, '91276558000150', 'Henrique e Eloá Marketing', 'contabilidade@henriqueeeloamarketingltda.com.br', 11, 988535281, false, NULL, 'J', 'Henrique e Eloá Marketing Ltda', '216455087625', NULL, 40);

INSERT INTO contas_receber
(cliente_id, status, forma_pagamento, num_parcela, valor_multa, valor_desconto, valor_liquido_recebido, valor_total, pontos, servico_prestado, data_criacao, data_vencimento, data_credito, data_alteracao, num_documento, tipo_servico_prestado, deleted_at, data_liquidacao)
VALUES(100, 'enviado', 'boleto', 1, 0.00, 0.00, 0.00, 2000.00, 0, 'Prestação serviço', '2020-06-07 22:15:46.628', '2020-12-05', NULL, '2020-06-07 23:47:33.019', '51234', 'MANUT', NULL, NULL);

INSERT INTO contas_receber
(id, cliente_id, status, forma_pagamento, num_parcela, valor_multa, valor_desconto, valor_liquido_recebido, valor_total, pontos, servico_prestado, data_criacao, data_vencimento, data_credito, data_alteracao, num_documento, tipo_servico_prestado, deleted_at, data_liquidacao)
VALUES(100, 'enviado', 'boleto', 1, 0.00, 0.00, 0.00, 550.50, 0, 'Manutenção de sistema', '2020-06-07 22:23:08.828', '2020-10-10', NULL, '2020-06-10 19:34:41.475', '51235', 'SERVICO', NULL, NULL);

INSERT INTO contas_receber
(id, cliente_id, status, forma_pagamento, num_parcela, valor_multa, valor_desconto, valor_liquido_recebido, valor_total, pontos, servico_prestado, data_criacao, data_vencimento, data_credito, data_alteracao, num_documento, tipo_servico_prestado, deleted_at, data_liquidacao)
VALUES(100, 'enviado', 'boleto', 1, 0.00, 0.00, 0.00, 1550.50, 0, 'Alocação de pessoa em projeto', '2020-06-10 19:59:30.525', '2020-08-10',NULL, '2020-06-10 20:00:35.777', '51236', 'SERVICO', NULL, NULL);
