package com.techeasy.contas.receber.application

import com.techeasy.contas.receber.infra.boleto.BoletoAdapter
import com.techeasy.contas.receber.infra.repositories.ContasReceberRepository
import org.apache.commons.io.IOUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayInputStream
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/boleto")
class BoletoController(val boletoAdapter: BoletoAdapter, val contaReceberRepo: ContasReceberRepository) {

    @GetMapping("/{contaReceber}")
    fun gerarBoletoConta(@PathVariable contaReceber: Long, response: HttpServletResponse){
        val contaReceberModel = contaReceberRepo.findById(contaReceber)

        val boletoFile = boletoAdapter.gerarBoleto(contaReceberModel.orElseThrow())

        response.setHeader("Content-disposition", "attachment; filename=boleto-conta-${contaReceberModel.get().numDocumento}")
        response.contentType = "text/plain; charset=ISO-8859-1"
        val inputStream = ByteArrayInputStream(boletoFile?.readBytes())
        IOUtils.copy(inputStream, response.outputStream)
        response.flushBuffer()
    }

}