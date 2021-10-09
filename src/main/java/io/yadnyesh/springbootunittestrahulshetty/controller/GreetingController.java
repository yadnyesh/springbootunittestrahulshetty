package io.yadnyesh.springbootunittestrahulshetty.controller;

import io.yadnyesh.springbootunittestrahulshetty.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    @Autowired
    Greeting greeting;

    AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting returnGreeting(@RequestParam(value = "name") String name) {
        greeting.setId(counter.incrementAndGet());
        greeting.setName("Hello " + name);
        return greeting;
    }
}
