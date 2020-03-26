package it.fairconnect.crashdatastream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.SendTo;


/**
 *  Binds input topic to output topic
 */
@SpringBootApplication
public class SingleInputToSingleOutputApplication {

    private static final Logger logger = LoggerFactory.getLogger(KafkaStreamsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SingleInputToSingleOutputApplication.class, args);
    }

    @EnableBinding(KafkaStreamsApplication.MultipleProcessor.class)
    public static class KafkaStreamsApplication {



        @StreamListener(MultipleProcessor.FROM_TOPIC_1)
        @SendTo(MultipleProcessor.TO_TOPIC_2)
        public String sendCommands(String input) {
            logger.info("to topic 2: " + input);
            return input;
        }
/*

        @StreamListener(MultipleProcessor.FROM_TOPIC_1)
        @SendTo(MultipleProcessor.TO_TOPIC_3)
        public String sendAlarmAck(String input) {
            logger.info("to topic 3: " + input);
            return input;
        }
*/


        interface MultipleProcessor {
            String FROM_TOPIC_1 = "topic1";
            String TO_TOPIC_2 = "topic2";
            String TO_TOPIC_3 = "topic3";

            @Input(FROM_TOPIC_1)
            SubscribableChannel FROM_TOPIC_1();

            @Output(TO_TOPIC_2)
            MessageChannel topic2();

            @Output(TO_TOPIC_3)
            MessageChannel topic3();
        }

    }
}