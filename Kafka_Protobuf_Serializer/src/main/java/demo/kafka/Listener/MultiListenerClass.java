package demo.kafka.Listener;

import com.google.protobuf.Message;
import com.kafka.dto.protobuf.OuterMsg1;
import com.kafka.dto.protobuf.OuterMsg2.MyMsg2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

@EnableKafka
@Configuration
@KafkaListener(topics = "topic4" ,containerFactory = "customFactoryMultiProtobuf",clientIdPrefix = "client3", concurrency = "4" ,groupId="protobuf")
public class MultiListenerClass {

    @KafkaHandler
    public void listner1(@Payload OuterMsg1.MyMsg1 msg1){
        System.out.println("multi-1 listner");
        System.out.println(msg1.getAge());
    }

    @KafkaHandler
    public void listner2(@Payload MyMsg2 msg2, @Headers ConsumerRecord<String, Message> record){
        System.out.println("multi-2 listner");
        System.out.println(msg2.getAddress());
        System.out.println(record);
    }
}
