package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.contas.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
}
