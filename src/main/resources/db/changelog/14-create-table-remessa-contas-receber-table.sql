--liquibase formatted sql

--changeset gabriel.moraes:14

create table if not exists remessa_contas_receber(
    remessa_id int8 ,
    conta_receber_id int8,
    created_at timestamptz not null default now(),
    primary key(remessa_id, conta_receber_id)
)
;

