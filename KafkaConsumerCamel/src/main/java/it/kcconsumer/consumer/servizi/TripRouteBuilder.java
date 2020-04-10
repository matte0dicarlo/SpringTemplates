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
                .log("${body}")             //formato JSON.
                .onException(Exception.class)
                .log(LoggingLevel.ERROR, "it.kcconsumer.radarmeteo", "Invalid Input")
                .maximumRedeliveries(2).redeliveryDelay(30000)
                .end()
                .to("bean:lastEndTripManager" +
                        "?method=getBody(${body})")
        ;
    }
}
