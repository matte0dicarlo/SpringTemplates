package it.mockmvctest.test;

import it.mockmvctest.servicelayer.impl.SumCalculatorImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@SpringBootTest
public class SumTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Simple test")
    public void test_service_1() throws Exception {
        SumCalculatorImpl service1Impl = new SumCalculatorImpl();
        assertEquals(5, service1Impl.sum(2, 3));

    }


}

