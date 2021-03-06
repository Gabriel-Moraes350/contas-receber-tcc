package com.techeasy.contas.receber.domain.comunicacoes.model;

import com.techeasy.contas.receber.domain.clientes.model.Cliente;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
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

    @Column(name = "motivo_comunicacao")
    private String motivoComunicacao;

    @Column(name = "dt_envio")
    private Date dataEnvio = new Date();
}
