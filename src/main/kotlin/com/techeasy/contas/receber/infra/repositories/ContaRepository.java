package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.contas.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("SELECT coalesce(max(numero), 0) + 1 FROM Conta")
    Long getMaxId();

}
