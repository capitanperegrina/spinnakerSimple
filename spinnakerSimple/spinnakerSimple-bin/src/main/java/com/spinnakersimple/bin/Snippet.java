package com.spinnakersimple.bin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Snippet {

    public static void main(final String[] args) {
        try {

            final ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
            // final ConfigurationService configurationService =
            // ctx.getBean(ConfigurationService.class);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}