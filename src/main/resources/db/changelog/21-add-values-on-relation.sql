--liquibase formatted sql

--changeset gabriel.moraes:21

alter table remessa_contas_receber
add column total_amount numeric(12,2)
;

alter table retorno_contas_receber
add column total_amount numeric(12,2)
;

