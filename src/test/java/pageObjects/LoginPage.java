package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.CommonUtilities;

public class LoginPage {
        private final WebDriver driver;
        private final CommonUtilities commonUtilities;
        public LoginPage(final WebDriver driver){
            this.driver = driver;
            this.commonUtilities = new CommonUtilities(this.driver);
        }
        public LoginPage clickLoginButton(final WebDriver driver){
            this.driver.get("https://demo.nopcommerce.com/");
            this.commonUtilities.clickButtonWithWait(By.xpath("//a[@class='ico-login']"));
            this.commonUtilities.waitForElementToBeVisible(this.driver.findElement(By.xpath("//button[contains(@class,'login-button')]")));
            return new LoginPage(driver);
        }
        public void enterEmail(final String userEmail){
            this.commonUtilities.getWebElement(By.xpath("//input[@class='email']")).sendKeys(userEmail);
        }
        public void enterPassword(final String userPassword){
            this.commonUtilities.getWebElement(By.xpath("//input[@class='password']")).sendKeys(userPassword);
        }
        public void clickLoginButton(){
            this.commonUtilities.clickButtonWithWait(By.xpath("//button[contains(@class,'login-button')]"));
            this.commonUtilities.waitForElementToBeVisible(this.driver.findElement(By.xpath("//div[@class='header-logo']/a/img")));
        }
        public String getMainTitlePage(){
            return this.driver.findElement(By.xpath("//div[@class='header-logo']/a/img")).getAttribute("alt").trim();
        }
        public String getMyAccountName(){
            return this.driver.findElement(By.xpath("//a[@class='ico-account']")).getText().trim();
        }
}
