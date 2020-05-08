package com.techeasy.contas.receber.domain.clientes.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cnpj;

    private String nome;

    private String email;

    @JoinColumn(name = "cargo_id")
    private int dddTelefone;

    private int telefone;

    private boolean bloqueado;

    @Column(name = "dat_ult_bloqueio")
    private Date datUltBloqueio;
}
