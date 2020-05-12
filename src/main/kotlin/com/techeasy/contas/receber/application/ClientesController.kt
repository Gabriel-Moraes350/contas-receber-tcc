package com.techeasy.contas.receber.application

import com.techeasy.contas.receber.domain.clientes.model.ClienteService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("clientes")
class ClientesController {

    private lateinit var clienteService: ClienteService

    constructor(clienteService: ClienteService) {
        this.clienteService = clienteService
    }

}