package stepDefinitions;

import base.BaseClass;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.RegistrationPage;


public class RegistrationSteps{

    WebDriver driver;
    RegistrationPage registrationPage;
    @Given("the user opens application url and enters into registration page")
    public void the_user_opens_application_url_and_enters_into_registration_page() {
        BaseClass baseClass = new BaseClass();
        this.driver = baseClass.initializeDriver();
        baseClass.configureDriver(this.driver);
        //this.driver = BaseClass.getDriver();
        this.registrationPage = new RegistrationPage(this.driver);
        this.registrationPage = this.registrationPage.clickRegisterButton(this.driver);
    }
    @When("the user enter all required detials")
    public void the_user_enter_all_required_detials() {
        this.registrationPage.enterFirstName("Mohan");
        this.registrationPage.enterLastName("Test");
        this.registrationPage.enterEmail("mohantest@gmail.com");
        this.registrationPage.enterPassword("Mohan123");
        this.registrationPage.enterConfirmPassword("Mohan123");
    }
    @When("the user clicks the register button")
    public void the_user_clicks_the_register_button() {
        this.registrationPage.clickConfirmRegisterButton();
    }
    @Then("if the user doesn't exists then should get success messgae like \\(successMessage: {string}) otherwise error like \\(errorMessage: {string})")
    public void if_the_user_doesn_t_exists_then_should_get_success_messgae_like_success_message_otherwise_error_like_error_message(String successMessage, String errorMessage) {

        if(!this.registrationPage.isLogoutButtonVisible()){
            Assert.assertEquals(this.registrationPage.getSuccessMessage(), successMessage);
        }
        else {
            Assert.assertEquals(this.registrationPage.getFailedMessage(),errorMessage);
        }
    }
}
