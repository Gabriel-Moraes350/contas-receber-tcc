--liquibase formatted sql

--changeset felipe.queiroz:15

alter table cliente
add column delete_date timestamptz
;