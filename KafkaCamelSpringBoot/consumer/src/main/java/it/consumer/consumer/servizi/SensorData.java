package it.consumer.consumer.servizi;

import lombok.Data;
import java.util.Date;

@Data
public class SensorData {
    private Date timestamp;
    private double latitude;
    private double longitude;
    private int altitude;
    private int input;
    private int speed;
    private int angle;
    private boolean treshold;
    private double accX;
    private double accY;
    private double accZ;
    private double rX;
    private double rY;
    private double rZ;
    private boolean fix;
}