package com.techeasy.contas.receber.domain.comunicacoes;

import com.techeasy.contas.receber.domain.comunicacoes.model.ComunicacaoCliente;
import com.techeasy.contas.receber.domain.comunicacoes.model.MotivoEnvio;
import com.techeasy.contas.receber.infra.repositories.ComunicacaoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComunicacaoClienteServiceImpl implements ComunicacaoClienteService{
    @Autowired
    private ComunicacaoClienteRepository comunicacaoClienteRepo;

    ComunicacaoClienteServiceImpl() {
    }

    @Override
    public List<MotivoEnvio> getMotivoComunicacao() {
        List<MotivoEnvio> list = new ArrayList<MotivoEnvio>();

        for (MotivoEnvio t : MotivoEnvio.values()) {
            list.add(t);
        }
        return list;
    }

    @Override
    public List<ComunicacaoCliente> getAll() {
        return comunicacaoClienteRepo.findAll();
    }

    @Override
    public ComunicacaoCliente getOne(Long id) {
        return comunicacaoClienteRepo.getOne(id);
    }

    @Override
    public Optional<ComunicacaoCliente> findOne(Long id) {
        return comunicacaoClienteRepo.findById(id);
    }

    @Override
    public ComunicacaoCliente save(ComunicacaoCliente comunicacaoCliente) {
        return comunicacaoClienteRepo.save(comunicacaoCliente);
    }

    @Override
    public void delete(Long id) {
        comunicacaoClienteRepo.deleteById(id);
    }
}
