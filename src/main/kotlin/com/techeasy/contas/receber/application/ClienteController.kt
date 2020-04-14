package com.techeasy.contas.receber.application

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("cliente")
class ClienteController {

    @GetMapping
    fun index(): String {
        return "index";
    }
}