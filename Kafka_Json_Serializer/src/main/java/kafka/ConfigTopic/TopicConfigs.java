package kafka.ConfigTopic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafka
public class TopicConfigs {

// create a simple topic for testing

    @Bean
    public NewTopic topic3() {
        return TopicBuilder.name("topic3")
                .partitions(10)
                .replicas(1)
                .build();
    }


}
