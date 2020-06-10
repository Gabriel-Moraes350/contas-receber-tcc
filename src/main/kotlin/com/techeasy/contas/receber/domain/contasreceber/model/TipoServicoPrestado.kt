package com.techeasy.contas.receber.domain.contasreceber.model

enum class TipoServicoPrestado(var description: String) {
    SERVICO("Prestação de Serviço"),
    MANUT("Manutenção de Sistema"),
    IMPLANT("Implantação de Sistema");
}
