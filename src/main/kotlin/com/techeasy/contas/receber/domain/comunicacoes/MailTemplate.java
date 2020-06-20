package com.techeasy.contas.receber.domain.comunicacoes;

import com.techeasy.contas.receber.domain.comunicacoes.model.MotivoEnvio;

public class MailTemplate {
    public String getMailTemplate(String name, String motivo) {
        final String kTemplate = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
                "<head>\n" +
                "<meta name=\"viewport\" content=\"width=device-width\" />\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "<title>Alerts e.g. approaching your limit</title>\n" +
                "\n" +
                "\n" +
                "<style type=\"text/css\">\n" +
                "img {\n" +
                "max-width: 100%;\n" +
                "}\n" +
                "body {\n" +
                "-webkit-font-smoothing: antialiased; -webkit-text-size-adjust: none; width: 100% !important; height: 100%; line-height: 1.6em;\n" +
                "}\n" +
                "body {\n" +
                "background-color: #f6f6f6;\n" +
                "}\n" +
                "@media only screen and (max-width: 640px) {\n" +
                "  body {\n" +
                "    padding: 0 !important;\n" +
                "  }\n" +
                "  h1 {\n" +
                "    font-weight: 800 !important; margin: 20px 0 5px !important;\n" +
                "  }\n" +
                "  h2 {\n" +
                "    font-weight: 800 !important; margin: 20px 0 5px !important;\n" +
                "  }\n" +
                "  h3 {\n" +
                "    font-weight: 800 !important; margin: 20px 0 5px !important;\n" +
                "  }\n" +
                "  h4 {\n" +
                "    font-weight: 800 !important; margin: 20px 0 5px !important;\n" +
                "  }\n" +
                "  h1 {\n" +
                "    font-size: 22px !important;\n" +
                "  }\n" +
                "  h2 {\n" +
                "    font-size: 18px !important;\n" +
                "  }\n" +
                "  h3 {\n" +
                "    font-size: 16px !important;\n" +
                "  }\n" +
                "  .container {\n" +
                "    padding: 0 !important; width: 100% !important;\n" +
                "  }\n" +
                "  .content {\n" +
                "    padding: 0 !important;\n" +
                "  }\n" +
                "  .content-wrap {\n" +
                "    padding: 10px !important;\n" +
                "  }\n" +
                "  .invoice {\n" +
                "    width: 100% !important;\n" +
                "  }\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "\n" +
                "<body itemscope itemtype=\"http://schema.org/EmailMessage\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; -webkit-font-smoothing: antialiased; -webkit-text-size-adjust: none; width: 100% !important; height: 100%; line-height: 1.6em; background-color: #f6f6f6; margin: 0;\" bgcolor=\"#f6f6f6\">\n" +
                "\n" +
                "<table class=\"body-wrap\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; background-color: #f6f6f6; margin: 0;\" bgcolor=\"#f6f6f6\"><tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\"><td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\" valign=\"top\"></td>\n" +
                "\t\t<td class=\"container\" width=\"600\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; display: block !important; max-width: 600px !important; clear: both !important; margin: 0 auto;\" valign=\"top\">\n" +
                "\t\t\t<div class=\"content\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; max-width: 600px; display: block; margin: 0 auto; padding: 20px;\">\n" +
                "\t\t\t\t<table class=\"main\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; border-radius: 3px; background-color: #fff; margin: 0; border: 1px solid #e9e9e9;\" bgcolor=\"#fff\"><tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\"><td class=\"alert alert-warning\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 16px; vertical-align: top; color: #fff; font-weight: 500; text-align: center; border-radius: 3px 3px 0 0; background-color: #6f42c1; margin: 0; padding: 20px;\" align=\"center\" bgcolor=\"#FF9F00\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t"+ motivo +" de fatura. Por favor, atenção!\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr><tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\"><td class=\"content-wrap\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 20px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\"><tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\"><td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\tBoa noite " + name + ", somos da <strong style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">TechEasy</strong>.\n" +
                "\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t</tr><tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\"><td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\tO email foi enviado por motivo de" + motivo + "em uma de suas faturas.\n" +
                "\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t</tr><tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\"><td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.whatsapp.com/\" class=\"btn-primary\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; color: #FFF; text-decoration: none; line-height: 2em; font-weight: bold; text-align: center; cursor: pointer; display: inline-block; border-radius: 5px; text-transform: capitalize; background-color: #25D366; margin: 0; border-color: #25D366; border-style: solid; border-width: 10px 20px;\">Chame no WhatsApp</a>\n" +
                "\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t</tr><tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\"><td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\tAtenciosamente\n" +
                "                                    </td>\n" +
                "                                </tr><tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\"><td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">\n" +
                "                                    Equipe TechEasy\n" +
                "                                </td>\n" +
                "\t\t\t\t\t\t\t\t</tr></table></td>\n" +
                "\t\t\t\t\t</tr></table><div class=\"footer\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; clear: both; color: #999; margin: 0; padding: 20px;\">\n" +
                "\t\t\t\t\t<table width=\"100%\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\"><tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\"><td class=\"aligncenter content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 12px; vertical-align: top; color: #999; text-align: center; margin: 0; padding: 0 0 20px;\" align=\"center\" valign=\"top\"> TechEasy Soluções em Tecnologia.</td>\n" +
                "\t\t\t\t\t\t</tr></table></div></div>\n" +
                "\t\t</td>\n" +
                "\t\t<td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\" valign=\"top\"></td>\n" +
                "\t</tr></table></body>\n" +
                "</html>";

        return kTemplate;
    }
}
