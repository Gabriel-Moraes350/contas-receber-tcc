package com.techeasy.contas.receber.application;

import com.techeasy.contas.receber.domain.beneficios.BeneficioService;
import com.techeasy.contas.receber.domain.beneficios.model.Beneficio;
import com.techeasy.contas.receber.domain.beneficios.model.BeneficioType;
import com.techeasy.contas.receber.domain.clientes.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/beneficios")
public class BeneficiosController {

    @Autowired
    ClienteServiceImpl clienteService;

    @Autowired
    BeneficioService beneficioService;

    @GetMapping
    public String index(Model model) {

        List<Beneficio> beneficios = beneficioService.listAll();
        model.addAttribute("beneficios", beneficios);
        return "beneficios/index";
    }

    @GetMapping("view")
    public String view(@RequestParam(required = false) Long id, Model model) {

        if (id != null) {
            //TODO
        } else {
            model.addAttribute("beneficio", new Beneficio());
        }

        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("beneficioTypes", BeneficioType.values());

        return "beneficios/view";
    }

}
