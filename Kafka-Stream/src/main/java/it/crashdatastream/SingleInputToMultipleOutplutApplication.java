package it.crashdatastream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.SendTo;


@SpringBootApplication
public class SingleInputToMultipleOutplutApplication {


    public static void main(String[] args) {
        SpringApplication.run(SingleInputToMultipleOutplut.class, args);
    }

    @EnableBinding(SingleInputToMultipleOutplut.MultipleProcessor.class)
    @EnableAutoConfiguration
    public static class SingleInputToMultipleOutplut {

        @StreamListener(MultipleProcessor.FROM_TOPIC_1)
        @SendTo({MultipleProcessor.TO_TOPIC_2, MultipleProcessor.TO_TOPIC_3})
        public String sendToMultipleTopics(String input) {
//            logger.info("sendCommands: " + input);
            return input;
        }

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
