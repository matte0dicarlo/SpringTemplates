package kafkacamelconsumerhttpclient.consumer;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FeedbackRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("kafka:{{consumer.topic}}?brokers={{kafka.brokers}}&consumersCount={{consumer.consumersCount}}&groupId={{consumer.group}}")
                .routeId("FromKafka")
                .log("------- ROUTE BUILDER -------")
                .log("${body}")             //formato JSON.
                .onException(Exception.class)
                .log(LoggingLevel.ERROR, "kafkacamelconsumerhttpclient", "Invalid Input")
                .maximumRedeliveries(2).redeliveryDelay(30000)
                .end()
                .to("bean:feedbackManager?method=getBody(${body})");
    }
}
