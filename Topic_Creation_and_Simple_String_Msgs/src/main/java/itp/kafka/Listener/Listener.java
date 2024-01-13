package itp.kafka.Listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@EnableKafka
@Configuration
public class Listener {

     @KafkaListener( topics = "topic1" ,containerFactory = "customFactoryStr",groupId = "foo")
     public void listen(ConsumerRecord<String,String> record) {System.out.println(record);}

    //you can use
    //public void listen(String record) {System.out.println(record);}
    //or
    //public void listen(@Payload ConsumerRecord<String,String> record) {System.out.println(record);}
}
