package com.techeasy.contas.receber.domain.remessa

import com.techeasy.contas.receber.domain.contasreceber.ContasReceberService
import com.techeasy.contas.receber.domain.contasreceber.model.ContasReceber
import com.techeasy.contas.receber.domain.remessa.model.*
import com.techeasy.contas.receber.infra.repositories.ContasReceberRepository
import com.techeasy.contas.receber.infra.repositories.RemessaContasReceberRelationRepository
import com.techeasy.contas.receber.infra.repositories.RemessaHistoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.File
import java.io.FileInputStream

@Service
class RemessaService {

    @Autowired
    private lateinit var contasReceberRepository: ContasReceberRepository

    @Autowired
    private lateinit var contasReceberService: ContasReceberService

    @Autowired
    private lateinit var remessaContaReceberRelationRepository: RemessaContasReceberRelationRepository

    @Autowired
    private lateinit var remessaPort: RemessaPort

    @Autowired
    private lateinit var remessaHistoryRepository: RemessaHistoryRepository

    fun getHistory(): List<RemessaHistoryDTO> {
        return remessaHistoryRepository.listAllWithoutBlob()
    }

    @Transactional
    fun generateRemessa(contasReceberForm: List<Long>): Unit {
        val contasReceber = contasReceberRepository.findAllIn(contasReceberForm)

        val dadosTitulos = contasReceber.map { DadosTitulo(it, it.cliente) }
        val remessa = Remessa(Header(1), dadosTitulos)

        val filePath = remessaPort.generate(remessa)

        saveHistoryRemessa(filePath, contasReceber)
        contasReceberService.updateContasReceberRemessaStatus(contasReceberForm)
    }

    private fun saveHistoryRemessa(filePath: String, contasReceber: List<ContasReceber>) {
        val fileName = filePath.substring(filePath.lastIndexOf("/") + 1)
        val inputStream = FileInputStream(File(filePath))
        val history = RemessaHistory(name = fileName, file = inputStream.readAllBytes())
        val savedHistory = remessaHistoryRepository.save(history)

        contasReceber.forEach {
            val relationId = RemessaContasReceberId()
            relationId.contaReceberId = it.id
            relationId.remessaId = savedHistory.id

            remessaContaReceberRelationRepository.save(RemessaContasReceberRelation(relationId, totalAmount = it.valorTotal))
        }

    }

    fun getDetailsOne(id: Long): RemessaHistory {
        return remessaHistoryRepository.getOne(id)
    }

    fun getRelationDetailsByRemessaId(id: Long): MutableList<RemessaContasReceberRelation>? {
        return remessaContaReceberRelationRepository.findByRemessaId(id)
    }
}