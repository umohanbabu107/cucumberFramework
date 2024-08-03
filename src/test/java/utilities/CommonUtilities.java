package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.NoSuchElementException;


public class CommonUtilities {
    WebDriver driver;
    WebDriverWait webDriverWait;
    public CommonUtilities(WebDriver driver){
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
    }

    public Person getPersonData() {
        final ObjectMapper objectMapper = new ObjectMapper();
        final File file = new File(".\\resources\\testData\\employee.json");
        try {
            return objectMapper.readValue(file, Person.class);
        }
        catch(final Exception ignored){

        }
        return null;
    }
    public WebElement getWebElement(final By xpath){
        final WebElement webElement;
        try{
            webElement = this.driver.findElement(xpath);
            return webElement;
        }
        catch (final Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
    public void clickButtonWithWait(final By element) {
        try{
            final WebElement webElement = this.webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
            webElement.click();
        }
        catch (final Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public void waitForElementToBeVisible(final WebElement element){
        try {
            this.webDriverWait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (final Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
