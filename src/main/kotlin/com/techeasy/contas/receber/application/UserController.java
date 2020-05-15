package com.techeasy.contas.receber.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/usuario")
public class UserController {
    @GetMapping
    String index() {
        return "usuario";
    }
}
