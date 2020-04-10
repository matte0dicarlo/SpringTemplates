package kafkacamelconsumerhttpclient.PdfClient;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PdfRequest {
    private String tipoReport;
    private String idAllarme;
    private String idContratto;
    private String idVeicolo;
    private String idStatoVeicolo;
}
