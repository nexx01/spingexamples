package com.example.demo.sslSetting;

import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Configuration
public class RestTemplateConfiguration {
    @Value("${trust-store}")
    private Resource trustStore;

    @Value("${trust-store-password}")
    private String trustStorePassword;

//    RestTemplate restTemplate() throws Exception {
//        SSLContext sslContext = new SSLContextBuilder()
//                .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
//                .build();
//        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setSSLSocketFactory(socketFactory)
//                .build();
//        HttpComponentsClientHttpRequestFactory factory =
//                new HttpComponentsClientHttpRequestFactory(httpClient);
//        return new RestTemplate(factory);
//    }

//    @Bean
//    @Primary
//    public RestTemplate restTemplateWithTrustStore(RestTemplateBuilder builder) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, CertificateException {
//        SSLContext sslContext = new SSLContextBuilder()
//                .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
//                .build();
//        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
//
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setSSLSocketFactory(socketFactory)
//                .build();
//
//        return builder
//                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(httpClient))
//                .build();
//    }
//@Bean
//public RestTemplate restTemplate()
//        throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//
//    SSLContextBuilder sslcontext = new SSLContextBuilder();
//    sslcontext.loadTrustMaterial((org.apache.hc.core5.ssl.TrustStrategy) new TrustSelfSignedStrategy());;
//
//    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
//            .loadTrustMaterial(null, acceptingTrustStrategy)
//            .build();
//
//    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//
//    CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(sslcontext.build()).setSSLHostnameVerifier(
//                    NoopHostnameVerifier.INSTANCE)
//            .build();
//
//    HttpComponentsClientHttpRequestFactory requestFactory =
//            new HttpComponentsClientHttpRequestFactory();
//
//    requestFactory.setHttpClient(httpClient);
//    RestTemplate restTemplate = new RestTemplate(requestFactory);
//    return restTemplate;
//}

    @Bean
    RestTemplate restTemplate() throws Exception {
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(
                        trustStore.getURL(),
                        trustStorePassword.toCharArray()
                ).build();
        SSLConnectionSocketFactory socketFactory =
                new SSLConnectionSocketFactory(sslContext);
        HttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory).build();
        HttpComponentsClientHttpRequestFactory factory =
                new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }

}
