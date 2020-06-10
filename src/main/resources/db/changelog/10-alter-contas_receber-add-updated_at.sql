--liquibase formatted sql

--changeset gabriel.moraes:10

alter table contas_receber
add column data_alteracao timestamptz default now()
;