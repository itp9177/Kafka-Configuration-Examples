package kafka.ConfigProducer;

import kafka.dto.MyMsg1;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JsonSerializedProducer {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    public String producerServer;

    @Bean
    public KafkaTemplate<String, MyMsg1> kafkaTemplateJson(ProducerFactory<String, MyMsg1> producerFactoryJson)
    {
        return new KafkaTemplate<>(producerFactoryJson);
    }

    @Bean
    public ProducerFactory<String, MyMsg1> producerFactoryJson() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,producerServer);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
}
