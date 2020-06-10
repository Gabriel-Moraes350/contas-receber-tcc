--liquibase formatted sql

--changeset gabriel.moraes:17

create table if not exists arquivos_retorno_history(
    id serial primary key,
    name varchar(255) not null,
    file_bytes bytea,
    user_id int8,
    created_at timestamptz not null default now()
)
;

