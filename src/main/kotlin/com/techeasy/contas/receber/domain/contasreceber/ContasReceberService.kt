package com.techeasy.contas.receber.domain.contasreceber

import com.techeasy.contas.receber.domain.contasreceber.model.ContasReceber
import com.techeasy.contas.receber.domain.contasreceber.model.FormaPagamento
import com.techeasy.contas.receber.domain.contasreceber.model.StatusRecebimento
import com.techeasy.contas.receber.domain.contasreceber.model.TipoServicoPrestado
import com.techeasy.contas.receber.domain.retorno.TransacaoDeTitulo

interface ContasReceberService {
    fun getAll(): List<ContasReceber>

    fun getOne(id: Long): ContasReceber?

    fun save(contasReceber: ContasReceber): ContasReceber

    fun delete(id: Long): Unit

    fun getFormasPagamento(): List<FormaPagamento>

    fun getStatus(): List<StatusRecebimento>

    fun getAguardando(): List<ContasReceber>

    fun getTipoServico(): List<TipoServicoPrestado>

    fun updateContasReceberRemessaStatus(ids: List<Long>): Unit

    fun getFirstByNumeroTitulo(numeroTitulo: String): ContasReceber?

    fun updateFromRetorno(transacaoDeTitulo: TransacaoDeTitulo, contasReceber: ContasReceber)
}
