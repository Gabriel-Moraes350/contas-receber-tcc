package com.techeasy.contas.receber.domain.retorno

import com.techeasy.contas.receber.domain.contasreceber.ContasReceberService
import com.techeasy.contas.receber.domain.contasreceber.model.ContasReceber
import com.techeasy.contas.receber.domain.retorno.model.RetornoContasReceberId
import com.techeasy.contas.receber.domain.retorno.model.RetornoContasReceberRelation
import com.techeasy.contas.receber.domain.retorno.model.RetornoHistory
import com.techeasy.contas.receber.domain.retorno.model.RetornoHistoryDTO
import com.techeasy.contas.receber.infra.repositories.RetornoContasReceberRelationRepository
import com.techeasy.contas.receber.infra.repositories.RetornoHistoryRepository
import com.techeasy.contas.receber.infra.retorno.RetornoWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime


@Service
class RetornoService {

    @Autowired
    private lateinit var contasReceberService: ContasReceberService

    @Autowired
    private lateinit var retornoContasReceberRelationRepository: RetornoContasReceberRelationRepository

    @Autowired
    private lateinit var retornoPort: RetornoPort

    @Autowired
    private lateinit var retornoHistoryRepository: RetornoHistoryRepository

    fun getHistory(): List<RetornoHistoryDTO> {
        return retornoHistoryRepository.listAllWithoutBlob()
    }

    @Transactional
    fun importar(retornoFile: MultipartFile): String {

        val retornoWrapper = retornoPort.importar(retornoFile)
        val history = this.saveHistoryRetorno(retornoWrapper, retornoFile.bytes)
        this.updateContasReceber(retornoWrapper.arquivoRetorno, history)

        return retornoWrapper.fileName
    }

    private fun updateContasReceber(arquivoRetorno: ArquivoRetorno, history: RetornoHistory) {
        arquivoRetorno.transacoes.forEach { transacao ->
            val contasReceber = contasReceberService.getFirstByNumeroTitulo(transacao.numeroTituloDadoPeloCedente)
            contasReceber?.let {
                contasReceberService.updateFromRetorno(transacao, contasReceber)
                this.createRelationContasReceberRetorno(contasReceber, history, transacao)
            }

        }
    }

    private fun createRelationContasReceberRetorno(contaReceber: ContasReceber, retorno: RetornoHistory, transacao: TransacaoDeTitulo){
        val id = RetornoContasReceberId()
        id.contaReceberId = contaReceber.id
        id.retornoId = retorno.id
        val retornoRelation = RetornoContasReceberRelation(id, transacao.valorRecebido, LocalDateTime.now())

        retornoContasReceberRelationRepository.save(retornoRelation)
    }

    private fun saveHistoryRetorno(retornoWrapper: RetornoWrapper, bytes: ByteArray): RetornoHistory {

        val retornoHistory = RetornoHistory(name = retornoWrapper.fileName,
                file = bytes,
                createdAt = LocalDateTime.now())
        return retornoHistoryRepository.save(retornoHistory)
    }


    fun getDetailsOne(id: Long): RetornoHistory {
        return retornoHistoryRepository.getOne(id)
    }

    fun getContasReceberRelationById(id: Long): MutableList<RetornoContasReceberRelation>? {
        return retornoContasReceberRelationRepository.findByContaReceberId(id)
    }
}