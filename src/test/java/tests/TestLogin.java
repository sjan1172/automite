package tests;

import objectrepository.LoginPO;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestLogin {

    private WebDriver driver;
    private LoginPO loginPO;

    @Before
    public void setUp(){
        driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        loginPO = new LoginPO(driver);
    }

    @Test
    public void successLogin(){
//        String appUrl = "http://the-internet.herokuapp.com/login";
//        driver.get(appUrl);

        loginPO.with("tomsmith", "SuperSecretPassword!");
        assertTrue("Success message does not present after logging in", loginPO.isSuccessMessagePresent());
    }

    @Test
    public void invalidPasswordLogin(){
//        String appUrl = "http://the-internet.herokuapp.com/login";
//        driver.get(appUrl);

        loginPO.with("tomsmith", "SuperSecretPassword123!");
        assertTrue("Failure message does not present after logging in", loginPO.isFailureMessagePresent());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
