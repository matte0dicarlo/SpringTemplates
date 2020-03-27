package it.fairconnect.consumer.servizi;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AlarmData {
    private int idAllarme;
    private String dataEvento;
    private double lat;
    private double lon;
    private int vel;
    private String pathFileCrash;
    private int idContratto;
    private boolean fix;
    private String dataFix;
    private double maxAccX;
    private double maxAccY;
    private double maxAccZ;
    private byte input;
    private int tipo;
    private int versionCrash;
    private int typeFlusso;
    private String idTrip;
    private double orientamento;
    private int nSat;
    private int altitude;
    private int dirMarcia;

}
