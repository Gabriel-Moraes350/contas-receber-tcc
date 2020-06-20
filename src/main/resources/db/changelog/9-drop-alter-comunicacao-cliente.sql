--liquibase formatted sql

--changeset caique.reimberg:10

/*alter table comunicacao_cliente rename column cod_motivo_comunicacao to motivo_comunicacao;

alter table comunicacao_cliente drop constraint comunicacao_cliente_cod_motivo_comunicacao_fkey;

alter table comunicacao_cliente alter column motivo_comunicacao type varchar(20);

drop table motivo_comunicacao;*/