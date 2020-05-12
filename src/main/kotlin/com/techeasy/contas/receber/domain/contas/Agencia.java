package com.techeasy.contas.receber.domain.contas;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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
