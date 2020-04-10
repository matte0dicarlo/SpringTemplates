package it.consumer.consumer.servizi;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.consumer.finder.repo.TripRepository;
import it.consumer.finder.repo.entities.TripEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;

@Service
public class LastEndTripManager {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripEntity tripEntity;

    @Autowired
    ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(LastEndTripManager.class);

    public void getBody(String body){
        logger.info(" from camel (json string):", body);
        try {
            TspData tspData = objectMapper.readValue(body,TspData.class);
            logger.info(" --------------------------> {}", tspData.getSensorsData().get(tspData.getSensorsData().size()-1).getInput());
            //todo: fix exception
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void insertLastEndTrip(String idDevice, Timestamp time, double lat, double lng) throws Exception {
        logger.info(" -- last end trip manager, insert -- \n\n");
        logger.info("\n\n idDevice: " + idDevice);
        logger.info("\n\n time: " + time);
        tripEntity.setIdDevice(idDevice);
        tripEntity.setLastData(time);
        tripEntity.setLat(lat);
        tripEntity.setLng(lng);
        tripRepository.save(tripEntity);

    }

    public void deleteLastEndTrip(String idDevice) throws Exception {
        logger.info(" -- last end trip manager, delete -- \n\n");
        tripRepository.delete(tripRepository.findByIdDevice(idDevice));
    }
}
