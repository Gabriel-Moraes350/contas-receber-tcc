--liquibase formatted sql

--changeset gabriel.moraes:1
create table test1 (
    id int primary key,
    name varchar(255)
);
--rollback drop table test1;
