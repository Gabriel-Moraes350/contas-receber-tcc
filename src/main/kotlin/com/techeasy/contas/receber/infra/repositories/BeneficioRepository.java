package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.beneficios.model.Beneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long>{

}
