package com.irealmar;

/**
 * TODO: documentar.
 * @version _TBD_
 * @since _TBD_
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO: documentar.
 */
@SpringBootApplication
@ComponentScan({
    "com.irealmar.controller",
    "com.irealmar.service",
    "com.irealmar.service.impl",
    "com.irealmar.dbaccess"
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
}