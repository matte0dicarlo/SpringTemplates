package it.restclienttemplate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.restclienttemplate.restservice.MyRequest;
import it.restclienttemplate.servicelayer.Service1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first")
@Slf4j
public class MyController {

    @Autowired
    private Service1 service1;

    @PostMapping(value = "/seconds")
    @ApiOperation(value = "Operation description")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public void getCerts(@RequestBody MyRequest request) {
        log.info("-- Received: " + request);


    }

}