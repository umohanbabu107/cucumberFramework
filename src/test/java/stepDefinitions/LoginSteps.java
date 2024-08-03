package stepDefinitions;

import base.BaseClass;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;

public class LoginSteps{

    WebDriver driver;
    LoginPage loginPage;
    @Given("the user opens the application and go to login page")
    public void the_user_opens_the_application_and_go_to_login_page() {
//        BaseClass baseClass = new BaseClass();
//        this.driver = baseClass.initializeDriver();
//        baseClass.configureDriver(this.driver);
        this.driver = BaseClass.getDriver();
        this.loginPage = new LoginPage(this.driver);
        this.loginPage = this.loginPage.clickLoginButton(this.driver);
    }
    @When("the user enter valid username and password \\(username : {string}, password: {string})")
    public void the_user_enter_valid_username_and_password_username_password(final String username, final String password) {
        this.loginPage.enterEmail(username);
        this.loginPage.enterPassword(password);
    }
    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        this.loginPage.clickLoginButton();
    }
    @Then("the user should navigate to my account page")
    public void the_user_should_navigate_to_my_account_page() {
        Assert.assertEquals(this.loginPage.getMainTitlePage(), "nopCommerce demo store");
    }
    @Then("the user should see the username in my account page")
    public void the_user_should_see_the_username_in_my_account_page() {
        Assert.assertEquals(this.loginPage.getMyAccountName(), "My account");
    }
}
