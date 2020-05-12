package com.techeasy.contas.receber.domain.clientes

import com.techeasy.contas.receber.domain.clientes.model.Cliente
import com.techeasy.contas.receber.domain.clientes.model.ClienteService
import com.techeasy.contas.receber.infra.repositories.ClienteRepository
import org.springframework.stereotype.Service

@Service
class ClienteServiceImpl : ClienteService {

    private lateinit var clienteRepository: ClienteRepository

    constructor(clienteRepository: ClienteRepository) {
        this.clienteRepository = clienteRepository
    }

    override fun getAll(): List<Cliente> {
        return clienteRepository.findAll()
    }

}