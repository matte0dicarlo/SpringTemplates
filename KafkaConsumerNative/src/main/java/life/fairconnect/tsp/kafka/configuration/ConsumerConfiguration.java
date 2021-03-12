package life.fairconnect.tsp.kafka.configuration;

import lombok.Data;

import java.util.Map;

@Data
public class ConsumerConfiguration {

    private Map<String, Object> consumerProps;

    private String consumerGroup;

    private String dataTopic;

    private long pollingTimeout;

    public ConsumerConfiguration(Map<String, Object> consumerProps, String consumerGroup, String dataTopic, long pollingTimeout ) {
        super();
        this.consumerProps = consumerProps;
        this.consumerGroup = consumerGroup;
        this.dataTopic = dataTopic;
        this.pollingTimeout = pollingTimeout;

    }
}
