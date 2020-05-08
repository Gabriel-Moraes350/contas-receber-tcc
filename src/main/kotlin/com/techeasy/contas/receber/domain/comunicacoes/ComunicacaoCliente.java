package com.techeasy.contas.receber.domain.comunicacoes;

import com.techeasy.contas.receber.domain.clientes.model.Cliente;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class ComunicacaoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cod_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cod_motivo_comunicacao")
    private MotivoComunicacao motivoComunicacao;

    @Column(name = "dt_envio")
    private Date dataEnvio;
}
