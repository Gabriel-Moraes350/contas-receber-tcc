package com.techeasy.contas.receber.domain.retorno.model

import java.time.LocalDateTime


interface RetornoHistoryDTO {
    fun getId(): Long
    fun getName(): String
    fun getCreatedAt(): LocalDateTime
}