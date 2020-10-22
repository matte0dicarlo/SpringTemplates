package it.kconsumer.servicelayer.repo.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@javax.persistence.Entity
@Component
@Table(name = "lastEndTrip")
public class FooEntity {

/*    @Id
    @Column(name = "iddevice")
    private String idDevice;

    @Column(name = "lastdata")
    private Date lastData;

    @Column(name = "processed")
    private Integer processed;

    @Column(name = "lng")
    private double lng;

    @Column(name = "lat")
    private double lat;*/
}
