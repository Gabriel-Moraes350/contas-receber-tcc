package com.techeasy.contas.receber.domain.remessa.model

import java.time.LocalDateTime


interface RemessaHistoryDTO {
    fun getId(): Long
    fun getName(): String
    fun getCreatedAt(): LocalDateTime
}