package objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicLoading extends Base {

    By btnStart = By.cssSelector("#start button");
    By finishText = By.id("finish");

    public DynamicLoading(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void loadExample(String exampleNumber){
        visit("/dynamic_loading/" + exampleNumber);
        click(btnStart);
    }

    public Boolean isFinishTextPresent(){
        return waitForElementDisplayed(finishText, 10);
    }




}
