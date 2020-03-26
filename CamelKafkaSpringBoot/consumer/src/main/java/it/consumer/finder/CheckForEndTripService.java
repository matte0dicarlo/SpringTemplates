package it.consumer.finder;

import it.consumer.finder.repo.TripRepository;
import it.consumer.finder.rest.NotificationClient;
import it.consumer.finder.rest.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import it.consumer.finder.repo.entities.*;

@Service
@Slf4j
public class CheckForEndTripService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripEntity tripEntity;

    @Autowired
    private NotificationClient notificationClient;

    @Autowired
    private Request request;


    public void checkForEndTripByIdDeviceService() throws IOException {
        log.info("-- Service -- ");
        List<TripEntity> trips = tripRepository.findAllWithLastDataAfter(calculateTreminutifaDate());
        log.info("3 minuti fa date: {}", calculateTreminutifaDate());
        notificationClient.checkForEndTripByIdDevice(trips);
    }

    public Date calculateTreminutifaDate() {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        //test purpouse
        //cal.add(Calendar.YEAR, -5);
        cal.add(Calendar.SECOND, -180);
        timestamp = new Timestamp(cal.getTime().getTime());
        return timestamp;
    }
}
