package it.kconsumer.consumerlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.kconsumer.servicelayer.repo.FooRepository;
import it.kconsumer.servicelayer.repo.entities.FooEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FooManager {

    @Autowired
    private FooRepository fooRepository;

    @Autowired
    private FooEntity fooEntity;

    @Autowired
    ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(FooManager.class);

    public void getBodyFromTopic1(String body) throws Exception {
        try {
            FooBodyData fooBodyData = objectMapper.readValue(body, FooBodyData.class);
        } catch (IOException ex) {
            throw new Exception("Errore nel mapping del json", ex);
        }
    }

    public void getBodyFromTopic2(String body) throws Exception {
        try {
            FooBodyData fooBodyData = objectMapper.readValue(body, FooBodyData.class);
        } catch (IOException ex) {
            throw new Exception("Errore nel mapping del json", ex);
        }
    }

}
