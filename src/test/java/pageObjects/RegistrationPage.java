package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.CommonUtilities;


public class RegistrationPage {
    private final WebDriver driver;
    private final CommonUtilities commonUtilities;

    public RegistrationPage(final WebDriver driver){
        this.driver = driver;
        this.commonUtilities = new CommonUtilities(this.driver);
    }

    public RegistrationPage clickRegisterButton(final WebDriver driver){
        this.driver.get("https://demo.nopcommerce.com/");
        this.commonUtilities.clickButtonWithWait(By.xpath("//a[text()='Register']"));
        this.commonUtilities.waitForElementToBeVisible(this.driver.findElement(By.xpath("//div/h1[text()='Register']")));
        return new RegistrationPage(driver);
    }

    public void enterFirstName(final String firstName){
        this.commonUtilities.getWebElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);
    }
    public void enterLastName(final String lastName){
        this.commonUtilities.getWebElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);
    }
    public void enterEmail(final String email){
        this.commonUtilities.getWebElement(By.xpath("//input[@id='Email']")).sendKeys(email);
    }
    public void enterPassword(final String password){
        this.commonUtilities.getWebElement(By.xpath("//input[@id='Password']")).sendKeys(password);
    }
    public void enterConfirmPassword(final String confirmPassword){
        this.commonUtilities.getWebElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(confirmPassword);
    }
    public void clickConfirmRegisterButton(){
        this.commonUtilities.clickButtonWithWait(By.xpath("//button[@id='register-button']"));
        try {
            Thread.sleep(2000);
        }
        catch (final Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public String getSuccessMessage(){
        return this.commonUtilities.getWebElement(By.xpath("//div[@class='result']")).getText().trim();
    }
    public String getFailedMessage(){
        return this.commonUtilities.getWebElement(By.xpath("//div[contains(@class,'message-error')]//li")).getText().trim();
    }
    public boolean isLogoutButtonVisible(){
        final WebElement loginElement = this.commonUtilities.getWebElement(By.xpath("//a[text()='Log out']"));
        return loginElement == null;
    }
}
