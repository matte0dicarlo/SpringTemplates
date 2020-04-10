package it.topicswitcher.consumer.servizi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TopicSwitcher {

    @Autowired
    AlarmData alarmData;

    @Autowired
    ObjectMapper objectMapper;

    @EndpointInject("direct:crash-producer")
    ProducerTemplate crashproducer;

    @EndpointInject("direct:alarm-producer")
    ProducerTemplate alarmproducer;


    private static final Logger logger = LoggerFactory.getLogger(TopicSwitcher.class);

    public void getBody(String body) throws Exception {

        logger.info("\n-- TOPICSWITCHER --\n");

        //1. deserializza, 2. analizza il tipoflusso, 3. reindirizza alla route giusta che produce.

        try {
            alarmData = objectMapper.readValue(body, AlarmData.class);
        } catch (IOException ex) {
            throw new Exception("Errore nel mapping del json", ex);
        }

        logger.info(" GETFLUSSO" + alarmData.getTypeFlusso());

        if (alarmData.getTypeFlusso() == 0) {         //tipo 2 = crash
            logger.info(" CRASH");
            crashproducer.sendBody(body);
        }
        if (alarmData.getTypeFlusso() == 1) {         //tipo 2 = crash
            logger.info("ALARM");
            alarmproducer.sendBody(body);
        }
    }
}
