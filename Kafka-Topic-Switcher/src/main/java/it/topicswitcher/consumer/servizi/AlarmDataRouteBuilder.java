package it.topicswitcher.consumer.servizi;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AlarmDataRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("kafka:{{consumer.topic}}?brokers={{kafka.brokers}}&consumersCount={{consumer.consumersCount}}&groupId={{consumer.group}}")
//                .log("------- ROUTE BUILDER -------")
                .routeId("FromKafka")
                .log("${body}")
                .to("bean:topicSwitcher?method=getBody(${body})")
        ;

        from("direct:crash-producer")
                .log("\n-- CRASH PRODUCER -- \n ${body}\n")
                .routeId("crashproducer")
                .to("kafka:{{crash-producer.topic}}?brokers={{kafka.brokers}}")
                ;

        from("direct:alarm-producer")
                .log("\n-- ALARM PRODUCER -- \n ${body}\n")
                .routeId("alarmproducer")
                .to("kafka:{{alarm-producer.topic}}?brokers={{kafka.brokers}}")
                ;

    }
}
