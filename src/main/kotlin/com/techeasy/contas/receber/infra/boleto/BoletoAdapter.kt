package com.techeasy.contas.receber.infra.boleto

import com.techeasy.contas.receber.domain.clientes.model.Cliente
import com.techeasy.contas.receber.domain.contasreceber.model.ContasReceber
import org.jrimum.bopepo.BancosSuportados
import org.jrimum.bopepo.Boleto
import org.jrimum.bopepo.view.BoletoViewer
import org.jrimum.domkee.comum.pessoa.endereco.CEP
import org.jrimum.domkee.comum.pessoa.endereco.Endereco
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa
import org.jrimum.domkee.financeiro.banco.febraban.*
import org.springframework.stereotype.Component
import java.io.File
import java.time.ZoneOffset
import java.util.*

@Component
class BoletoAdapter {
    private val CONVENIO = 2866935
    private val TECHEASY = "TECHEASY LTDA"
    private val CNPJ: String = "37472388000133"
    private val AGENCIA: Int = 4429
    private val DIG_AGENCIA: Int = 0
    private val CONTA_CORRENTE: Int = 1063248
    private val DIG_CONTA_CORRENTE: Int = 4

    fun gerarBoleto(contaReceber: ContasReceber): File? {
        val cliente = contaReceber.cliente
        val clienteAddress = cliente?.endereco
        // Cedente
        val cedente = Cedente(TECHEASY, CNPJ)
        val titulo = Titulo(buildContaBancaria(), buildSacado(cliente, clienteAddress), cedente)
        buildTituloInfo(titulo, contaReceber)

        // Dados do boleto
        val boleto = buildBoletoInfo(titulo, contaReceber)
        val boletoViewer = BoletoViewer(boleto)
        return boletoViewer.getPdfAsFile("resource/boletoBB.pdf")
    }

    private fun buildSacado(cliente: Cliente?, clienteAddress: com.techeasy.contas.receber.domain.clientes.Endereco?): Sacado {
        val sacado = Sacado(cliente?.nomeFantasia, cliente?.cnpj)
        sacado.addEndereco(buildEndereco(clienteAddress))
        return sacado
    }

    private fun buildBoletoInfo(titulo: Titulo, contaReceber: ContasReceber): Boleto {
        val boleto = Boleto(titulo)
        /* cria uma informação fake para o usuário, pois  foi necessáio o nº convênio em "contaBancaria.setNumeroDaConta" para
             *  poder mostrar agencia e conta para o usuário
             */

        val extras = mutableMapOf<String, String>()
        extras.put("Serviço: ", contaReceber.servicoPrestado)
        boleto.textosExtras = extras
        boleto.localPagamento = "Pagar preferencialmente no Banco do Brasil"
        boleto.instrucaoAoSacado = "Evite multas, pague em dias suas contas."
        boleto.instrucao1 = "NÃO ACEITAR PAGAMENTO EM CHEQUE"
        boleto.instrucao3 = "EM CASO DE ATRASO COBRAR MULTA DE 2%, MAIS JUROS DE 1% AO MÊS"
        return boleto
    }

    private fun buildTituloInfo(titulo: Titulo, contaReceber: ContasReceber) {
        titulo.numeroDoDocumento = contaReceber.numDocumento
        val nossoNumero = "0000000001"

        titulo.nossoNumero = CONVENIO.toString() + nossoNumero

        titulo.valor = contaReceber.valorTotal
        titulo.desconto = contaReceber.valorDesconto
        titulo.acrecimo = contaReceber.valorMulta
        titulo.valorCobrado = titulo.valor + titulo.acrecimo  - titulo.desconto
        titulo.dataDoDocumento = Date()
        titulo.dataDoVencimento = Date.from(contaReceber.dataVencimento?.atStartOfDay()?.toInstant(ZoneOffset.UTC))
        titulo.tipoDeDocumento = TipoDeTitulo.DS_DUPLICATA_DE_SERVICO
        titulo.aceite = Titulo.Aceite.N
    }

    private fun buildContaBancaria(): ContaBancaria {
        // Criando o título
        val contaBancaria = ContaBancaria(BancosSuportados.BANCO_DO_BRASIL.create())
        contaBancaria.agencia = Agencia(AGENCIA, DIG_AGENCIA.toString())
        contaBancaria.numeroDaConta = NumeroDaConta(CONTA_CORRENTE, DIG_CONTA_CORRENTE.toString()) // usado para convêncio > 1.000.000 - senão basta colocar o Nº da conta
        contaBancaria.carteira = Carteira(17)
        return contaBancaria
    }

    private fun buildEndereco(clienteAddress: com.techeasy.contas.receber.domain.clientes.Endereco?): Endereco {
        // Endereço do sacado
        var endereco = Endereco()
        endereco.uf = UnidadeFederativa.valueOf(clienteAddress?.uf.toString())
        endereco.localidade = clienteAddress?.cidade
        endereco.setCep(CEP(clienteAddress?.cep))
        endereco.bairro = clienteAddress?.bairro
        endereco.logradouro = clienteAddress?.rua
        endereco.numero = clienteAddress?.numero.toString()
        return endereco
    }
}