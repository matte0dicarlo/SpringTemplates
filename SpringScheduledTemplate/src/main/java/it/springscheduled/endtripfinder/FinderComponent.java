package it.springscheduled.endtripfinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FinderComponent {

    private static final Logger log = LoggerFactory.getLogger(FinderComponent.class);

    @Autowired
    private CheckForEndTripService checkForEndTripService;

    @Scheduled(fixedRate = 30000)
    public void checkForEndTrip() {
        log.info("component.. ");
        checkForEndTripService.checkForEndTripByIdDevice();
    }


}