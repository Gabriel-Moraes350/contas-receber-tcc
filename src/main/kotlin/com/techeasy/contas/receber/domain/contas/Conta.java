package com.techeasy.contas.receber.domain.contas;

import com.techeasy.contas.receber.domain.clientes.model.Cliente;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@EqualsAndHashCode(of = "contaId")
@Entity
public class Conta {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ContaId contaId;

    @JoinColumn(name = "tipo_conta")
    private String tipoConta;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Data
    @EqualsAndHashCode
    @Embeddable
    public static class ContaId implements Serializable {

        private Long numero;

        @Column(name = "agencia_cod")
        private Long agencia;

        @Column(name = "banco_cod")
        private Long banco;

        public ContaId(Long agencia, Long banco, Long numero) {
            this.agencia = agencia;
            this.banco = banco;
            this.numero = numero;
        }

        public ContaId(){}

    }
}

