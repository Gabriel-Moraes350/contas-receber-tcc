package com.techeasy.contas.receber.domain.clientes

import javax.persistence.*

@Entity
@Table(name = "endereco")
data class Endereco(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        val rua: String,

        val bairro: String,

        val cep: String,

        val numero: Int,

        @Column(length = 2)
        val uf: String,

        val cidade: String,

        val complemento: String? = ""
)