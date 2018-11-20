package tests;

import objectrepository.DynamicLoading;
import objectrepository.LoginPO;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestDynamicLoading {
    private WebDriver driver;
    private DynamicLoading dynamicLoading;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        dynamicLoading = new DynamicLoading(driver);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
