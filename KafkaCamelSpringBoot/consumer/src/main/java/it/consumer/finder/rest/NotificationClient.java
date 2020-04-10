package it.consumer.finder.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.consumer.finder.repo.entities.TripEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class NotificationClient {

    @Value("${radarmeteo.notification-url}")
    private String notificatioUrl;

    @Autowired
    private Response response;

    @Autowired
    ObjectMapper objectMapper;

    private Response sendNotification(Request request) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        JsonNode responseAsJsonString = restTemplate.postForObject(notificatioUrl, request, JsonNode.class);
        return response;
    }

    public void checkForEndTripByIdDevice(List<TripEntity> trips) {
        for (int i = 0; i < trips.size(); i++) {
            log.info("idDevice con lastdata < 3 minuti: {}", trips.get(i).getIdDevice());
            if (trips.get(i).getProcessed() == 0) {
                log.info("idDevice con processed = 0: {}", trips.get(i).getIdDevice());
                log.info("-- INVIO NOTIFICA --");
//                notificationClient.sendNotification(request);
            }
        }
    }
}
