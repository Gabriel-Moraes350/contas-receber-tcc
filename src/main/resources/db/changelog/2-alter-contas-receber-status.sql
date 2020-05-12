--liquibase formatted sql

--changeset gabriel.moraes:2

alter table contas_receber
alter column
status
set default 'aguardando';

