package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findAllByOrderByIdAsc();
}

