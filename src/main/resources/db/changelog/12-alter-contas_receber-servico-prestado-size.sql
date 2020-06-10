--liquibase formatted sql

--changeset gabriel.moraes:12

alter table contas_receber
alter column servico_prestado type text
;

alter table contas_receber
add column tipo_servico_prestado varchar(50) not null default 'SERVICO'
;

