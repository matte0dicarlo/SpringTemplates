package it.fairconnect.consumer.servizi;

import lombok.Data;
import java.util.List;

@Data
public class TspData {
    private String voucherId;
    private String idDevice;
    private String idTrip;
    private String idPacket;
    private String idSeq;
    private int numPos;
    private int idProtocol;
    private int idOrganization;
    private String streamDate;
    private List<SensorData> sensorsData;
}
