package com.techeasy.contas.receber.application

import com.techeasy.contas.receber.domain.contasreceber.ContasReceberService
import com.techeasy.contas.receber.domain.remessa.RemessaService
import com.techeasy.contas.receber.domain.remessa.model.RemessaContasReceberRelation
import org.apache.commons.io.IOUtils
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import java.io.ByteArrayInputStream
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/remessa")
class RemessaController(private val contasReceberService: ContasReceberService,
                        private val remessaService: RemessaService) {

    companion object {
        @JvmStatic
        private val PREFIX = "remessa"
        private val log = LoggerFactory.getLogger(RemessaController.javaClass)

    }

    @GetMapping
    fun index(model: Model): String {
        val contasReceber = contasReceberService.getAguardando()

        model.set("contasReceber", contasReceber)

        return "$PREFIX/index"
    }

    @PostMapping("gerar")
    fun gerar(@RequestParam("contasRemessa") contasGerarRemessa: List<Long>): String {
        log.info(contasGerarRemessa.toString());
        remessaService.generateRemessa(contasGerarRemessa)

        return "redirect:/remessa/history"
    }

    @GetMapping("history")
    fun listFiles(model: Model): String {
        model["remessas"] = remessaService.getHistory()

        return "$PREFIX/history"
    }

    @GetMapping("{id}/details")
    @ResponseBody
    fun getDetails(@PathVariable("id") id:Long): ResponseEntity<List<RemessaContasReceberRelation>>{
        return ResponseEntity(remessaService.getRelationDetailsByRemessaId(id), HttpStatus.OK)
    }

    @GetMapping("file/{id}")
    fun getFile(@PathVariable("id") id: Long, response: HttpServletResponse): HttpServletResponse {

        val remessa = remessaService.getDetailsOne(id)

        response.setHeader("Content-disposition", "attachment; filename=${remessa.name}")
        response.contentType = "text/plain; charset=ISO-8859-1"
        val inputStream = ByteArrayInputStream(remessa.file)
        IOUtils.copy(inputStream, response.outputStream)
        response.flushBuffer()
        return response
    }
}