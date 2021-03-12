package life.fairconnect.tsp.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@Slf4j
public class IngProducerImpl implements IngProducer {

    @Value(value = "${kafka.dataTopic}")
    private String producerTopic;

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Override
    public void publishRawData(String message) {

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapAddress);
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<Integer, String> producer = new KafkaProducer<Integer, String>(props);
        ProducerRecord producerRecord = new ProducerRecord<Integer, String>(producerTopic, message);    //todo: replace with rawdata
        producer.send(producerRecord);
        producer.close();
    }
}
