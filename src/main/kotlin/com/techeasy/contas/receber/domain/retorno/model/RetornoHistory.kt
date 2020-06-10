package com.techeasy.contas.receber.domain.retorno.model

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "arquivos_retorno_history")
data class RetornoHistory(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var name: String = "",

        var userId: Long? = null,

        @CreatedDate
        var createdAt: LocalDateTime = LocalDateTime.now(),

        @Column(name = "file_bytes", nullable = true)
        var file: ByteArray? = null

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RetornoHistory

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        return result
    }
}