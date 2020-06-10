package com.techeasy.contas.receber.domain.remessa.model

data class Header(
        var sequencial: Int,
        val convenio: Int = 2866935,
        val cnpj: String = "37472388000133",
        val prefixoAgencia: Int = 4429,
        val digitoAgencia: Int = 0,
        val contaCorrente: Int = 1063248,
        val digContaCorrente: Int = 4
)