package com.techeasy.contas.receber.infra.retorno

import com.techeasy.contas.receber.domain.retorno.ArquivoRetorno

data class RetornoWrapper(
        val fileName: String,
        val arquivoRetorno: ArquivoRetorno
)