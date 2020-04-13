package com.techeasy.contas.receber.clientes

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Controller {

    @GetMapping
    fun index(): String{
        return "index";
    }
}