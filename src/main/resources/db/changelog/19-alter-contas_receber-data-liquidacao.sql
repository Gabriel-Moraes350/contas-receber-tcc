--liquibase formatted sql

--changeset gabriel.moraes:19

alter table contas_receber
drop column data_ocorrencia
;

alter table contas_receber
add column data_liquidacao timestamptz default null
;

