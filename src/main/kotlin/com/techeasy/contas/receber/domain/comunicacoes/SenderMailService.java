package com.techeasy.contas.receber.domain.comunicacoes;

import com.techeasy.contas.receber.domain.comunicacoes.model.ComunicacaoCliente;
import com.techeasy.contas.receber.domain.contasreceber.model.ContasReceber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SenderMailService {
    @Autowired
    private JavaMailSender mailSender;

    private String setEmailMessage(ComunicacaoCliente comunicacaoCliente, List<ContasReceber> contas) {
        List<String> messages = new ArrayList<>();

        for (ContasReceber conta: contas) {
            messages.add("\nNumero do documento: " + conta.getNumDocumento() +
                    "\nValor: " + conta.getValorTotal() +
                    "\nData Vencimento: " + conta.getDataVencimento() +
                    "\nServiço Prestado: " + conta.getServicoPrestado() +
                    "\nForma de Pagamento: " + conta.getFormaPagamento() + "\n\n");
        }
        return "Olá " + comunicacaoCliente.getCliente().getNomeFantasia() + " Tudo bem?" +
                "\nNós somos da TechEasy e estamos lhe enviando este email por motivo de " +
                comunicacaoCliente.getMotivoComunicacao() + " em uma de suas faturas.\n" +
                "Segue a(s) fatura(s)\n"+
                messages.toString() +
                "\n\n Caso seja necessário, entre em contato conosco para podemos auxiliar melhor!" +
                "\n\nAtenciosamente" +
                "\nEquipe TechEasy!";
    }

    private SimpleMailMessage prepareEmail(ComunicacaoCliente comunicacaoCliente, List<ContasReceber> contas) {
        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(comunicacaoCliente.getCliente().getEmail());
        email.setSubject(comunicacaoCliente.getMotivoComunicacao() + " de sua ultima fatura");
        email.setText(setEmailMessage(comunicacaoCliente, contas));

        return email;
    }

    public void send(ComunicacaoCliente comunicacaoCliente, List<ContasReceber> contas) {
        mailSender.send(prepareEmail(comunicacaoCliente, contas));
    }

}
