package it.mockmvctest.servicelayer;

import org.springframework.stereotype.Component;

@Component
public interface SumCalculator {
    int sum(int a, int b);


}
