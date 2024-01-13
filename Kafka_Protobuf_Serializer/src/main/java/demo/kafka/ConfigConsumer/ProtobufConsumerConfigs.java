package demo.kafka.ConfigConsumer;

import com.google.protobuf.Message;

import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializerConfig;
import io.confluent.kafka.serializers.subject.RecordNameStrategy;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProtobufConsumerConfigs {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    public String producerServer;

    @Bean
    public ConsumerFactory<String, Message> consumerFactoryMultiProtobuf() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, producerServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "foo");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaProtobufDeserializer.class);
        // props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(KafkaProtobufDeserializerConfig.DERIVE_TYPE_CONFIG, true); // listen to multiple object types we need to set derive type true
        props.put(KafkaProtobufDeserializerConfig.VALUE_SUBJECT_NAME_STRATEGY, RecordNameStrategy.class); // value schema subject strategy ex: packagename.classname
        props.put("schema.registry.url", "http://localhost:8081");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(props ,new StringDeserializer(),new KafkaProtobufDeserializer<>());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message> customFactoryMultiProtobuf() {

        ConcurrentKafkaListenerContainerFactory<String, Message> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        factory.setConsumerFactory(consumerFactoryMultiProtobuf());
        return factory;
    }
}
