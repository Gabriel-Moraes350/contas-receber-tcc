package com.techeasy.contas.receber.application

import com.techeasy.contas.receber.domain.clientes.ClienteServiceImpl
import com.techeasy.contas.receber.domain.contasreceber.ContasReceberService
import com.techeasy.contas.receber.domain.contasreceber.model.ContasReceber
import org.slf4j.LoggerFactory
import org.springframework.beans.propertyeditors.CustomNumberEditor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.ServletRequestDataBinder
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.OffsetDateTime
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("contas-receber")
class ContasReceberController(private var contasReceberService: ContasReceberService, private var clienteService: ClienteServiceImpl) {


    companion object {
        @JvmStatic
        private val PREFIX = "contas-receber"
        private val log = LoggerFactory.getLogger(ContasReceberController.javaClass)
    }

    @InitBinder
    @Throws(Exception::class)
    fun initBinder(request: HttpServletRequest?, binder: ServletRequestDataBinder) {
        val df = DecimalFormat()
        val dfs = DecimalFormatSymbols()
        dfs.decimalSeparator = ','
        dfs.groupingSeparator = '.'
        df.decimalFormatSymbols = dfs
        df.maximumFractionDigits = 32
        df.maximumIntegerDigits = 32
        binder.registerCustomEditor(BigDecimal::class.java, CustomNumberEditor(BigDecimal::class.java, df, true))
    }

    @GetMapping
    fun index(model: Model): String {

        val contasReceber = contasReceberService.getAll()

        model.set("contasReceber", contasReceber.sortedBy { it.dataVencimento })

        return "$PREFIX/index"
    }

    @GetMapping("view")
    fun view(@RequestParam(name = "id", required = false) id: Long?,
             model: Model): String {


        if (id != null) {
            val contaReceber = contasReceberService.getOne(id)
            contaReceber?.let { model["contaReceber"] = contaReceber }
        } else {
            model["contaReceber"] = ContasReceber()
        }

        model["allStatus"] = contasReceberService.getStatus()
        model["formasPagamento"] = contasReceberService.getFormasPagamento()
        model["clientes"] = clienteService.findAll()
        model["tiposServico"] = contasReceberService.getTipoServico()

        return "$PREFIX/view"
    }

    @PostMapping("save")
    fun save(@ModelAttribute(value = "contaReceber") contaReceber: ContasReceber): String {
        log.info(contaReceber.toString());
        contaReceber.dataAlteracao = OffsetDateTime.now()
        contasReceberService.save(contaReceber);
        return "redirect:/$PREFIX"
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    fun delete(@PathVariable id: Long): ResponseEntity<String> {
        contasReceberService.delete(id)

        return ResponseEntity.ok("ok")
    }
}