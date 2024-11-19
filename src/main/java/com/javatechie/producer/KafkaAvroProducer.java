package com.javatechie.producer;

import com.javatechie.dto.Invoke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaAvroProducer {

    @Value("${topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Invoke> template;


    public void send(Invoke invoke){
        CompletableFuture<SendResult<String, Invoke>> future = template.send(topicName, UUID.randomUUID().toString(),invoke);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + invoke +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        invoke + "] due to : " + ex.getMessage());
            }
        });
    }
}
