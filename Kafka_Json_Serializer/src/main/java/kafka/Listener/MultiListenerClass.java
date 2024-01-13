package kafka.Listener;

import kafka.dto.MyMsg1;
import kafka.dto.MyMsg2;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@EnableKafka
@Configuration
@KafkaListener(topics = "topic3" ,containerFactory = "customFactorymulti",clientIdPrefix = "client3", concurrency = "4" ,groupId="my4")
public class MultiListenerClass {

    @KafkaHandler
    public void listner1(@Payload MyMsg1 myMsg1){
        System.out.println("multi-1 listner");
        System.out.println(myMsg1);
    }

    @KafkaHandler
    public void listner2(@Payload MyMsg2 myMsg2){
        System.out.println("multi-2 listner");
        System.out.println(myMsg2);
    }

}
