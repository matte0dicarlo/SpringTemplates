package kafkacamelconsumerhttpclient.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.kafkacamelconsumerhttpclient.PdfClient.PdfHttpClient;
import it.kafkacamelconsumerhttpclient.consumer.model.AlarmManagedEvent;
import it.kafkacamelconsumerhttpclient.consumer.model.CrashQuestionaire;
import it.kafkacamelconsumerhttpclient.core.alarm.repositories.AlarmRepository;
import it.kafkacamelconsumerhttpclient.core.alarm.repositories.entities.AlarmEntity;
import it.kafkacamelconsumerhttpclient.core.alarmlog.component.AlarmLogComponent;
import it.kafkacamelconsumerhttpclient.core.alarmlog.repositories.AlarmLogRepository;
import it.kafkacamelconsumerhttpclient.core.alarmlog.repositories.entities.AlarmLogEntity;
import it.kafkacamelconsumerhttpclient.core.alarmlog.repositories.entities.AlarmLogEntityId;
import it.kafkacamelconsumerhttpclient.core.models.enums.state.StateEnum;
import it.kafkacamelconsumerhttpclient.core.note.repositories.NoteRepository;
import it.kafkacamelconsumerhttpclient.core.phonecontract.repositories.PhoneContractRepository;
import it.kafkacamelconsumerhttpclient.core.questionaire.repositories.QuestionaireRepository;
import it.kafkacamelconsumerhttpclient.core.questionaire.repositories.entities.QuestionaireEntity;
import it.kafkacamelconsumerhttpclient.persistence.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
public class FeedbackManager {
    private static final Logger logger = LoggerFactory.getLogger(FeedbackManager.class);

    public void getBody(String body) throws Exception {
        try {
            alarmManagedEvent = objectMapper.readValue(body, AlarmManagedEvent.class);
        } catch (IOException ex) {
            throw new Exception("Errore nel mapping del json", ex);
        }
            pdfHttpClient.postPdfService(alarmManagedEvent);
        }
    }

}
