package com.techeasy.contas.receber.application;

import com.techeasy.contas.receber.domain.clientes.ClienteServiceImpl;
import com.techeasy.contas.receber.domain.clientes.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClientesController {


    @Autowired
    ClienteServiceImpl clienteService;

    @GetMapping
    public String index(Model model) {

        List<Cliente> clientes = clienteService.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes/index";
    }

    @GetMapping("view")
    public String view(@RequestParam(required = false) Long id, Model model) {

        if (id != null) {
            Optional<Cliente> cliente = clienteService.findOne(id);
            model.addAttribute("cliente", cliente.orElse(null));
        } else {
            model.addAttribute("cliente", new Cliente());
        }
        return "clientes/view";
    }

    @PostMapping("save")
    public String save(@ModelAttribute(value = "cliente") Cliente cliente) {

        if(cliente.isBloqueado() && cliente.getDatUltBloqueio() == null) {
            cliente.setDatUltBloqueio(new Date());
        }
        clienteService.save(cliente);
        return "redirect:/clientes";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Long id) {

        clienteService.delete(id);
        return ResponseEntity.ok("ok");
    }

}
