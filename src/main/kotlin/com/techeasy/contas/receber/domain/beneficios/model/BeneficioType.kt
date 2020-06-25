package com.techeasy.contas.receber.domain.beneficios.model

enum class BeneficioType(val description: String) {
    BLOCK("Restrição"),
    DESCONTO("Desconto");

    fun getByDescription(desc:String): BeneficioType? {
        val mapValues = values().associateBy { it.description}

        return mapValues[desc]
    }
}