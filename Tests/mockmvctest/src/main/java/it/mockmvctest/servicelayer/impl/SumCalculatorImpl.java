package it.mockmvctest.servicelayer.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SumCalculatorImpl implements it.mockmvctest.servicelayer.SumCalculator {

    public int sum(int a, int b) {
        log.info("sum");
        return a + b;
    }

}
