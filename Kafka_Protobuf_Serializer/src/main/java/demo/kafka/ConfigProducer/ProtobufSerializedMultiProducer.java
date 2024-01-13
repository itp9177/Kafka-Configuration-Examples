package demo.kafka.ConfigProducer;

import com.google.protobuf.Message;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializerConfig;
import io.confluent.kafka.serializers.subject.RecordNameStrategy;
import io.confluent.kafka.serializers.subject.TopicNameStrategy;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProtobufSerializedMultiProducer {

    @Value("${spring.kafka.producer.bootstrap-servers}")

    private String producerServer;
    @Bean
    public ProducerFactory<String, Message> ProtobuffTypeProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,producerServer);
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaProtobufSerializer.class);
        configProps.put("schema.registry.url", "http://127.0.0.1:8081"); // schema registry url
        configProps.put(KafkaProtobufSerializerConfig.VALUE_SUBJECT_NAME_STRATEGY, RecordNameStrategy.class); // TopicNameStrategy can be used
        configProps.put("auto.register.schemas", "true");



        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean(name = "myprotobuf")
    public KafkaTemplate<String, Message> ProtobuffTypeKafkaTemplate1() {
        return new KafkaTemplate<>(ProtobuffTypeProducerFactory());
    }
}
