package com.techeasy.contas.receber.domain.remessa.model

import org.springframework.data.annotation.CreatedDate
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "remessa_contas_receber")
data class RemessaContasReceberRelation(
        @EmbeddedId
        var remessaContaReceberId: RemessaContasReceberId? = null,

        var totalAmount: BigDecimal? = null,

        @CreatedDate
        var createdAt: LocalDateTime = LocalDateTime.now()
)