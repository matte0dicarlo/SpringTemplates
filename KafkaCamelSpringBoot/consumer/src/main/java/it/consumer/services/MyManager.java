package it.consumer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class MyManager {

    @Autowired
    ObjectMapper objectMapper;

    public void getBody(String body){
//        try {
//            MyModel myModel = objectMapper.readValue(body, MyModel.class);
            log.info("-- MANAGER --\n");
            log.info("message body received: " + body);

//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}
