package kafkacamelconsumerhttpclient.consumer.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Oggetto ricevuto dal topic Kafka
 */

@Data
@Component
public class AlarmManagedEvent {
    private int id;
    private int operatorId;
    private String date;
    private String notes;
    //isEventReal
    private char eventType;
    private String status;

}
