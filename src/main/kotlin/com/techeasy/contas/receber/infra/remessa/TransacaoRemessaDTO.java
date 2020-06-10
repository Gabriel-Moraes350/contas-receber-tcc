package com.techeasy.contas.receber.infra.remessa;

import com.techeasy.contas.receber.domain.remessa.model.DadosTitulo;
import com.techeasy.contas.receber.domain.remessa.model.Header;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class TransacaoRemessaDTO {
    Header header;
    DadosTitulo dadosTitulo;
    Integer sequencial;
}
