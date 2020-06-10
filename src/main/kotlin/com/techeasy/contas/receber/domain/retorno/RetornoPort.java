package com.techeasy.contas.receber.domain.retorno;

import com.techeasy.contas.receber.infra.retorno.RetornoWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RetornoPort {
    RetornoWrapper importar(MultipartFile fileRetorno) throws IOException;
}
