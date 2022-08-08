package com.example.demo.mail;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mail.dsl.Mail;

@Configuration
public class TacoOrderEmailIntegrationConfig
{
    @Bean
    public IntegrationFlow tacoOrderEmailFow(EmailProperties emailProperties,
                                             EmailToOrderTransformer toOrderTransformer,
                                             OrderSubmitMessageHandler orderSubmitHandler){
        return IntegrationFlows
                .from(Mail.imapInboundAdapter(emailProperties.getImapUrl()),
                        e -> e.poller(
                                Pollers.fixedDelay(emailProperties.getPollRate())))
                .transform(toOrderTransformer)
                .handle(orderSubmitHandler)
                .get();
}

}
