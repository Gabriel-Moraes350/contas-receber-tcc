package com.techeasy.contas.receber.domain.contasreceber.model

import com.techeasy.contas.receber.domain.clientes.model.Cliente
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.NumberFormat
import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "contas_receber")
data class ContasReceber(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @ManyToOne
        @JoinColumn(name = "cliente_id")
        var cliente: Cliente? = null,

        @Enumerated(EnumType.STRING)
        var status: StatusRecebimento = StatusRecebimento.aguardando,

        @Enumerated(EnumType.STRING)
        var formaPagamento: FormaPagamento? = null,

        var numParcela: Int? = 0,

        var numDocumento: String? = null,

        @field:NumberFormat(pattern = "#.###,##") var valorMulta: BigDecimal = BigDecimal.valueOf(0, 2),

        @field:NumberFormat(pattern = "#.###,##") var valorDesconto: BigDecimal = BigDecimal.valueOf(0, 2),

        @field:NumberFormat(pattern = "#.###,##") var valorLiquidoRecebido: BigDecimal? = null,

        @field:NumberFormat(pattern = "#.###,##") var valorTotal: BigDecimal? = null,

        var pontos: Int = 0,

        var servicoPrestado: String = "",

        @Enumerated(EnumType.STRING)
        var tipoServicoPrestado: TipoServicoPrestado = TipoServicoPrestado.SERVICO,

        @CreatedDate
        @field:DateTimeFormat(pattern = "dd/MM/yyyy") var dataCriacao: OffsetDateTime? = OffsetDateTime.now(),
        @UpdateTimestamp
        var dataAlteracao: OffsetDateTime? = null,

        @field:DateTimeFormat(pattern = "dd/MM/yyyy") var dataVencimento: LocalDate? = null,

        @field:DateTimeFormat(pattern = "dd/MM/yyyy") var dataCredito: LocalDate? = null,

        @field:DateTimeFormat(pattern = "dd/MM/yyyy") var dataLiquidacao: LocalDate? = null,
        var deletedAt: OffsetDateTime? = null
);