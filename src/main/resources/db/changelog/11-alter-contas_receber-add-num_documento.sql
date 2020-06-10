--liquibase formatted sql

--changeset gabriel.moraes:11

alter table contas_receber
add column num_documento varchar(255)
;


alter table contas_receber
drop column cod_registro_lote
;