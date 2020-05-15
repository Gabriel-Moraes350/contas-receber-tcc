--liquibase formatted sql

--changeset caique.reimberg:2

delete from usuario where id = 1;

delete from cargo where id = 1;

delete from nivel where id = 1;

insert into cargo(id, cargo) values (1, 'teste');

insert into nivel(id, nivel) values (1, 'usuario');

insert into usuario values (1, 'teste', 'teste', 'teste', '$2y$12$WOKMgNNZ6q95UmJw896JR.cTzEfUIQQqpRThWa/G67yzSG6WUtlXq', 1, 1);

--rollback drop table if exists usuario cascade;
--rollback drop table if exists nivel cascade;
--rollback drop table if exists cargo cascade;
