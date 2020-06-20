package com.techeasy.contas.receber.infra.repositories

import com.techeasy.contas.receber.domain.contasreceber.model.ContasReceber
import com.techeasy.contas.receber.domain.contasreceber.model.StatusRecebimento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
interface ContasReceberRepository : JpaRepository<ContasReceber, Long> {

    @Query("select * from contas_receber where status in ('aguardando', 'erro', 'expirado') " +
            " and forma_pagamento = 'boleto' and deleted_at is null " +
            "order by data_vencimento", nativeQuery = true)
    fun findPending(): List<ContasReceber>

    @Query("select c from ContasReceber c where c.id in :ids and deletedAt is null")
    fun findAllIn(@Param("ids") ids: List<Long>): List<ContasReceber>

    @Modifying
    @Query("update from ContasReceber set status = 'enviado' where id in :ids and deletedAt is null")
    fun updateContasReceberRemessaStatus(@Param("ids") ids: List<Long>)

    @Query("select * from contas_receber c where c.num_documento = :numeroTitulo and deleted_at is null and status = 'enviado' order by data_alteracao desc limit 1", nativeQuery = true)
    fun findFirstByNumeroTitulo(@Param("numeroTitulo") numero: String): ContasReceber

    @Modifying
    @Query("update from ContasReceber c set c.deletedAt = :now where c.id = :id")
    fun setDeletedAt(id: Long, now: OffsetDateTime)

    @Query("select c from ContasReceber c where deletedAt is null")
    fun findAllExceptDeleted(): List<ContasReceber>

    @Query("select * from contas_receber c where c.cliente_id = :clienteId and c.data_criacao between :dataInicio and :dataFim and c.status = 'pago' and deleted_at is null", nativeQuery = true)
    fun findAllByClientePago(clienteId: Long, dataInicio: OffsetDateTime, dataFim: OffsetDateTime): List<ContasReceber>

    @Query("select * from contas_receber c where c.cliente_id = :clienteId and c.data_criacao between :dataInicio and :dataFim and " +
            "(c.status = 'enviado' or c.status = 'aguardando') and c.data_vencimento >= now() and deleted_at is null", nativeQuery = true)
    fun findAllPendentesByCliente(clienteId: Long, dataInicio: OffsetDateTime, dataFim: OffsetDateTime): List<ContasReceber>

    @Query(value = """
        select * from contas_receber c where c.cliente_id = :clienteId and c.data_criacao between :dataInicio and :dataFim and 
            (c.status = 'enviado' or c.status = 'aguardando') and c.data_vencimento < now() and deleted_at is null
    """, nativeQuery = true)
    fun findAllByClienteVencido(clienteId: Long, dataInicio: OffsetDateTime, dataFim: OffsetDateTime): List<ContasReceber>

    @Modifying
    @Query("update contas_receber set status = :status where deleted_at is null and data_vencimento <= now()  and status != 'pago' ",nativeQuery = true)
    fun updateStatusVencidas(@Param("status") status: String = StatusRecebimento.expirado.toString())
}