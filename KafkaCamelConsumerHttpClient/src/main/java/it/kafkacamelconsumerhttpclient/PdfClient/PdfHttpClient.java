package it.kafkacamelconsumerhttpclient.PdfClient;

import it.kafkacamelconsumerhttpclient.consumer.FeedbackManager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;


@Slf4j
@Component
public class PdfHttpClient {

    @Value("${pdfservice.url}")
    private String pdfServiceUrl;

    @Autowired
    private PdfResponse pdfResponse;

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private AlarmLogComponent alarmLogComponent;

    @Autowired
    private StateVehicleRepository stateVehicleRepository;

    private static final Logger logger = LoggerFactory.getLogger(FeedbackManager.class);

    public void postPdfService(AlarmManagedEvent alarmManagedEvent) throws IOException, URISyntaxException {
        logger.info(" -- HTTP CLIENT --");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        Optional<AlarmEntity> alarmEntity = alarmRepository.findById(alarmManagedEvent.getId());
        if (alarmEntity.get().getFileRef() != null) {
            StateVehicleEntity stateVehicleEntity = stateVehicleRepository.findByIdContratto(alarmEntity.get().getIdContratto());
            try {
                HttpGet pdfRequest = new HttpGet(pdfServiceUrl
                        + "?idAllarme=" + alarmManagedEvent.getId()
                        + "&tipoReport=SOMEREPORT"
                        + "&idContratto=" + stateVehicleEntity.getIdContratto()
                        + "&idVeicolo=" + stateVehicleEntity.getIdVeicolo()
                        + "&idStatoVeicolo=" + stateVehicleEntity.getIdStatoVeicolo());

                logger.info("Url Sent: " + pdfRequest);
                CloseableHttpResponse response = httpClient.execute(pdfRequest);
                logger.info("response " + response.toString());

                if (response.toString().length() > 0) {
                    logger.info("pdf path exists:");
                    HttpEntity entity = response.getEntity();
                    String responseString = EntityUtils.toString(entity, "UTF-8");
                    logger.info("" + responseString);
                } else {
                    logger.info("Client response: pdf path doesn't exist\n");
                }

                response.close();
                logger.info("client closed");
            } finally {
                httpClient.close();
            }
        } else {
            logger.info("alarm {} fileRef is null" + alarmEntity.get().getIdAllarme());
        }
    }
}
