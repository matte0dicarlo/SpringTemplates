package it.consumer.finder.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class Request {

    @JsonProperty(value = "id_device")
    private String idDevice;

    //@JsonIgnore
    private Date lastData;

    //@JsonIgnore
    private Integer processed;

    private double lng;

    private double lat;
}
