package it.springbootbase.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class SimpleConsumer implements Runnable {

    //    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress = "127.0.0.1:9092";

    //    @Value(value = "${kafka.kafkaConsumer.kafkaConsumer-group}")
    private String consumerGroup = "consumer-group-1";

    //    @Value(value = "${kafka.kafkaConsumer.polling.timeout.in.millis}")
    private long pollingTimeout = 3000;

    //    @Value(value = "${kafka.dataTopic}")
    private String dataTopic = "topictest";

    private long restartDelay = 3000;

    private Consumer<String, String> kafkaConsumer;


    @Override
    public void run() {
        kafkaConsumer = new KafkaConsumer<>(configureConsumer());
        kafkaConsumer.subscribe(Arrays.asList(dataTopic));
        try {
            log.info("start");
            Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
            while (true) {
                log.info("start");
                try {
                    currentOffsets.clear();
                    ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(pollingTimeout));
                    for (ConsumerRecord<String, String> record : consumerRecords) {
                        log.info("record.value(): " + record.value());
                    }
                } catch (Exception e) {
                    log.error("Errore consumer:");
                }
            }
        } finally {
            log.error("chiudo il kafkaConsumer");
            kafkaConsumer.close();
            try {
                Thread.sleep(restartDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, Object> configureConsumer() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return consumerProps;
    }

}

