package life.fairconnect.tsp.kafka.configuration;

import life.fairconnect.tsp.kafka.consumer.InjestionConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class KafkaConsumerConfiguration {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.consumer.consumer-group}")
    private String consumerGroup;

    @Value(value = "${kafka.consumer.polling.timeout.in.millis}")
    private long pollingTimeout;

    @Value(value = "${kafka.dataTopic}")
    private String dataTopic;

    @Bean
    public Object createConsumer() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        ConsumerConfiguration componentConfiguration = new ConsumerConfiguration(consumerProps, consumerGroup, dataTopic, pollingTimeout);

        return new InjestionConsumer(componentConfiguration);
    }
}
