package com.techeasy.contas.receber.application;

import com.techeasy.contas.receber.domain.clientes.ClienteServiceImpl;
import com.techeasy.contas.receber.domain.clientes.model.Cliente;
import com.techeasy.contas.receber.domain.contasreceber.model.ContasReceber;
import com.techeasy.contas.receber.infra.repositories.ContasReceberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    ClienteServiceImpl clienteService;

    @Autowired
    ContasReceberRepository contasReceberRepository;

    DateTimeFormatter FORMAT = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
            .parseDefaulting(ChronoField.OFFSET_SECONDS, 0)
            .toFormatter();

    @GetMapping
    public String index(Model model) {

        List<Cliente> clientes = clienteService.findAll();
        List<ContasReceber> contas = new ArrayList<>();
        model.addAttribute("clientes", clientes);
        model.addAttribute("contas", contas);
        return "consulta/index";
    }

    @PostMapping("/buscar")
    @ResponseBody
    public ResponseEntity<List<ContasReceber>> search(@RequestParam Long idCliente, @RequestParam String inicio, @RequestParam String fim,
                                                      @RequestParam String status) {

        OffsetDateTime dataInicio = ZonedDateTime.parse(inicio, FORMAT).toOffsetDateTime();
        OffsetDateTime dataFim = ZonedDateTime.parse(fim, FORMAT).toOffsetDateTime();

        switch (status) {
            case "pago":
                return new ResponseEntity<>(contasReceberRepository.findAllByClientePago(idCliente, dataInicio, dataFim), HttpStatus.OK);
            case "avencer":
                return new ResponseEntity<>(contasReceberRepository.findAllPendentesByCliente(idCliente, dataInicio, dataFim), HttpStatus.OK);
            case "vencidos":
                return new ResponseEntity<>(contasReceberRepository.findAllByClienteVencido(idCliente, dataInicio, dataFim), HttpStatus.OK);
        }
        return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);



    }
}
