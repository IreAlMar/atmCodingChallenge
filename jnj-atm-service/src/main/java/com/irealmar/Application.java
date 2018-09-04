package com.irealmar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.irealmar.repository.CashContainer;
import com.irealmar.repository.ClientContainer;
import com.irealmar.repository.TransactionContainer;

/**
 * Application.
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
     * main.
     * @param args
     *        args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Singleton instance retriever.
     * @return the cash container
     */
    @Bean
    public CashContainer cashContainer() {
        return new CashContainer();
    }

    /**
     * Singleton instance retriever.
     * @return the transaction container
     */
    @Bean
    public TransactionContainer transactionContainer() {
        return new TransactionContainer();
    }

    /**
     * Singleton instance retriever.
     * @return the client container
     */
    @Bean
    public ClientContainer clientContainer() {
        return new ClientContainer();
    }

}
