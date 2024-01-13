package demo.kafka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class KafkaProtobufSerializer {


    public static void main(String[] args) {
        SpringApplication.run(KafkaProtobufSerializer.class, args);
    }

}