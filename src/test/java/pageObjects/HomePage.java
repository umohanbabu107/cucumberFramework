package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonUtilities;

public class HomePage {
    final WebDriver driver;
    final CommonUtilities commonUtilities;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.commonUtilities = new CommonUtilities(this.driver);
    }
    
}
