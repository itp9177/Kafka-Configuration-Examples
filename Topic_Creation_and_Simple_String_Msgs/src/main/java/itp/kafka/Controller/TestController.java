package itp.kafka.Controller;


import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/test")
public class TestController {
    private final KafkaTemplate<String,String> kafkaTemplate;
    public TestController(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate =kafkaTemplate;
    }

    @GetMapping("/producer")
    public void test1(){
        String message =  UUID.randomUUID().toString();
        String key = UUID.randomUUID().toString();
        ProducerRecord<String,String> record= new ProducerRecord<>("topic1",key,message); // write a simple string message to topic1
        kafkaTemplate.send(record);

    }
}
