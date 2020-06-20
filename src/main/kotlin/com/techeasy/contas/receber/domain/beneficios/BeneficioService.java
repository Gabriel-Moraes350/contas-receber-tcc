package com.techeasy.contas.receber.domain.beneficios;

import com.techeasy.contas.receber.domain.beneficios.model.Beneficio;
import com.techeasy.contas.receber.infra.repositories.BeneficioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficioService {

    @Autowired
    private BeneficioRepository beneficioRepository;

    public List<Beneficio> listAll(){
        return beneficioRepository.findAll();
    }
}
