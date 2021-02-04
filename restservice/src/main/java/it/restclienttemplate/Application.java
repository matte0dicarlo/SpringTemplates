package it.restclienttemplate;

//import it.restclienttemplate.config.DataSourceConfig;
import it.restclienttemplate.config.RestTemplateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import({DataSourceConfig.class, RestTemplateConfig.class})
@Import({RestTemplateConfig.class})
@EntityScan(
        basePackages = {"it.restclienttemplate"}
)
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public Application() {
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, new String[0]);
        logger.info("-- MAIN ---");
    }
}
