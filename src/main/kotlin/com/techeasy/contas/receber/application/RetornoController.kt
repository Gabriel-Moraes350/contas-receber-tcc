package com.techeasy.contas.receber.application

import com.techeasy.contas.receber.domain.retorno.RetornoService
import com.techeasy.contas.receber.domain.retorno.model.RetornoContasReceberRelation
import org.apache.commons.io.IOUtils
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.io.ByteArrayInputStream
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/retorno")
class RetornoController(private val retornoService: RetornoService) {

    companion object {
        @JvmStatic
        private val PREFIX = "retorno"
        private val log = LoggerFactory.getLogger(RetornoController.javaClass)

    }

    @PostMapping("importar")
    fun importar(@RequestParam("file") file: MultipartFile,
                 redirectAttributes: RedirectAttributes): String {

        val fileName = retornoService.importar(file)
        redirectAttributes.addFlashAttribute("message",
            "Arquivo: $fileName importado com sucesso!");
        return "redirect:/retorno/history"
    }

    @GetMapping("history")
    fun listFiles(model: Model): String {
        model["retornos"] = retornoService.getHistory()


        return "$PREFIX/history"
    }

    @GetMapping("{id}/details")
    @ResponseBody
    fun getDetails(@PathVariable("id") id:Long): ResponseEntity<MutableList<RetornoContasReceberRelation>> {
        return ResponseEntity(retornoService.getContasReceberRelationById(id), HttpStatus.OK)
    }

    @GetMapping("file/{id}")
    fun getFile(@PathVariable("id") id: Long, response: HttpServletResponse): HttpServletResponse {

        val retorno = retornoService.getDetailsOne(id)

        response.setHeader("Content-disposition", "attachment; filename=${retorno.name}")
        response.contentType = "text/plain; charset=ISO-8859-1"
        val inputStream = ByteArrayInputStream(retorno.file)
        IOUtils.copy(inputStream, response.outputStream)
        response.flushBuffer()
        return response
    }
}