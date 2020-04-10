package it.springscheduled.endtripfinder;

import it.springscheduled.endtripfinder.repo.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import it.springscheduled.endtripfinder.repo.entities.*;

@Service
@Slf4j
public class CheckForEndTripService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripEntity tripEntity;

    public void checkForEndTripByIdDevice() {
        log.info("-- Service -- ");

        List<TripEntity> trips = tripRepository.findAllWithLastDataAfter(calculateTreminutifaDate());

        log.info("3 minuti fa date: {}", calculateTreminutifaDate());

        for (int i = 0; i < trips.size(); i++) {
            log.info("->  {}", trips.get(i).getIdDevice());
        }

    }

    public Date calculateTreminutifaDate() {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());

        cal.add(Calendar.SECOND, -180);
        timestamp = new Timestamp(cal.getTime().getTime());
        return timestamp;
    }

}
