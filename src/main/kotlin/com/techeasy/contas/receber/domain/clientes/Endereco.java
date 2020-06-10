package com.techeasy.contas.receber.domain.clientes;

import com.techeasy.contas.receber.domain.clientes.model.Cliente;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rua;

    private String bairro;

    private String cep;

    private Integer numero;

    @Column(length = 2)
    private String uf;

    private String cidade;

    private String complemento;
}