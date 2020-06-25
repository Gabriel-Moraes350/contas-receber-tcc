package com.techeasy.contas.receber.domain.clientes.model;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> getAll();

    List<Cliente> findAll();

    Optional<Cliente> findOne(Long id);

    void save(Cliente cliente);

    void delete(Long id);

    void bloquear(Cliente cliente);

    void desbloquear(Cliente cliente);

}
