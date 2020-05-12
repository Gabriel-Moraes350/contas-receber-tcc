package com.techeasy.contas.receber.domain.clientes.model


interface ClienteService {
    fun getAll(): List<Cliente>
}