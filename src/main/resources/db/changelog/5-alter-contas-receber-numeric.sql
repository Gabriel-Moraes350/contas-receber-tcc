--liquibase formatted sql

--changeset gabriel.moraes:5

alter table contas_receber
alter column valor_multa type numeric(12, 2)
;

alter table contas_receber
alter column valor_desconto type numeric(12, 2)
;

alter table contas_receber
alter column valor_liquido_recebido type numeric(12, 2)
;

alter table contas_receber
alter column valor_total type numeric(12, 2)
;