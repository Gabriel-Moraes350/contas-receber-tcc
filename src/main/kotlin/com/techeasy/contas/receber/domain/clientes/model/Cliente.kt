package com.techeasy.contas.receber.domain.clientes.model

import com.techeasy.contas.receber.domain.clientes.Endereco
import org.springframework.format.annotation.DateTimeFormat
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "cliente")
data class Cliente(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinColumn(name = "cliente_id")
        var enderecos: List<Endereco>?,

        val cnpj: String,

        val nome: String,

        val email: String,

        val dddTelefone: Int?,

        val telefone: Int?,

        @Column(nullable = false)
        val bloqueado: Boolean = false,

        @field:DateTimeFormat(pattern = "dd/MM/yyyy") val datUltBloqueio: OffsetDateTime?
);