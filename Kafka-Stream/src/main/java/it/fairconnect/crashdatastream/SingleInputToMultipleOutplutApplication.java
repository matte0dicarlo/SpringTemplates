
@SpringBootApplication
public class SingleInputToMultipleOutplutApplication {

    private static final Logger logger = LoggerFactory.getLogger(SingleInputToMultipleOutplut.class);


    public static void main(String[] args) {
        SpringApplication.run(SingleInputToMultipleOutplut.class, args);
    }

    @EnableBinding(SingleInputToMultipleOutplut.MultipleProcessor.class)
    @EnableAutoConfiguration
    public static class SingleInputToMultipleOutplut {

        @StreamListener(MultipleProcessor.FROM_TOPIC_1)
        @SendTo({MultipleProcessor.TO_TOPIC_2, MultipleProcessor.TO_TOPIC_3})
        public String sendToMultipleTopics(String input) {
            logger.info("sendCommands: " + input);
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
*/
