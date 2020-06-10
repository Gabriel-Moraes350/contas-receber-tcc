package com.techeasy.contas.receber.domain.clientes.model;

public enum TipoCliente {
    F("CPF"), J("CNPJ");

    private String description;

    TipoCliente(String description) {
        this.description = description;
    }
}
