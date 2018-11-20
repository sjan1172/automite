package objectrepository;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
    private WebDriver driver;

    public Base(WebDriver driver){
        this.driver = driver;
    }

    public void visit(String url){
        driver.get(url);
    }

    public WebElement find(By locator){
        return driver.findElement(locator);
    }

    public void click(By locator){
        find(locator).click();
    }

    public void type(String inputText, By locator){
        find(locator).clear();
        find(locator).sendKeys(inputText);
    }

    public void submit(By locator){
        find(locator).submit();
    }

    public Boolean isDisplayed(By locator){
        try{
            return find(locator).isDisplayed();
        }catch(NoSuchElementException ex){
            return false;
        }
    }

    public Boolean waitForElementDisplayed(By locator, Integer... timeout) {
        try{
            waitFor(ExpectedConditions.visibilityOfElementLocated(locator), (timeout.length > 0 ? timeout[0]: null));
            return find(locator).isDisplayed();
        }catch(TimeoutException ex){
            return false;
        }
    }

    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout){
        timeout = timeout != null ? timeout : 5;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }
}
