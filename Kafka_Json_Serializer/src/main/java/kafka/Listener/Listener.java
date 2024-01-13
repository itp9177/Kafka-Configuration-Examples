package kafka.Listener;

import kafka.dto.MyMsg1;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@Configuration
public class Listener {

     @KafkaListener( topics = "topic2" ,containerFactory = "customFactoryJson",groupId = "foo")
     public void listen(ConsumerRecord<String, MyMsg1> record) {System.out.println(record.value().getClass());}

    //you can use
    //public void listen(String record) {System.out.println(record);}
    //or
    //public void listen(@Payload ConsumerRecord<String,String> record) {System.out.println(record);}
}
