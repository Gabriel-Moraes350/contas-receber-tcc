--liquibase formatted sql

--changeset felipe.queiroz:7

alter table cliente
    alter column cnpj type varchar(18),
    add column tipo char(1),
    add column razao_social varchar(255),
    add column inscricao_estadual varchar(15)
;

alter table cliente
    rename nome to nome_fantasia
;