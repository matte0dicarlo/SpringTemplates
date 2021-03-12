package it.mockmvctest.test;


import it.mockmvctest.servicelayer.impl.Calculator;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private Calculator calculator;

    @Test
    @DisplayName("Simple multiplication should work")
    public void testMultiply() {
        calculator = new Calculator();
        assertEquals(20, calculator.multiply(4, 5),
                "Regular multiplication should work");
    }
}
