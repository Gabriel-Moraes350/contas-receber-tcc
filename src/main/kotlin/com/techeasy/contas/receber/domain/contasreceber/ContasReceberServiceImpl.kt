package com.techeasy.contas.receber.domain.contasreceber

import com.techeasy.contas.receber.domain.contasreceber.model.ContasReceber
import com.techeasy.contas.receber.domain.contasreceber.model.FormaPagamento
import com.techeasy.contas.receber.domain.contasreceber.model.StatusRecebimento
import com.techeasy.contas.receber.infra.repositories.ContasReceberRepository
import org.springframework.stereotype.Service

@Service
class ContasReceberServiceImpl : ContasReceberService {

    private lateinit var contasReceberRepo: ContasReceberRepository

    constructor(contasReceberRepository: ContasReceberRepository) {
        contasReceberRepo = contasReceberRepository
    }

    override fun getAll(): List<ContasReceber> {
        return contasReceberRepo.findAll()
    }

    override fun getOne(id: Long): ContasReceber? {
        return contasReceberRepo.getOne(id)
    }

    override fun save(contasReceber: ContasReceber): ContasReceber {
        val saved = contasReceberRepo.save(contasReceber)

        return saved
    }


    override fun delete(id: Long) {
        contasReceberRepo.deleteById(id)
    }

    override fun getFormasPagamento(): List<FormaPagamento> {
        return FormaPagamento.values().asList()
    }

    override fun getStatus(): List<StatusRecebimento> {
        return StatusRecebimento.values().asList()
    }
}