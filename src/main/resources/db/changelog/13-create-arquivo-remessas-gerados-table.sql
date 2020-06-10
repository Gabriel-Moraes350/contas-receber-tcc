--liquibase formatted sql

--changeset gabriel.moraes:13

create table if not exists arquivos_remessas_history(
    id serial primary key,
    name varchar(255) not null,
    file_bytes bytea,
    created_at timestamptz not null default now()
)
;

