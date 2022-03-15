package com.spinnakersimple.bin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spinnakersimple.servicios.AnunciosService;

public class RenuevaAnuncioTesting {

    public static void main(final String[] args) {
        try {

            final ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
            final AnunciosService anuncioService = ctx.getBean(AnunciosService.class);
            anuncioService.notificaAnunciosQueCaducanEnNDias(1);

            /*
             * final Criptografia criptografia = ctx.getBean(Criptografia.class);
             *
             * final String url = criptografia.codificaParaUrl(
             * criptografia.encriptar(GeneradorClaves.generaPin(10) + "|" + "112" + "|" +
             * GeneradorClaves.generaPin(10)));
             *
             * String r = url; r = criptografia.descodificaParaUrl(r); r =
             * criptografia.desencriptar(r); r = r.split("\\|")[1]; System.out.println(r);
             */

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
