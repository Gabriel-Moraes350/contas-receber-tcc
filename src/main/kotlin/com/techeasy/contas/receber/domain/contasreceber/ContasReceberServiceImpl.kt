package com.techeasy.contas.receber.domain.contasreceber

import com.techeasy.contas.receber.domain.contasreceber.model.ContasReceber
import com.techeasy.contas.receber.domain.contasreceber.model.FormaPagamento
import com.techeasy.contas.receber.domain.contasreceber.model.StatusRecebimento
import com.techeasy.contas.receber.domain.contasreceber.model.TipoServicoPrestado
import com.techeasy.contas.receber.domain.retorno.TransacaoDeTitulo
import com.techeasy.contas.receber.infra.repositories.ContasReceberRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*
import javax.transaction.Transactional

@Service
class ContasReceberServiceImpl(contasReceberRepository: ContasReceberRepository) : ContasReceberService {

    companion object{
        @JvmStatic
        private val log: Logger = LoggerFactory.getLogger(ContasReceberServiceImpl.javaClass)

        @JvmStatic
        private val TIMEZONE = "-03:00"
    }

    private var contasReceberRepo: ContasReceberRepository = contasReceberRepository

    override fun getAll(): List<ContasReceber> {
        return contasReceberRepo.findAllExceptDeleted()
    }

    override fun updateFromRetorno(transacaoDeTitulo: TransacaoDeTitulo, contasReceber: ContasReceber) {
        contasReceber.dataAlteracao = OffsetDateTime.now()
        contasReceber.dataCredito = getLocalDate(transacaoDeTitulo.dataCredito)
        contasReceber.dataLiquidacao = getLocalDate(transacaoDeTitulo.dataLiquidacao)
        contasReceber.valorLiquidoRecebido = transacaoDeTitulo.valorRecebido
        contasReceber.valorDesconto = transacaoDeTitulo.descontoConcedido
        contasReceber.status = StatusRecebimento.pago

        contasReceberRepo.save(contasReceber)
    }

    private fun getLocalDate(date: Date) =
            date.toInstant().atOffset(ZoneOffset.of(TIMEZONE)).toLocalDate()

    override fun getOne(id: Long): ContasReceber? {
        return contasReceberRepo.getOne(id)
    }

    override fun save(contasReceber: ContasReceber): ContasReceber {
        val saved = contasReceberRepo.save(contasReceber)

        return saved
    }

    @Transactional
    override fun delete(id: Long) {
        contasReceberRepo.setDeletedAt(id, OffsetDateTime.now())
    }

    override fun getFormasPagamento(): List<FormaPagamento> {
        return FormaPagamento.values().asList()
    }

    override fun getStatus(): List<StatusRecebimento> {
        return StatusRecebimento.values().asList()
    }

    override fun getAguardando(): List<ContasReceber> {

        return contasReceberRepo.findPending();
    }

    override fun updateContasReceberRemessaStatus(ids: List<Long>) {
        contasReceberRepo.updateContasReceberRemessaStatus(ids)
    }

    override fun getFirstByNumeroTitulo(numeroTitulo: String): ContasReceber? {
        try{
            return contasReceberRepo.findFirstByNumeroTitulo(numeroTitulo.trim())
        }catch(e: EmptyResultDataAccessException){
            log.info("Conta receber not found")
            return null
        }
    }

    override fun getTipoServico(): List<TipoServicoPrestado> {
        return TipoServicoPrestado.values().toList()
    }
}