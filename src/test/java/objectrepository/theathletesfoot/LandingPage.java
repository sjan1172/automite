package objectrepository.theathletesfoot;

import objectrepository.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;

public class LandingPage extends Base {

    private By imgLogo = By.xpath("//img[contains(@src, 'logo.svg')]");
    private By lnkLogin = By.xpath("//a[text()='Login']");
    private By lnkRegister = By.linkText("Register");
    private By lnkContact = By.linkText("Contact");
    private By lnkTrackOrders = By.linkText("Track order");

    private By lblGreetUser = By.xpath("//li[@class=\"greet welcome\"]");

    private By lblCartItemsCount = By.xpath("//div[@class='minicart-wrapper']//span[@class='value']");
    private By iconCart = By.className("minicart-wrapper");

    public LandingPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        String siteUrl = visit("/login");
        assertTrue(getPageUrl().equals(siteUrl)); //TODO
    }

    public Boolean isLogoVisible() {
        return isDisplayed(imgLogo);
    }

    public void clickLogo() {
        click(imgLogo);
    }

    public void clickLoginMenu() {
        waitForClickable(lnkLogin);
        click(lnkLogin);
    }
}
