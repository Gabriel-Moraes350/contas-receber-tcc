package com.techeasy.contas.receber.domain.clientes;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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

    public Long getId() {
        return id;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getUf() {
        return uf;
    }

    public String getCidade() {
        return cidade;
    }

    public String getComplemento() {
        return complemento;
    }
}