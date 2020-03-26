package it.fairconnect.endtripfinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportCurrentTimeTask {
    private static final Logger log = LoggerFactory.getLogger(ReportCurrentTimeTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("\nThe time is now {} ", dateFormat.format(new Date()));
    }

}
