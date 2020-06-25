package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.beneficios.model.Beneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long>{

    @Query(value = "select * from beneficio where cliente_id = :clienteId and dt_validade > now()", nativeQuery = true)
    List<Beneficio> findByCliente(Long clienteId);
}
