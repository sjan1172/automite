package tests;

import objectrepository.LoginPO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestLogin extends Base{

    private LoginPO loginPO;

    @Before
    public void setUp() {
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
}
