package objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class LoginPO {
    private WebDriver driver;

    By txtUsernameLocator = By.id("username");
    By txtPasswordLocator = By.id("password");
    By btnLoginLocator = By.cssSelector("button");

    By successMessageLocator = By.cssSelector(".flash.success");
    By failureMessageLocator = By.cssSelector(".flash.error");

    public LoginPO(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://the-internet.herokuapp.com/login");
        assertTrue(driver.findElement(btnLoginLocator).isDisplayed());
    }

    public void with(String username, String password) {
        driver.findElement(txtUsernameLocator).sendKeys(username);
        driver.findElement(txtPasswordLocator).sendKeys(password);
        driver.findElement(btnLoginLocator).click();
        System.out.println("===== USER LOGGED IN =====");
    }

    public Boolean isSuccessMessagePresent(){
        return driver.findElement(successMessageLocator).isDisplayed();
    }

    public Boolean isFailureMessagePresent(){
        return driver.findElement(failureMessageLocator).isDisplayed();
    }

}
