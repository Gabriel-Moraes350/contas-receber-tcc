package com.techeasy.contas.receber.domain.retorno.model

import org.springframework.data.annotation.CreatedDate
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "retorno_contas_receber")
data class RetornoContasReceberRelation(
        @EmbeddedId
        var retornoContaReceberId: RetornoContasReceberId? = null,

        var totalAmount: BigDecimal? = null,

        @CreatedDate
        var createdAt: LocalDateTime = LocalDateTime.now()
)