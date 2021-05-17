
package it.springscheduledproducer.scheduledservice;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SchedProducerService {

    @EndpointInject("direct:message-producer")
    ProducerTemplate messageProducer;

    private static final Logger logger = LoggerFactory.getLogger(SchedProducerService.class);

    public void buildBody() throws Exception {
        String body = "test";
        messageProducer.sendBody(body);
    }
}
