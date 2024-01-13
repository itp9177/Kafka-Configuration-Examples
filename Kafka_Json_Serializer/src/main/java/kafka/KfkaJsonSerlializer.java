package kafka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class KfkaJsonSerlializer {


   // @Value("${spring.kafka.producer.bootstrap-servers}")
   // private String producerServer;

    public static void main(String[] args) {
        SpringApplication.run(KfkaJsonSerlializer.class, args);
    }

}