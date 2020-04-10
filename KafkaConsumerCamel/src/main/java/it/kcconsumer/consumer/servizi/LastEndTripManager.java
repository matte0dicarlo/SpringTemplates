package it.kcconsumer.consumer.servizi;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.kcconsumer.endtripfinder.exception.MissingItemException;
import it.kcconsumer.endtripfinder.repo.TripRepository;
import it.kcconsumer.endtripfinder.repo.entities.TripEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.MissingFormatArgumentException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LastEndTripManager {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripEntity tripEntity;

    @Autowired
    ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(LastEndTripManager.class);

    public void getBody(String body) throws Exception {
        try {
            TspData tspData = objectMapper.readValue(body, TspData.class);
            SensorData lastSensorData = tspData.getSensorsData().get(tspData.getSensorsData().size() - 1);
            int input = lastSensorData.getInput();
            logger.info(" Input Last SensorData: {}", input);
            if (input == 0) {
                insertLastEndTrip(tspData.getIdDevice(), tspData.getStreamDate(), lastSensorData.getLongitude(), lastSensorData.getLatitude());
            } else if (input == 1) {
                deleteLastEndTrip(tspData.getIdDevice());
            } else throw new Exception("Inconsistenza nel valore dell'input del SensorData");
        } catch (IOException ex) {
            throw new Exception("Errore nel mapping del json", ex);
        }
    }

    public void insertLastEndTrip(String idDevice, String time, double lat, double lng) throws Exception {
        logger.info(" -- last end trip manager, insert -- \n\n");
        logger.info("\n\n idDevice: " + idDevice);
        logger.info("\n\n time: " + time);
        tripEntity.setIdDevice(idDevice);
        DateFormat stringDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");       //2020-02-24 11:06:26
        tripEntity.setLastData(stringDateFormat.parse(time));
        tripEntity.setLat(lat);
        tripEntity.setLng(lng);
        tripEntity.setProcessed(0);
        logger.info(" -- ABOUT TO SAVE -- ");
        tripRepository.save(tripEntity);
        logger.info(" -- SAVED trip with idDevice: -- {} ", tripEntity.getIdDevice());
    }

    public void deleteLastEndTrip(String idDevice) {
        try {
            logger.info(" -- ABOUT TO DELETE idDevice: {}", tripRepository.findById(idDevice).get().getIdDevice());
            TripEntity tripEntity = tripRepository.findById(idDevice)
                    .orElseThrow(() -> new MissingItemException("Elemento da cancellare non esistente nel db."));
            tripRepository.delete(tripEntity);
            logger.info(" -- DELETED trip ");
        } catch (MissingItemException e) {
            logger.warn(e.getMessage());
        }
    }

}
