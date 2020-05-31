package com.techeasy.contas.receber.domain.clientes;

import com.techeasy.contas.receber.domain.clientes.model.Cliente;
import com.techeasy.contas.receber.domain.clientes.model.ClienteService;
import com.techeasy.contas.receber.infra.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Optional<Cliente> findOne(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void save(Cliente cliente) {
        if (cliente.getTipo().equals("F")) {
            cliente.setCnpj(cliente.getCnpj().split(",")[0]);
            cliente.setNomeFantasia(cliente.getNomeFantasia().split(",")[0]);
        } else {
            cliente.setCnpj(cliente.getCnpj().split(",")[1]);
            cliente.setNomeFantasia(cliente.getNomeFantasia().split(",")[1]);
        }
        clienteRepository.save(cliente);
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }


}
