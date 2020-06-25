package com.techeasy.contas.receber.application;

import com.techeasy.contas.receber.domain.beneficios.BeneficioService;
import com.techeasy.contas.receber.domain.beneficios.model.Beneficio;
import com.techeasy.contas.receber.domain.beneficios.model.BeneficioType;
import com.techeasy.contas.receber.domain.clientes.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/beneficios")
public class BeneficiosController {

    @Autowired
    ClienteServiceImpl clienteService;

    @Autowired
    BeneficioService beneficioService;

    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        df.setMaximumFractionDigits(32);
        df.setMaximumIntegerDigits(32);
        binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, df, true));
    }

    @GetMapping
    public String index(Model model) {

        List<Beneficio> beneficios = beneficioService.listAll();
        model.addAttribute("beneficios", beneficios);
        return "beneficios/index";
    }

    @GetMapping("view")
    public String view(@RequestParam(required = false) Long id, Model model) {

        if (id != null) {
            Optional<Beneficio> beneficio = beneficioService.findOne(id);
            model.addAttribute("beneficio", beneficio.orElse(null));
        } else {
            model.addAttribute("beneficio", new Beneficio());
        }

        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("beneficioTypes", BeneficioType.values());

        return "beneficios/view";
    }

    @PostMapping("save")
    public String save(@ModelAttribute(value = "beneficio") Beneficio beneficio) {

        if (beneficio.getDescricao().equals(BeneficioType.BLOCK)) {
            clienteService.bloquear(beneficio.getCliente());
        }
        beneficioService.save(beneficio);
        return "redirect:/beneficios";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Long id) {

        Optional<Beneficio> beneficio = beneficioService.findOne(id);
        if (beneficio.get().getDescricao().equals(BeneficioType.BLOCK)) {
            clienteService.desbloquear(beneficio.get().getCliente());
        }
        beneficioService.delete(id);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Beneficio>> buscar(@PathVariable String id) {

        Long clienteId = Long.parseLong(id);
        try {
            return new ResponseEntity<>(beneficioService.findByCliente(clienteId), HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
