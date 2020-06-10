--liquibase formatted sql

--changeset gabriel.moraes:18

create table if not exists retorno_contas_receber(
    retorno_id int8 ,
    conta_receber_id int8,
    created_at timestamptz not null default now(),
    primary key(retorno_id, conta_receber_id)
)
;

