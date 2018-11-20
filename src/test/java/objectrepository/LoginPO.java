package objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class LoginPO extends Base {

    By txtUsernameLocator = By.id("username");
    By txtPasswordLocator = By.id("password");
    By btnLoginLocator = By.cssSelector("button");

    By successMessageLocator = By.cssSelector(".flash.success");
    By failureMessageLocator = By.cssSelector(".flash.error");

    public LoginPO(WebDriver driver) {
        super(driver);
        visit("http://the-internet.herokuapp.com/login");
        assertTrue(isDisplayed(btnLoginLocator));
    }

    public void with(String username, String password) {
        type(username, txtUsernameLocator);
        type(password, txtPasswordLocator);
        click(btnLoginLocator);
        System.out.println("===== USER LOGGED IN =====");
    }

    public Boolean isSuccessMessagePresent(){
        return isDisplayed(successMessageLocator);
    }

    public Boolean isFailureMessagePresent(){
        return isDisplayed(failureMessageLocator);
    }
}
