package objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoading extends Base {

    By btnStart = By.cssSelector("#start button");
    By finishText = By.id("finish");

    public DynamicLoading(WebDriver driver) {
        super(driver);
    }

    public void loadExample(String exampleNumber){
        visit("/dynamic_loading/" + exampleNumber);
        click(btnStart);
    }

    public Boolean isFinishTextPresent(){
        return waitForElementDisplayed(finishText, 10);
    }




}
