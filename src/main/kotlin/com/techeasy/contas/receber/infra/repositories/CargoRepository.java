package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.usuarios.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
