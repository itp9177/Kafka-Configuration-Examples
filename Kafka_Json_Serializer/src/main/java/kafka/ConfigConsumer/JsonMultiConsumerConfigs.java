package kafka.ConfigConsumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JsonMultiConsumerConfigs {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    public String producerServer;

    @Bean
    public ConsumerFactory<String, Object> consumerFactorymulti() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, producerServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "foo");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.TYPE_MAPPINGS, "MyMsg1:kafka.dto.MyMsg1,MyMsg2:kafka.dto.MyMsg2");

        return new DefaultKafkaConsumerFactory<>(props ,new StringDeserializer(),new JsonDeserializer<>());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> customFactorymulti() {

        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        factory.setConsumerFactory(consumerFactorymulti());
        return factory;
    }
}
