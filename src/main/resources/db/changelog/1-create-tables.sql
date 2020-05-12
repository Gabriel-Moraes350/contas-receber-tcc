--liquibase formatted sql

--changeset gabriel.moraes:1

create table if not exists  cliente(
    id serial primary key,
    cnpj int not null,
    nome varchar(255) not null,
    email varchar(255) not null,
    ddd_telefone int,
    telefone int,
    bloqueado boolean,
    dat_ult_bloqueio timestamptz
);

create table if not exists contas_receber(
    id serial primary key,
    cliente_id integer references cliente(id),
status varchar(10) not null default 'pago',
forma_pagamento varchar(30),
num_parcela int,
codigo_banco int,
cod_remessa int,
cod_registro_lote int,
valor_multa numeric(8, 4),
valor_desconto numeric(8, 4),
valor_liquido_recebido numeric(8, 4),
valor_total numeric(8, 4),
pontos int,
servico_prestado varchar(255),
data_criacao timestamptz,
data_vencimento date,
data_credito timestamptz,
data_ocorrencia timestamptz
);

create table if not exists  endereco(
    id serial primary key,
    rua varchar(255),
    bairro varchar(255),
    cep int,
    numero int,
    uf char(2),
    cidade varchar(100),
    complemento text,
    cliente_id integer references cliente(id)
);

create table if not exists  motivo_comunicacao(
    id serial primary key,
    descricao varchar(255) not null
);

create table if not exists  comunicacao_cliente(
    id serial primary key,
    cod_cliente integer references cliente(id),
cod_motivo_comunicacao integer references motivo_comunicacao(id),
dt_envio timestamptz default now()
);

create table if not exists  nivel(
    id serial primary key,
    nivel varchar(30) not null,
    descricao varchar(255)
);

create table if not exists  cargo(
    id serial primary key,
    cargo varchar(30) not null,
    descricao varchar(255)
);

create table if not exists  usuario(
    id serial primary key,
    nome varchar(255) not null,
    email varchar(255) not null,
    login varchar(255) not null,
    senha varchar(255) not null,
    cargo_id integer references cargo(id),
cod_nivel integer references nivel(id)
);

create table if not exists  banco(
    codigo serial primary key,
    nome varchar(100)
);

create table if not exists  agencia(
    codigo serial primary key,
    gerente_email varchar(255),
    endereco text,
    telefone varchar(15),
    banco_cod integer references banco(codigo)
);

create table if not exists  conta(
    numero serial,
    tipo_conta varchar(30),
    agencia_cod integer references agencia(codigo),
banco_cod integer references banco(codigo),
cliente_id integer references cliente(id),
primary key(numero, agencia_cod, banco_cod)
);

create table if not exists  beneficio(
    id serial primary key,
    dt_validade date,
    dt_criacao timestamptz,
    descricao varchar(255),
    min_pontos int,
    valor_desconto numeric(8 ,4)
);

--rollback drop table if exists contas_receber cascade;
--rollback drop table if exists endereco cascade;
--rollback drop table if exists comunicacao_cliente cascade;
--rollback drop table if exists motivo_comunicacao cascade;
--rollback drop table if exists usuario cascade;
--rollback drop table if exists nivel cascade;
--rollback drop table if exists cargo cascade;
--rollback drop table if exists conta cascade;
--rollback drop table if exists agencia cascade;
--rollback drop table if exists banco cascade;
--rollback drop table if exists beneficio cascade;
--rollback drop table if exists cliente cascade;
