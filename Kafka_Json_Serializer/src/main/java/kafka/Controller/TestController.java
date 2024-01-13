package kafka.Controller;


import kafka.dto.MyMsg1;
import kafka.dto.MyMsg2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private KafkaTemplate<String, MyMsg1> kafkaTemplateJson;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplateJsonMulti;


    @GetMapping("/jsonproducer")
    public void test1() {
        MyMsg1 myMsg1 = new MyMsg1();

        myMsg1.setAge(26);
        myMsg1.setName("myname");

        ProducerRecord<String, MyMsg1> record = new ProducerRecord<>("topic2", UUID.randomUUID().toString(), myMsg1); // write a MyMsg 1 with json serialization
        kafkaTemplateJson.send(record);

    }

    /**
     * produce different message types to same topic with multiple data type producer
     **/


    @GetMapping("/multiproducer/msg1")  // MyMsg1 type message to topic3 with json serialization class
    public void test2() {
        MyMsg1 myMsg1 = new MyMsg1();

        myMsg1.setAge(26);
        myMsg1.setName("myname");

        ProducerRecord<String, Object> record = new ProducerRecord<>("topic3", UUID.randomUUID().toString(), myMsg1); // write a MyMsg 1 with json serialization
        kafkaTemplateJsonMulti.send(record);

    }

    @GetMapping("/multiproducer/msg2")  // MyMsg2 type message to topic3 with json serialization class
    public void test3() {
        MyMsg2 myMsg2 = new MyMsg2();

        myMsg2.setAddress("my address");
        myMsg2.setName("myname");

        ProducerRecord<String, Object> record = new ProducerRecord<>("topic3", UUID.randomUUID().toString(), myMsg2); // write a MyMsg 2 with json serialization
        kafkaTemplateJsonMulti.send(record);

    }
}
