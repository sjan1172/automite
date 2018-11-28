package objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;

public class LoginPO extends Base {

    By txtUsernameLocator = By.id("username");
    By txtPasswordLocator = By.id("password");
    By btnLoginLocator = By.cssSelector("button");

    By successMessageLocator = By.cssSelector(".flash.success");
    By failureMessageLocator = By.cssSelector(".flash.error");

    public LoginPO(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        visit("/login");
        assertTrue(isDisplayed(btnLoginLocator));
    }

    public void with(String username, String password) {
        type(username, txtUsernameLocator);
        type(password, txtPasswordLocator);
        click(btnLoginLocator);
    }

    public Boolean isSuccessMessagePresent() {
        return waitForElementDisplayed(successMessageLocator);
    }

    public Boolean isFailureMessagePresent() {
        return waitForElementDisplayed(failureMessageLocator);
    }
}
