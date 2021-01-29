package it.restclienttemplate.rest.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientUnoService implements ClientUnoInterface{
    public void performCall(){
        log.info("client uno perform call");
    }
}
