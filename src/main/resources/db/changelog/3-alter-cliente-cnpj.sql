--liquibase formatted sql

--changeset gabriel.moraes:3

alter table cliente
alter column cnpj type varchar(14)
;
