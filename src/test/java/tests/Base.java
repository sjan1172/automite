package tests;

import com.saucelabs.saucerest.SauceREST;
import net.sf.randomjunit.RandomTestRunner;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;

//@RunWith(RandomTestRunner.class) //runs parallel execution on random order
public class Base implements Config{
    protected WebDriver driver;
    private String testName;
    private String sessionId;
    private SauceREST sauceCient;

    @Rule
    public ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            if(host.equals("saucelabs")){
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", browserName);
                capabilities.setCapability("version", browserVersion);
                capabilities.setCapability("platform", platform);
                capabilities.setCapability("name", testName);
                String sauseUrl = String.format("http://%s:%s@ondemand.saucelab.com:80/wd/hub", sauseUser, sauseKey);
                driver = new RemoteWebDriver(new URL(sauseUrl), capabilities);

                sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
                sauceCient = new SauceREST(sauseUser,  sauseKey);

            }
            else if(host.equals("localhost")){
                if(browserName.equals("firefox")){
                    driver = new FirefoxDriver();
                }
                else if(browserName.equals("chrome")){
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
                    driver = new ChromeDriver();
                }
                else if(browserName.equals("safari")){
                    //To make safari work, on safari browser > go to develop > Allow Remote Automation option
                    driver = new SafariDriver();
                }
            }
        }

        @Override
        protected void after() {
            driver.quit();
        }
    };

    @Rule
    public TestRule watcher = new TestWatcher(){
        protected void starting(Description description){
            testName = description.getDisplayName();
        }

        protected void failed(Throwable throwable, Description description){
            if(host.equals("saucelabs")){
                sauceCient.jobFailed(sessionId);
                System.out.println(String.format("http://saucelabs.com/tests/%s", sessionId));

            }
        }

        protected void succeeded(Description description) {
            if (host.equals("saucelabs")) {
                sauceCient.jobPassed(sessionId);
            }
        }
    };

}
