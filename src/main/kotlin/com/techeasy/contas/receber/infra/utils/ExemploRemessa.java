package com.techeasy.contas.receber.infra.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jrimum.bopepo.pdf.Files;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.jrimum.utilix.ClassLoaders;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExemploRemessa {

	private static int convenio = 2866935;

	private static int sequencial; // Vari�vel usada para seta a sequencia dos arquivos
	private static String nDocumento = "000000000";

	/**
	 * Classe geradora de arquivos de REMESSA para BANCO DO BRASIL - CNAB 400
	 *
	 * @author Rayan
	 */

	/*
	 * NOTA : as linhas comentadas s�o setadas por padr�o no layout do arquivo
	 */
	public static void main(String[] args) throws IOException {

		// Identificando Layout
		File layoutTemporario = new File("LayoutRemessa_BBCNAB400.txg.xml");
		File layout = Files.bytesToFile(layoutTemporario, IOUtils.toByteArray(ClassLoaders.getResourceAsStream("/layoutBanco/LayoutRemessa_BBCNAB400.txg.xml")));

		// Criando o arquivo de remessa
		FlatFile<Record> ff = Texgit.createFlatFile(layout);

		// Registrando Header
		ff.addRecord(createHeader(ff));

		/* La�o para representar mais de 1 boleto registrado no arquivo
		 *  Registrando T�tulos(boletos)
		 **/
		for (sequencial = 1; sequencial <= 2; sequencial++) {
			ff.addRecord(createTransacaoTitulos(ff, sequencial));
		}

		// Registrando Trailer
		ff.addRecord(createTrailer(ff, sequencial));

		// Salvando arquivo de remessa.
		File arquivoDeRemessa = new File("resource/arquivoRemessa/REMESSABB" + new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + ".REM");
		FileUtils.writeLines(arquivoDeRemessa, StandardCharsets.ISO_8859_1.name(), ff.write(), "\r\n");
		System.out.println("Arquivo gerado com sucesso em: " + arquivoDeRemessa);

		if (layoutTemporario != null) {
			layoutTemporario.delete();
		}
	}

	private static Record createHeader(FlatFile<Record> ff) {

		Record header = ff.createRecord("Header");

		header.setValue("DataGravacao", 310520);
		header.setValue("SequencialRemessa", "0000000");
		header.setValue("NumeroConvenioLider", convenio);

		return header;
	}

	private static Record createTransacaoTitulos(FlatFile<Record> ff, int sequencial) {

		/*
		 * NOTA : as linhas comentadas s�o setadas por padr�o no layout do arquivo
		 */
		Record transacaoTitulos = ff.createRecord("TransacaoTitulo");

		/*
		transacaoTitulos.setValue("IdentiFicacaoRegistroDetalhe", 7);
		transacaoTitulos.setValue("TipoInscricaoCedente", 02);
		*/
		transacaoTitulos.setValue("NumeroCpfCnpjCedente", "06313870000441");
		transacaoTitulos.setValue("PrefixoAgencia", 1623);
		transacaoTitulos.setValue("DigitoVerificadorAgencia", 2);
		transacaoTitulos.setValue("NumeroContaCorrenteCedente", 101916);
		transacaoTitulos.setValue("DigitoVerificadorConta", 0);
		transacaoTitulos.setValue("NumeroCovenioCobrancaCedente", convenio);
		transacaoTitulos.setValue("NossoNumero", convenio + nDocumento + sequencial);

		transacaoTitulos.setValue("VariacaoCarteira", "019");
		/*
		transacaoTitulos.setValue("ContaCaucao", 0);
		transacaoTitulos.setValue("NumeroBordero", 000000);
		transacaoTitulos.setValue("TipoCobranca", "     ");
		*/
		transacaoTitulos.setValue("CarteiraCobranca", 17);
		transacaoTitulos.setValue("Comando", 01);
		//transacaoTitulos.setValue("NumeroTituloAtribuidoCedente", "          ");
		transacaoTitulos.setValue("DataVencimento", "190118");
		transacaoTitulos.setValue("ValorTitulo", 1);
		/*transacaoTitulos.setValue("NumeroBanco", 001);
		transacaoTitulos.setValue("PrefixoAgenciaCobradora", 0000);
		transacaoTitulos.setValue("DigitoVerificadorPrefixoAgenciaCobradora", " ");
		*/
		transacaoTitulos.setValue("EspecieTitulo", 12);
		transacaoTitulos.setValue("AceiteTitulo", "N");
		transacaoTitulos.setValue("DataEmissao", 170118);
		/*
		transacaoTitulos.setValue("InstrucaoCodificada", "  ");
		transacaoTitulos.setValue("InstrucaoCodificada", "  ");
		*/
		transacaoTitulos.setValue("JurosMoraDiaAtraso", "00000000003");
		/*transacaoTitulos.setValue("DataLimite", "      ");
 		transacaoTitulos.setValue("ValorDesconto", "           "); 
		transacaoTitulos.setValue("ValorIof", "           ");
		transacaoTitulos.setValue("ValorAbatimento", "           ");
		*/
		transacaoTitulos.setValue("TipoInscricaoPagador", 01);
		transacaoTitulos.setValue("NumeroCnpjCpfPagador", "12345678900");
		transacaoTitulos.setValue("NomePagador", "FULADO DA SILVA SAURO");
		//transacaoTitulos.setValue("ComplementoRegistro", "");
		transacaoTitulos.setValue("EnderecoPagador", "Rua Augusto Correa, LABES");
		transacaoTitulos.setValue("BairroPagador", "Guama");
		transacaoTitulos.setValue("CepEnderecoPagador", "66075110");
		transacaoTitulos.setValue("CidadePagador", "BELEM");
		transacaoTitulos.setValue("UfCidadePagador", "PA");
		/*
		transacaoTitulos.setValue("Observacao", "                                        ");
		transacaoTitulos.setValue("NumeroDiasProtesto", "  ");
		transacaoTitulos.setValue("ComplementoRegistro", " ");
		*/
		transacaoTitulos.setValue("SequencialRegistro", sequencial);

		return transacaoTitulos;
	}

	private static Record createTrailer(FlatFile<Record> ff, int sequencial) {

		Record trailer = ff.createRecord("Trailler");

		trailer.setValue("NumeroSequencialRegistro", sequencial);

		return trailer;
	}
}
