package com.irealmar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.irealmar.repository.CashContainer;
import com.irealmar.repository.ClientContainer;
import com.irealmar.repository.TransactionContainer;

/**
 * TODO: documentar.
 */
@SpringBootApplication
@ComponentScan({
    "com.irealmar.controller",
    "com.irealmar.service",
    "com.irealmar.service.impl",
    "com.irealmar.repository"
})
public class Application {

    /**
     * TODO: documentar.
     * @param args
     *        args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CashContainer cashContainer() {
        return new CashContainer();
    }

    @Bean
    public TransactionContainer transactionContainer() {
        return new TransactionContainer();
    }

    @Bean
    public ClientContainer clientContainer() {
        return new ClientContainer();
    }

}
