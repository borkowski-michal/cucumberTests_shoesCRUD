package borkowski.store.BDDTests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.assertEquals;


class checkColorOfShoes{
    static String isItThatColor(String IsIt){
        if ("red".equals(IsIt)) {
            return "Yes";
        }
        return "Nope";
    }
}

public class StepIsItRed {

    String shoesColor;
    String respond;

    @Given("this is {string}")
    public void this_is(String col) {
        this.shoesColor = col;
    }

    @When("I ask are you sure I's red")
    public void i_ask_are_you_sure_I_s_red() {
        this.respond = checkColorOfShoes.isItThatColor(shoesColor);
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedColor) {
        assertEquals(expectedColor, respond);
    }
}
