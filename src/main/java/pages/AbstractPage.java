package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;

public abstract class AbstractPage {

    WebDriver driver = DriverFactory.getDriver();

    AbstractPage() {
        PageFactory.initElements(driver, this);
    }

    public void selectOptionByName(String controlName, String optionName) {
        driver.findElement(By.name(controlName)).click();
        driver.findElement(By.xpath("//option[text()='" + optionName +"']")).click();
    }

    public void switchToFrame(String frameId) {
        driver.switchTo().frame(frameId);
        //driver.switchTo().defaultContent();
    }
}

