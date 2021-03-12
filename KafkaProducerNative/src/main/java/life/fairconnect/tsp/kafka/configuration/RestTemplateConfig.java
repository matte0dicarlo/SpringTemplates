package life.fairconnect.tsp.kafka.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Value("${rest.timeout.millis}")
    private int timeoutMillis;

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {

        return builder
                .setConnectTimeout(Duration.ofMillis(timeoutMillis))
                .setReadTimeout(Duration.ofMillis(timeoutMillis))
                .build();
    }

}
