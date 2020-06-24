package com.techeasy.contas.receber.application;


import com.techeasy.contas.receber.domain.clientes.ClienteServiceImpl;
import com.techeasy.contas.receber.domain.comunicacoes.ComunicacaoClienteServiceImpl;
import com.techeasy.contas.receber.domain.comunicacoes.SenderMailService;
import com.techeasy.contas.receber.domain.comunicacoes.model.ComunicacaoCliente;
import com.techeasy.contas.receber.domain.comunicacoes.model.MotivoEnvio;
import com.techeasy.contas.receber.domain.contasreceber.ContasReceberServiceImpl;
import com.techeasy.contas.receber.domain.contasreceber.model.ContasReceber;
import com.techeasy.contas.receber.domain.contasreceber.model.StatusRecebimento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/comunicacao-cliente")
public class ComunicacaoClienteController {
    @Autowired
    private SenderMailService mailService;

    @Autowired
    private ComunicacaoClienteServiceImpl comunicacaoService;

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private ContasReceberServiceImpl contasService;


    Logger log = LoggerFactory.getLogger(ComunicacaoClienteController.class);
    final String kPrefix = "comunicacao-cliente";

    ComunicacaoClienteController(
            SenderMailService mailService,
            ComunicacaoClienteServiceImpl comunicacaoService,
            ClienteServiceImpl clienteService,
            ContasReceberServiceImpl contasService) {
        this.mailService = mailService;
        this.comunicacaoService = comunicacaoService;
        this.clienteService = clienteService;
        this.contasService = contasService;
    }

    @InitBinder
    private void InitBinder(HttpServletRequest request, ServletRequestDataBinder binder) {

    }

    @GetMapping
    String index(Model model) {
        List<ComunicacaoCliente> listComunicacao = comunicacaoService.getAll();
        model.addAttribute("comunicacaoCliente", listComunicacao);

        return kPrefix + "/index";
    }

    @GetMapping("view")
    String view(@RequestParam(name = "id", required = false) Long id, Model model) {
        if (id != null) {
            Optional<ComunicacaoCliente> comunicacaoCliente = comunicacaoService.findOne(id);
            model.addAttribute("comunicacaoCliente", comunicacaoCliente.orElse(null));
        } else {
            model.addAttribute("comunicacaoCliente", new ComunicacaoCliente());
        }

        model.addAttribute("clientes", clienteService.getAll());
        model.addAttribute("motivoComunicacao", comunicacaoService.getMotivoComunicacao());

        return kPrefix + "/view";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Long id) {
        comunicacaoService.delete(id);
        return ResponseEntity.ok("ok");
    }

    boolean checkConta(ContasReceber conta, MotivoEnvio motivo) {
        boolean result = false;

        switch (motivo) {
            case Antecipaçao:
                if (conta.getStatus() != StatusRecebimento.expirado && conta.getStatus() != StatusRecebimento.pago) {
                    result = true;
                }
                break;
            case Reenvio:
            case Aviso:
                result = true;
                break;
            case Atraso:
                if (conta.getStatus() == StatusRecebimento.expirado) {
                    result = true;
                }
                break;
        }

        return result;
    }

    @GetMapping("{clienteId}/{motivo}/tabela-contas")
    @ResponseBody
    public ResponseEntity<List<ContasReceber>> getContasToEmail(@PathVariable("clienteId") Long clienteId,
                                                                @PathVariable("motivo") String motivo) {
        List<ContasReceber> contas = new ArrayList<>();

        for (ContasReceber conta : contasService.getAll()) {
            if (conta.getCliente().getId().equals(clienteId)) {
                if (checkConta(conta, MotivoEnvio.valueOf(motivo))) {
                    contas.add(conta);
                }
            }
        }

        return ResponseEntity.ok(contas);
    }

    @ResponseBody
    @PostMapping("sendMail")
    public ResponseEntity sendMail(@RequestBody Map<String, Object> contasListId) {
        List<String> contas = (ArrayList<String>) contasListId.get("contas");
        ComunicacaoCliente comunicacaoCliente = new ComunicacaoCliente();
        String motivo = (String) contasListId.get("motivo");
        List<ContasReceber> listContas = new ArrayList<>();

        log.info(contas.toString());
        log.info(motivo);

        for (String conta: contas) {
            listContas.add(contasService.getOne(Long.parseLong(conta)));
        }

        comunicacaoCliente.setCliente(listContas.get(0).getCliente());
        comunicacaoCliente.setMotivoComunicacao((String)contasListId.get("motivo"));
        comunicacaoService.save(comunicacaoCliente);

        mailService.send(comunicacaoCliente, listContas);
        return ResponseEntity.ok().build();
    }

}
