package it.restclienttemplate;

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

@RestController
@Slf4j
public class MyController {


    @GetMapping(value = "/stringvalue", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String stringValue() {
        return "Hello there!";
    }

    /**
     * It returns a string value, no param
     * @return
     */
    @PostMapping(value = "/jsonvaluenoparampost")
    @ResponseBody
    public MyResponse jsonvaluenoparampost() {
        MyResponse response = new MyResponse();
        response.setResponseValue("123");
        return response;

    }

    /**
     * It returns a string value, no param
     * @return
     */
    @GetMapping(value = "/jsonvaluenoparamget")
    @ResponseBody
    public MyResponse jsonvaluenoparamget() {
        MyResponse response = new MyResponse();
        response.setResponseValue("123");
        return response;

    }

    /**
     * It returns a json value, API called with param
     * @return
     */
    @GetMapping(value = "/jsonvalue")
    public ResponseEntity<MyResponse> jsonValue(@RequestBody MyRequest request) {
        log.info("-- Received: " + request);
        log.info("-- Received value: " + request.getRequestValue());
        MyResponse response = new MyResponse();
        response.setResponseValue("123");

        return new ResponseEntity<>(response, HttpStatus.OK);


    }

}