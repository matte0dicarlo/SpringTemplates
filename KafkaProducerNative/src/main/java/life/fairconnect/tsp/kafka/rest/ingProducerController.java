package life.fairconnect.tsp.kafka.rest;

import life.fairconnect.tsp.kafka.producer.IngProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ingProducerController {

    @Autowired
    private IngProducer producer;

    @PostMapping("/publish")
    public void publish() {
        /*todo:
           - rename method
           - get body
           - add serializer


        */
        producer.publishRawData("test message1");

    }
}
