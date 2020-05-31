package com.techeasy.contas.receber.domain.clientes.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cnpj;

    private String tipo;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Email
    private String email;

    @Column(name = "ddd_telefone")
    private int dddTelefone;

    private int telefone;

    @Column(name = "inscricao_estadual")
    private String inscricaoEstadual;

    private boolean bloqueado;

    @Column(name = "dat_ult_bloqueio")
    private Date datUltBloqueio;
}
