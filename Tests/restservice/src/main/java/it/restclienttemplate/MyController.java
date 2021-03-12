package it.restclienttemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.restclienttemplate.restservice.MyRequest;
import it.restclienttemplate.restservice.MyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class MyController {

    public static final ObjectMapper mapper = new ObjectMapper();



    @PostMapping(value = "/noparampost")
    @ResponseBody
    public MyResponse jsonValueNoParamPost() {
        MyResponse response = new MyResponse();
        response.setResponseValue("json value without param post");
        return response;

    }

    @GetMapping(value = "/noparamget")
    @ResponseBody
    public MyResponse jsonValueNoParamGet() {
        MyResponse response = new MyResponse();
        response.setResponseValue("json value without param get");
        return response;

    }

    @PostMapping(value = "/parampost")
    public ResponseEntity<MyResponse> jsonValueWithParamPost(@RequestBody String requestAsString) throws IOException {

        MyRequest receivedRequest = mapper.readValue(requestAsString,MyRequest.class);
        log.info("-- Received request as a string: " + requestAsString);
        log.info("-- Received request value: " + receivedRequest.getRequestValue());

        MyResponse response = new MyResponse();
        response.setResponseValue("json value with param post");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/paramget")
    public ResponseEntity<MyResponse> jsonValueWithParamGet(@RequestBody String requestAsString) throws IOException {

        MyRequest receivedRequest = mapper.readValue(requestAsString,MyRequest.class);
        log.info("-- Received request as a string: " + requestAsString);
        log.info("-- Received request value: " + receivedRequest.getRequestValue());

        MyResponse response = new MyResponse();
        response.setResponseValue("json value with param get");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}