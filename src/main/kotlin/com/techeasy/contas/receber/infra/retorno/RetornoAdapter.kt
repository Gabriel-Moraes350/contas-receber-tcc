package com.techeasy.contas.receber.infra.retorno

import com.techeasy.contas.receber.domain.retorno.ArquivoRetorno
import com.techeasy.contas.receber.domain.retorno.RetornoPort
import org.apache.commons.io.FileUtils
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream

@Service
class RetornoAdapter: RetornoPort {
    override fun importar(fileRetorno: MultipartFile): RetornoWrapper? {
        val file = File.createTempFile("temp", fileRetorno.originalFilename)
        val writter = FileOutputStream(file)
        writter.write(fileRetorno.bytes)

        val lines = FileUtils.readLines(file, "UTF-8")
        val arquivo = ArquivoRetorno.newInstance(lines)

        return RetornoWrapper(fileRetorno.originalFilename?: file.name, arquivo)
    }


}