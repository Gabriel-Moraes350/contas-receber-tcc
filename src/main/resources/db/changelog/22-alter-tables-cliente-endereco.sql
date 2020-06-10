--liquibase formatted sql

--changeset felipe.queiroz:22

alter table cliente
    add column endereco_id integer references endereco(id)
;

alter table endereco
    drop column cliente_id
;

