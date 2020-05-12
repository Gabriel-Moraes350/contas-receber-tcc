--liquibase formatted sql

--changeset gabriel.moraes:4

alter table endereco
alter column cep type varchar(8)
;