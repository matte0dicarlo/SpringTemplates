package tester;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Test {

    private static final Logger log = (Logger) LoggerFactory.getLogger(Test.class);

    public void tester() {
        log.info("component.. ");
    }


}