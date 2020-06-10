package com.techeasy.contas.receber.domain.remessa.model

import lombok.Builder

@Builder
data class Remessa(
        var header: Header,
        var dadosTitulos: List<DadosTitulo>
)