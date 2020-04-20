package com.techeasy.contas.receber.application;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteJavaController {

    @GetMapping
    public String index(){
        return "teste";
    }
}
