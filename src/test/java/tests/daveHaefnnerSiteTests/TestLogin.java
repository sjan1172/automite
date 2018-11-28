package tests.daveHaefnnerSiteTests;

import objectrepository.LoginPO;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.Base;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class TestLogin extends Base {

    private LoginPO loginPO;

    @BeforeMethod
    public void setUp() {
        loginPO = new LoginPO(driver, wait);
    }

    @Test
    public void successLogin() {
        loginPO.with("tomsmith", "SuperSecretPassword!");
        assertTrue(loginPO.isSuccessMessagePresent(), "Success message does not present after logging in");
    }

    @Test
    public void invalidPasswordLogin() {
        loginPO.with("tomsmith", "SuperSecretPassword123!");
        assertTrue(loginPO.isFailureMessagePresent(), "Failure message does not present after logging in");
        assertFalse(loginPO.isSuccessMessagePresent(), "Success message shown for invalid password login");
    }
}
