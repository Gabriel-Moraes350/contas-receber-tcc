package com.techeasy.contas.receber.domain.beneficios;

import com.techeasy.contas.receber.domain.beneficios.model.Beneficio;
import com.techeasy.contas.receber.infra.repositories.BeneficioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficioService {

    @Autowired
    private BeneficioRepository beneficioRepository;

    public List<Beneficio> listAll(){
        return beneficioRepository.findAll();
    }

    public Optional<Beneficio> findOne(Long id) {
        return beneficioRepository.findById(id);
    }

    public void save(Beneficio beneficio) {

        beneficioRepository.save(beneficio);
    }

    public void delete(Long id) {

        Optional<Beneficio> beneficio = beneficioRepository.findById(id);
        beneficioRepository.delete(beneficio.get());
    }

    public List<Beneficio> findByCliente(Long clienteId){
        return beneficioRepository.findByCliente(clienteId);
    }
}
