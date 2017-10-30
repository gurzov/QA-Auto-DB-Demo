package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class RubberDucksPage extends AbstractPage{

    @FindBy(className = "name")
    private List<WebElement> duckNames;

    @FindBy(className = "price-wrapper")
    private List<WebElement> duckPrices;

    @FindBy(className = "campaign-price")
    private List<WebElement> campaignPrices;

//    public RubberDucksPage() {
//        driver.get("http://localhost/litecart/en/rubber-ducks-c-1/");
//    }

    public List<String> getDuckNames() {
        List<String> duckNamesResult = new ArrayList<>();
        for(WebElement currentDuckName: duckNames) {
            duckNamesResult.add(currentDuckName.getText());
        }
        return duckNamesResult;
    }

    public int getDucksCountOnPage() {
        return getDuckNames().size();
    }

    public List<Float> getDuckPrices() {
        List<Float> duckPricesResult = new ArrayList<>();
        for(WebElement currentDuckPrice: duckPrices) {
            duckPricesResult.add(Float.valueOf(
                    currentDuckPrice.findElement(By.xpath("*[1]")).getText().substring(1)));
        }
        return duckPricesResult;
    }

    public Map<String,Float> getDuckPricesToMap() {
        Map<String,Float> duckPricesResultToMap = new TreeMap<>();
        for(WebElement currentDuckName: duckNames) {
            duckPricesResultToMap.put(
                    currentDuckName.getText(),
                    Float.valueOf(currentDuckName
                            .findElement(By.xpath("//div[@class='price-wrapper']"))
                            .findElement(By.xpath("*[1]")).getText().substring(1)));
        }
        return duckPricesResultToMap;
    }

    public Map<String,Float> getCampaignPriceToMap() {
        ////*[@class='campaign-price']/parent::div/preceding-sibling::div[@class='name']
        Map<String,Float> campaignPricesResultToMap = new TreeMap<>();
        for(WebElement currentDuckPrice: campaignPrices) {
            campaignPricesResultToMap.put(
                    currentDuckPrice.findElement(By.xpath("../preceding-sibling::div[@class='name']")).getText(),
                    Float.valueOf(currentDuckPrice.getText().substring(1)));
        }
        return campaignPricesResultToMap;

    }
}
