package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.usuarios.model.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelRepository extends JpaRepository<Nivel, Long> {
}
