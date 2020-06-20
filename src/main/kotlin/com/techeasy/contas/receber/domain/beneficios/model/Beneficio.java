package com.techeasy.contas.receber.domain.beneficios.model;

import com.techeasy.contas.receber.domain.clientes.model.Cliente;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "beneficio")
public class Beneficio {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cliente cliente;

    private Instant dtCriacao;

    @Enumerated(EnumType.STRING)
    private BeneficioType descricao;

    private LocalDate dtValidade;

    private BigDecimal valorDesconto;
}
