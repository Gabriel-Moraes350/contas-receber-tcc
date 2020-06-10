package com.techeasy.contas.receber.domain.remessa;

import com.techeasy.contas.receber.domain.remessa.model.Remessa;

import java.io.IOException;

public interface RemessaPort {
    public String generate(Remessa remessa) throws IOException;
}
