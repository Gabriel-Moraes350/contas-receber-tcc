--liquibase formatted sql

--changeset gabriel.moraes:15

alter table contas_receber
drop column codigo_banco
;

alter table contas_receber
add column deleted_at timestamptz default null
;

