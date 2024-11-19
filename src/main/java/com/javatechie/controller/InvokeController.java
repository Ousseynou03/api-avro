package com.javatechie.controller;

import com.javatechie.dto.Invoke;
import com.javatechie.producer.KafkaAvroProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvokeController {
    @Autowired
    private KafkaAvroProducer producer;

    @PostMapping("/invoke")
    public String sendMessage(@RequestBody Invoke invoke) {
        producer.send(invoke);
        return "message published !";
    }
}
