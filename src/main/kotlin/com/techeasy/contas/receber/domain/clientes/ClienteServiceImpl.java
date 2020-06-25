package com.techeasy.contas.receber.domain.clientes;

import com.techeasy.contas.receber.domain.clientes.model.Cliente;
import com.techeasy.contas.receber.domain.clientes.model.ClienteService;
import com.techeasy.contas.receber.infra.repositories.ClienteRepository;
import com.techeasy.contas.receber.infra.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findByDeleteDate();
    }

    @Override
    public Optional<Cliente> findOne(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void save(Cliente cliente) {
        if (cliente.getTipo().name().equals("F")) {
            cliente.setCnpj(cliente.getCnpj().split(",")[0]);
            cliente.setNomeFantasia(cliente.getNomeFantasia().split(",")[0]);
        } else {
            cliente.setCnpj(cliente.getCnpj().split(",")[1]);
            cliente.setNomeFantasia(cliente.getNomeFantasia().split(",")[1]);
        }
        cliente.setCnpj(cliente.getCnpj().replaceAll("\\.|-|/", ""));
        cliente.setInscricaoEstadual(cliente.getInscricaoEstadual().replaceAll("\\.", ""));
        cliente.getEndereco().setCep(cliente.getEndereco().getCep().replaceAll("\\.|-", ""));

        clienteRepository.save(cliente);
    }

    @Override
    public void delete(Long id) {

        Optional<Cliente> cliente = clienteRepository.findById(id);
        cliente.get().setDeleteDate(new Date());
        clienteRepository.save(cliente.get());
    }

    @Override
    public void bloquear(Cliente cliente) {

        cliente.setBloqueado(true);
        cliente.setDatUltBloqueio(new Date());
        clienteRepository.save(cliente);
    }

    @Override
    public void desbloquear(Cliente cliente) {

        cliente.setBloqueado(false);
        cliente.setDatUltBloqueio(null);
        clienteRepository.save(cliente);
    }

}
