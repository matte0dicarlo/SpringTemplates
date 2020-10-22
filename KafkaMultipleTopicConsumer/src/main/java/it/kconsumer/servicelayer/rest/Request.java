package it.kconsumer.servicelayer.rest;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Request {
    private String fooId;
}
