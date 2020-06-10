package com.techeasy.contas.receber.domain.remessa.model

import com.techeasy.contas.receber.domain.clientes.model.Cliente
import com.techeasy.contas.receber.domain.contasreceber.model.ContasReceber
import lombok.Builder

@Builder
data class DadosTitulo(
        var contaReceber: ContasReceber,
        var cliente: Cliente?

)