package tests;

import objectrepository.LoginPO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestLogin {

    private WebDriver driver;
    private LoginPO loginPO;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        loginPO = new LoginPO(driver);
    }

    @Test
    public void successLogin() {
        loginPO.with("tomsmith", "SuperSecretPassword!");
        assertTrue("Success message does not present after logging in", loginPO.isSuccessMessagePresent());
    }

    @Test
    public void invalidPasswordLogin() {
        loginPO.with("tomsmith", "SuperSecretPassword123!");
        assertTrue("Failure message does not present after logging in", loginPO.isFailureMessagePresent());
        assertFalse("Success message shown for invalid password login", loginPO.isSuccessMessagePresent());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
