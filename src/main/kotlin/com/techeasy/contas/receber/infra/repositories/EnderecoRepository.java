package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.clientes.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
