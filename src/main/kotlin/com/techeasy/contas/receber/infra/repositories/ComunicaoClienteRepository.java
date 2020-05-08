package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.comunicacoes.ComunicacaoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunicaoClienteRepository extends JpaRepository<ComunicacaoCliente, Long> {}
