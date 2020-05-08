package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
