package com.techeasy.contas.receber.domain.remessa.model;

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class RemessaContasReceberId : Serializable {
    var remessaId: Long? = null;
    var contaReceberId: Long? = null;

}