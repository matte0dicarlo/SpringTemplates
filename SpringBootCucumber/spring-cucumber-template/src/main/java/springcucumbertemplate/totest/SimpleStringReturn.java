package springcucumbertemplate.totest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleStringReturn {
    private static final Logger log = LoggerFactory.getLogger(SimpleStringReturn.class);

    public String returnChar(int digit) {

        String returnStr = null;
        log.info("Digit Received: " + digit);
        if (digit < 500) returnStr = "minore di 500";
        else if (digit >= 500) returnStr = "maggiore di 500";
        return returnStr;
    }

}
