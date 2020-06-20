--liquibase formatted sql

--changeset gabriel.moraes:24

ALTER TABLE beneficio
add column cliente_id int4;

alter table beneficio
drop column min_pontos;

alter table beneficio
add constraint beneficio_cliente_fk foreign key (cliente_id) references cliente(id);
