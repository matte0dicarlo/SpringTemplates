package it.mockmvctest.servicelayer.impl;

import it.mockmvctest.servicelayer.Service1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class Service1Impl implements Service1 {

    public Integer sum(Integer a, Integer b) {
        log.info("sum");
        return a + b;
    }

}
