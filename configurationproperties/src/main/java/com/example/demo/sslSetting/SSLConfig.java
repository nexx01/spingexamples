package com.example.demo.sslSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class SSLConfig {
    @Autowired
    private Environment env;

    @PostConstruct
    private void configureSSL() {
        //set to TLSv1.1 or TLSv1.2
        System.setProperty("https.protocols", "TLSv1.1");

        //load the 'javax.net.ssl.trustStore' and
        //'javax.net.ssl.trustStorePassword' from application.properties
        System.setProperty("javax.net.ssl.trustStore", env.getProperty("trust-store"));
        System.setProperty("javax.net.ssl.trustStorePassword",env.getProperty("trust-store-password"));
    }
}