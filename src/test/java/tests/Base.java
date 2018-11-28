package tests;

import com.saucelabs.saucerest.SauceREST;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Base implements Config {
    protected WebDriver driver;
    private String testName;
    private String sessionId;
    private SauceREST sauceCient;

    @BeforeClass
    protected void before() throws Throwable {
        loadDriver();
    }

    @AfterClass
    protected void after() {
        unLoadDriver();
    }

    private void setDriverOnLocalServer() {
        String pathToFirefoxGeckoDriverMAC = System.getProperty("user.dir") + "/libraries/macOS/geckodriver";
        String pathToChromeDriverMAC = System.getProperty("user.dir") + "/libraries/macOS/chromedriver";
        String pathToFirefoxGeckoDriverWIN = System.getProperty("user.dir") + "/libraries/win/geckodriver.exe";
        String pathToChromeDriverWIN = System.getProperty("user.dir") + "\\libraries\\win\\chromedriver.exe";

        String os = System.getProperty("os.name");
        String driverPath = null;
        if (StringUtils.containsIgnoreCase(os, "mac")) {
            System.setProperty("webdriver.gecko.driver", pathToFirefoxGeckoDriverMAC);
            System.setProperty("webdriver.chrome.driver", pathToChromeDriverMAC);
        } else if (StringUtils.containsIgnoreCase(os, "win")) {
            System.setProperty("webdriver.gecko.driver", pathToFirefoxGeckoDriverWIN);
            System.setProperty("webdriver.chrome.driver", pathToChromeDriverWIN);
        } else {
            System.out.println("OS not covered. Update TestBase!!!!");
            driverPath = null;
        }
    }

    private void setDriverOnCouldServer() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("platform", platform);
        capabilities.setCapability("name", testName);
        String sauseUrl = String.format("http://%s:%s@ondemand.saucelab.com:80/wd/hub", sauseUser, sauseKey);
        driver = new RemoteWebDriver(new URL(sauseUrl), capabilities);

        sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        sauceCient = new SauceREST(sauseUser, sauseKey);
    }

    private void loadLocalDriver() {
        if (browserName.contains("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.contains("chrome")) {
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
    }

    private void loadDriver() throws MalformedURLException {
        if (host.equals("saucelabs")) {
            setDriverOnCouldServer();
        } else if (host.equals("localhost")) {
            setDriverOnLocalServer();
            loadLocalDriver();
        }
    }

    private void unLoadDriver(){
        driver.quit();
    }
}
