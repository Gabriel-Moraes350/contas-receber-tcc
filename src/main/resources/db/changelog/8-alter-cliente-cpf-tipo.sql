--liquibase formatted sql

--changeset felipe.queiroz:9

alter table cliente
    alter column tipo set not null,
    add constraint tipo check (tipo IN ('F', 'J'))
;