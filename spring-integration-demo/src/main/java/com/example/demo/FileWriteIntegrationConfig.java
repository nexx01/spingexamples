//package com.example.demo;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.integration.annotation.Transformer;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.file.FileWritingMessageHandler;
//import org.springframework.integration.file.support.FileExistsMode;
//import org.springframework.integration.transformer.GenericTransformer;
//import org.springframework.messaging.MessageChannel;
//
//import java.io.File;
//
//@Configuration
//public class FileWriteIntegrationConfig {
//
//    @Bean
//    @Transformer(inputChannel = "textInChannel", outputChannel = "fileWriterChannel")
//    public GenericTransformer<String, String> upperCaseTransformer() {
//        return String::toUpperCase;
//    }
//
//
//    @Bean
//    @ServiceActivator(inputChannel = "fileWriterChannel")
//    public FileWritingMessageHandler fileWriter() {
//        FileWritingMessageHandler fileWritingMessageHandler = new FileWritingMessageHandler(new File("/tmp/sia6/files"));
//        fileWritingMessageHandler.setExpectReply(false);
//        fileWritingMessageHandler.setFileExistsMode(FileExistsMode.APPEND);
//        fileWritingMessageHandler.setAppendNewLine(true);
//        return fileWritingMessageHandler;
//    }
//
//    //create automaticaly but if need custom logic
////    @Bean
////    public MessageChannel textInChannel() {
////        return new DirectChannel();
////    }
////
////    @Bean
////    public MessageChannel fileWriterChannel(){
////        return new DirectChannel();
////    }
//}
