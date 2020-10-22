package it.kconsumer.servicelayer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;

@Slf4j
@Component
public class NotificationClient {

    @Value("${restservice.notification-url}")
    private String notificatioUrl;

    @Autowired
    private Response response;

    @Autowired
    private Request request;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NotificationClient notificationClient;

    private Response sendNotification(Request request) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        JsonNode responseAsJsonString = restTemplate.postForObject(notificatioUrl, request, JsonNode.class);
        return response;
    }

}
