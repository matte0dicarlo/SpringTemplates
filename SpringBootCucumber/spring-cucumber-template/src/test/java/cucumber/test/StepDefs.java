package cucumber.test;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import springcucumbertemplate.Application;
import springcucumbertemplate.totest.SimpleStringReturn;

import static org.hamcrest.MatcherAssert.assertThat;

public class StepDefs extends CucumberIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(Application.class);


    String returnStr;
    String minore = "minore di 500";
    String maggiore = "maggiore di 500";

    SimpleStringReturn simpleStringReturn = new SimpleStringReturn();


    @When("^viene chiamata con valore minore (\\d+)$")
    public void viene_chiamata_con_il_valore_minore_del_valore_soglia(int value) {
        returnStr = simpleStringReturn.returnChar(value);
    }


    @Then("^riceve una stringa che dice che il dato è minore del valore soglia$")
    public void sendMinorDigit() {
        assertThat("minore: ", returnStr.equals(minore));
    }

    @When("^viene chiamata con valore maggiore (\\d+)$")
    public void viene_chiamata_con_il_valore_maggiore_del_valore_soglia(int value) {
        returnStr = simpleStringReturn.returnChar(value);
    }


    @Then("^riceve una stringa che dice che il dato è maggiore del valore soglia$")
    public void sendMajorDigit() {
        assertThat("maggiore: ", returnStr.equals(maggiore));
    }



}
