--liquibase formatted sql

--changeset gabriel.moraes:16

alter table contas_receber
drop column cod_remessa
;

