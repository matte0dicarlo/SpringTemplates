package life.fairconnect.tsp.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import life.fairconnect.tsp.kafka.configuration.ConsumerConfiguration;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class InjestionConsumer implements Runnable {

    private String dataTopic;

    private long pollingTimeout;

    private long restartDelay = 3000;

    @Autowired
    private ObjectMapper jsonMapper;

    private Consumer<String, String> kafkaConsumer;

    private Map<String, Object> consumerProps;

//    private RawData rawData;


    public InjestionConsumer(ConsumerConfiguration consumerConfiguration) {
        consumerProps = consumerConfiguration.getConsumerProps();
        dataTopic = consumerConfiguration.getDataTopic();
        pollingTimeout = consumerConfiguration.getPollingTimeout();
        startKafkaConsumer();
    }

    private void startKafkaConsumer() {
        log.info("Istanzio un nuovo consumer");
        kafkaConsumer = new KafkaConsumer<>(consumerProps);
        kafkaConsumer.subscribe(Arrays.asList(dataTopic));
        new Thread(this).start();
    }


    @Override
    public void run() {
        try {
            Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
            while (true) {
                try {
                    currentOffsets.clear();
                    ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(pollingTimeout));
                    for (ConsumerRecord<String, String> record : consumerRecords) {
                        log.info("record.value(): " + record.value());
                    }
                } catch (Exception e) {
                    log.error("Errore consumer:", e);
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
            startKafkaConsumer();
        }
    }
}

