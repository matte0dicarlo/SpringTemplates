package it.kcconsumer.consumer.servizi;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TripRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("kafka:{{consumer.topic}}?brokers={{kafka.brokers}}&consumersCount={{consumer.consumersCount}}&groupId={{consumer.group}}")
                .routeId("FromKafka")
                .log("------- ROUTE BUILDER -------")
                .log("${body}")
        ;
    }
}
