package com.techeasy.contas.receber.infra.remessa;

import com.techeasy.contas.receber.domain.clientes.model.Cliente;
import com.techeasy.contas.receber.domain.clientes.model.TipoCliente;
import com.techeasy.contas.receber.domain.contasreceber.model.ContasReceber;
import com.techeasy.contas.receber.domain.remessa.RemessaPort;
import com.techeasy.contas.receber.domain.remessa.model.DadosTitulo;
import com.techeasy.contas.receber.domain.remessa.model.Header;
import com.techeasy.contas.receber.domain.remessa.model.Remessa;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jrimum.bopepo.pdf.Files;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.jrimum.utilix.ClassLoaders;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
@Slf4j
public class RemessaAdapter implements RemessaPort {
	private static String nDocumento = "0000000001";

	public static final int CARTEIRA_COBRANCA = 17;
	public static final String VARIACAO_CARTEIRA = "019";
	public static final int COMANDO_REGISTRO_TITULO = 01;
	public static final int DUPLICATE_DE_SERVICO = 12;
	public static final String ACEITE_TITULO = "N";
	public static final String JUROS_DIA_ATRASO = "00000000003";
	private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ddMMyy");
	private File layoutTemporario;

	private static Record createTrailer(FlatFile<Record> ff, Header header) {

		Record trailer = ff.createRecord("Trailler");

		trailer.setValue("NumeroSequencialRegistro", header.getSequencial());

		return trailer;
	}

	private FlatFile<Record> generateNewLayoutTemporaryFile() throws IOException {
		// Identificando Layout
		this.layoutTemporario = new File("LayoutRemessa_BBCNAB400.txg.xml");
		File layout = Files.bytesToFile(layoutTemporario, IOUtils.toByteArray(ClassLoaders.getResourceAsStream("/layoutBanco/LayoutRemessa_BBCNAB400.txg.xml")));
		return Texgit.createFlatFile(layout);
	}

	private Record createHeader(FlatFile<Record> ff, Header remessaHeader) {
		Record header = ff.createRecord("Header");

		header.setValue("DataGravacao", dateFormat.format(LocalDate.now()));
		header.setValue("SequencialRemessa", remessaHeader.getSequencial());
		header.setValue("NumeroConvenioLider", remessaHeader.getConvenio());

		return header;
	}

	@Override
	public String generate(Remessa remessa) throws IOException {

		// Criando o arquivo de remessa
		FlatFile<Record> ff = generateNewLayoutTemporaryFile();

		// Registrando Header
		ff.addRecord(createHeader(ff, remessa.getHeader()));

		Integer sequencial = 1;
		for (DadosTitulo dadosTitulo : remessa.getDadosTitulos()) {
			TransacaoRemessaDTO dto = new TransacaoRemessaDTO(remessa.getHeader(), dadosTitulo, sequencial);
			ff.addRecord(createTransacaoTitulos(ff, dto));
			sequencial++;
		}

		// Registrando Trailer
		ff.addRecord(createTrailer(ff, remessa.getHeader()));
		return generateRemessaFile(ff);

	}

	@NotNull
	private String generateRemessaFile(FlatFile<Record> ff) throws IOException {
		// Salvando arquivo de remessa.
		File arquivoDeRemessa = new File("resource/arquivoRemessa/REMESSABB" + new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + ".REM");
		FileUtils.writeLines(arquivoDeRemessa, StandardCharsets.ISO_8859_1.name(), ff.write(), "\r\n");
		log.info("Arquivo gerado com sucesso {}", arquivoDeRemessa.getName());

		deleteTemporaryFile();
		return arquivoDeRemessa.getAbsolutePath();
	}

	private void deleteTemporaryFile() {
		if (layoutTemporario != null) {
			layoutTemporario.delete();
		}
	}

	private Record createTransacaoTitulos(FlatFile<Record> ff, TransacaoRemessaDTO dto) {

		Record transacaoTitulos = ff.createRecord("TransacaoTitulo");
		Header header = dto.getHeader();
		DadosTitulo dadosTitulo = dto.getDadosTitulo();
		ContasReceber contasReceber = dadosTitulo.getContaReceber();
		Cliente cliente = dadosTitulo.getCliente();

		transacaoTitulos.setValue("TipoInscricaoCedente", getTipoInscricao(cliente));
		transacaoTitulos.setValue("NumeroCpfCnpjCedente", header.getCnpj());

		transacaoTitulos.setValue("PrefixoAgencia", header.getPrefixoAgencia());
		transacaoTitulos.setValue("DigitoVerificadorAgencia", header.getDigitoAgencia());
		transacaoTitulos.setValue("NumeroContaCorrenteCedente", header.getContaCorrente());
		transacaoTitulos.setValue("DigitoVerificadorConta", header.getDigContaCorrente());

		transacaoTitulos.setValue("NumeroCovenioCobrancaCedente", header.getConvenio());
		transacaoTitulos.setValue("CodigoControleEmpresa", contasReceber.getId());
		transacaoTitulos.setValue("NossoNumero", header.getConvenio() + nDocumento);

		transacaoTitulos.setValue("VariacaoCarteira", VARIACAO_CARTEIRA);
		transacaoTitulos.setValue("CarteiraCobranca", CARTEIRA_COBRANCA);
		transacaoTitulos.setValue("Comando", COMANDO_REGISTRO_TITULO);


		transacaoTitulos.setValue("NumeroTituloAtribuidoCedente", contasReceber.getNumDocumento());
		transacaoTitulos.setValue("DataVencimento", dateFormat.format(contasReceber.getDataVencimento()));
		transacaoTitulos.setValue("ValorTitulo", contasReceber.getValorTotal());

		transacaoTitulos.setValue("EspecieTitulo", DUPLICATE_DE_SERVICO);
		transacaoTitulos.setValue("AceiteTitulo", ACEITE_TITULO);
		transacaoTitulos.setValue("DataEmissao", dateFormat.format(contasReceber.getDataCriacao()));

		transacaoTitulos.setValue("JurosMoraDiaAtraso", JUROS_DIA_ATRASO);

		if (contasReceber.getValorDesconto().intValue() != BigDecimal.ZERO.intValue()) {
			transacaoTitulos.setValue("DataLimite", dateFormat.format(contasReceber.getDataVencimento()));
		}
		transacaoTitulos.setValue("ValorDesconto", getValorDesconto(contasReceber));

		transacaoTitulos.setValue("TipoInscricaoPagador", getTipoInscricao(cliente));
		transacaoTitulos.setValue("NumeroCnpjCpfPagador", cliente.getCnpj());
		transacaoTitulos.setValue("NomePagador", cliente.getRazaoSocial());

		transacaoTitulos.setValue("EnderecoPagador", cliente.getEndereco().getRua() + cliente.getEndereco().getNumero());
		transacaoTitulos.setValue("BairroPagador", cliente.getEndereco().getBairro());
		transacaoTitulos.setValue("CepEnderecoPagador", cliente.getEndereco().getCep());
		transacaoTitulos.setValue("CidadePagador", cliente.getEndereco().getCidade());
		transacaoTitulos.setValue("UfCidadePagador", cliente.getEndereco().getUf());
		transacaoTitulos.setValue("SequencialRegistro", dto.getSequencial());

		return transacaoTitulos;
	}

	@NotNull
	private Object getValorDesconto(ContasReceber contasReceber) {
		return contasReceber.getValorDesconto() != null ? contasReceber.getValorDesconto() : "           ";
	}

	private int getTipoInscricao(Cliente cliente) {
		return cliente.getTipo().equals(TipoCliente.F) ? 01 : 02;
	}
}
