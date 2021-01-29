package it.restclienttemplate.rest;

import it.restclienttemplate.rest.client.ClientUnoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledCallService {

    @Autowired
    private ClientUnoService clientUno;

    @Scheduled(fixedRate = 3000)
    public void useClientUno() {
        log.info("using client uno");
        clientUno.performCall();
    }

}
