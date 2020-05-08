package com.techeasy.contas.receber.domain.contas;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(of = "codigo")
@Entity
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "gerente_email")
    private String gerenteEmail;

    private String endereco;

    private String telefone;

    @ManyToOne
    @JoinColumn(name = "banco_cod")
    private Banco banco;
}
