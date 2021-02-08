package it.consumer.test;

import it.consumer.services.MyRouteBuilder;
import org.apache.camel.*;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashMap;
import java.util.Map;


@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints("bean:*")
public class MyConsumerTest {

    @Autowired
    CamelContext camelContext;

    @Produce
    ProducerTemplate mockKafkaProducer;

    @EndpointInject("mock:bean:myManager?method=getBody(${body})")
    MockEndpoint finalSink;

    @Test
    public void testKafkaRoute() throws Exception {

        AdviceWithRouteBuilder.adviceWith(camelContext, "FromKafka", routeBuilder -> {
            routeBuilder.replaceFromWith("direct:kafka-from");
        });

        Map<String, Object> headers = new HashMap<>();
        headers.put(KafkaConstants.TOPIC, "testTopic");

        mockKafkaProducer.sendBodyAndHeaders("direct:kafka-from", "test-body", headers);

/*
        finalSink.expectedMessageCount(1);
        finalSink.expectedBodiesReceived("test-body");
        finalSink.expectedHeaderReceived(KafkaConstants.TOPIC, "testTopic");
*/
        finalSink.assertIsSatisfied();

    }

}