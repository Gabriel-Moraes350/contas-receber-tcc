package com.techeasy.contas.receber.domain.beneficios.model;

import com.techeasy.contas.receber.domain.clientes.model.Cliente;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "beneficio")
public class Beneficio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @Column(name = "dt_criacao")
    private Instant dtCriacao;

    @Enumerated(EnumType.STRING)
    private BeneficioType descricao;

    @Column(name = "dt_validade")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dtValidade;

    @Column(name = "valor_desconto")
    @NumberFormat(pattern = "#.###,##")
    private BigDecimal valorDesconto;
}
