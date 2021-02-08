package it.consumer.services;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("kafka:{{consumer.topic}}?brokers=localhost:9092&consumersCount={{consumer.consumersCount}}&groupId={{consumer.group}}")
                .routeId("FromKafka")
                .log("------- ROUTE BUILDER -------")
                .log("${body}")             //formato JSON.
                .to("bean:myManager?method=getBody(${body})");

    }
}
