--liquibase formatted sql

--changeset gabriel.moraes:20

alter table contas_receber
alter column data_liquidacao type date
;

alter table contas_receber
alter column data_credito type date
;

