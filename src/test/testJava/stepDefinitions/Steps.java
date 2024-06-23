package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Steps {
    WebDriver driver = new ChromeDriver();
    @Given("the user opens the application and go to login page")
    public void the_user_opens_the_application_and_go_to_login_page() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[@class='ico-login']")).click();
    }
    @When("the user enter valid username and password \\(username : {string}, password: {string})")
    public void the_user_enter_valid_username_and_password_username_password(String username, String password) {
        driver.findElement(By.xpath("//input[@class='email']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@class='password']")).sendKeys(password);
    }
    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        driver.findElement(By.xpath("//button[contains(@class,'login-button')]")).click();
    }
    @Then("the user should navigate to my account page")
    public void the_user_should_navigate_to_my_account_page() {
        String mainPageTitle = driver.findElement(By.xpath("//div[@class='header-logo']/a/img")).getAttribute("alt").trim();
        Assert.assertEquals(mainPageTitle, "nopCommerce demo store");
    }
    @Then("the user should see the username in my account page")
    public void the_user_should_see_the_username_in_my_account_page() {
        String myAccountName  = driver.findElement(By.xpath("//a[@class='ico-account']")).getText().trim();
        Assert.assertEquals(myAccountName, "My account");
        System.out.println("Login successful");
        driver.close();
    }
}
