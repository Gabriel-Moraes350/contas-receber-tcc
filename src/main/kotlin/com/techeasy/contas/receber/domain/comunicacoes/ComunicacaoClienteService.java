package com.techeasy.contas.receber.domain.comunicacoes;

import com.techeasy.contas.receber.domain.comunicacoes.model.ComunicacaoCliente;
import com.techeasy.contas.receber.domain.comunicacoes.model.MotivoEnvio;

import java.util.List;
import java.util.Optional;

public interface ComunicacaoClienteService {
    List<ComunicacaoCliente> getAll();

    ComunicacaoCliente getOne(Long id);

    ComunicacaoCliente save(ComunicacaoCliente comunicacaoCliente);

    List<MotivoEnvio> getMotivoComunicacao();

    Optional<ComunicacaoCliente> findOne(Long id);

    void delete(Long id);
}
