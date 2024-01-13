package demo.kafka.Controller;


import com.google.protobuf.Message;
import com.kafka.dto.protobuf.OuterMsg2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kafka.dto.protobuf.OuterMsg1.MyMsg1;
import java.util.UUID;


@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * we produce to same topic different objects types
     */
    @Autowired
    private KafkaTemplate<String,Message> ProtobuffTypeKafkaTemplate1;

    @GetMapping("/multiproducer/msg1")
    public void testmulti(){
        MyMsg1 message = MyMsg1.newBuilder().setName("some name").setAge(26).build();
        ProducerRecord<String, Message> record = new ProducerRecord<>( "topic4", UUID.randomUUID().toString(),message);

        ProtobuffTypeKafkaTemplate1.send(record);
    }
    @GetMapping("/multiproducer/msg2")
    public void testmulti2(){

        OuterMsg2.MyMsg2 message = OuterMsg2.MyMsg2.newBuilder().setName("some other name").setAddress("some other address").build();
        ProducerRecord<String, Message> record = new ProducerRecord<>( "topic4", UUID.randomUUID().toString(),message);

        ProtobuffTypeKafkaTemplate1.send(record);

    }

}
