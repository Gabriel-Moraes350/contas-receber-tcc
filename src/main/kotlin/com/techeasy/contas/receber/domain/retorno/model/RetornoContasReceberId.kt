package com.techeasy.contas.receber.domain.retorno.model;

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class RetornoContasReceberId : Serializable {
    var retornoId: Long? = null;
    var contaReceberId: Long? = null;

}