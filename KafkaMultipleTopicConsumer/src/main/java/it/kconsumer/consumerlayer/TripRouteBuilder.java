package it.kconsumer.consumerlayer;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TripRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("kafka:{{consumer.topic1}}?brokers={{kafka.brokers}}&consumersCount={{consumer.consumersCount}}&groupId={{consumer.group}}")
                .routeId("FromKafka")
                .log("------- ROUTE BUILDER TOPIC 1-------")
                .log("${body}")             //formato JSON.
                .onException(Exception.class)
                .log(LoggingLevel.ERROR, "it.kconsumer", "Invalid Input")
                .maximumRedeliveries(2).redeliveryDelay(30000)
                .end()
                .to("bean:fooManager?method=getBodyFromTopic1(${body})");

        from("kafka:{{consumer.topic2}}?brokers={{kafka.brokers}}&consumersCount={{consumer.consumersCount}}&groupId={{consumer.group}}")
                .routeId("FromKafka")
                .log("------- ROUTE BUILDER TOPIC 2-------")
                .log("${body}")             //formato JSON.
                .onException(Exception.class)
                .log(LoggingLevel.ERROR, "it.kconsumer", "Invalid Input")
                .maximumRedeliveries(2).redeliveryDelay(30000)
                .end()
                .to("bean:fooManager?method=getBodyFromTopic2(${body})");

    }

}
