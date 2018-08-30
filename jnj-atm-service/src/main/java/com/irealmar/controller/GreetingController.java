
package com.irealmar.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irealmar.dbaccess.Greeting;
import com.irealmar.service.CounterLogic;

//TODO: DELETE ME!

/**
 * TODO: documentar.
 * @version _TBD_
 * @since _TBD_
 */
@RestController
@EnableAutoConfiguration
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final CounterLogic counterLogic = new CounterLogic();

    /**
     * TODO: documentar.
     * @param name
     *        name
     * @return greeting
     */
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name",
        defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    /**
     * TODO: documentar.
     * @return counter
     */
    @RequestMapping("/count")
    public Integer count() {
        return counterLogic.count();

    }
}
