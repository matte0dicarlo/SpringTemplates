package it.mockmvctest.servicelayer.impl;

import org.springframework.stereotype.Component;

@Component
public class MultCalculator {

    public int multiply(int a, int b) {
        return a * b;
    }
}