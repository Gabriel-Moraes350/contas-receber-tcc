package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.comunicacoes.MotivoComunicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoComunicacaoRepository extends JpaRepository<MotivoComunicacao, Long> {
}
