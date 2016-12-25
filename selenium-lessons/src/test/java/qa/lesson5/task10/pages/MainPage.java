package qa.lesson5.task10.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Main Page
 */
public class MainPage extends AbstractPage {

    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getFirstProductInCampaigns() {
        return driver.findElement(By.cssSelector("#box-campaigns li.product"));
    }

    public String getProductURL(WebElement product) {
        return product.findElement(By.cssSelector("a.link")).getAttribute("href");
    }

    public String getProductName(WebElement product) {
        return product.findElement(By.cssSelector(".name")).getAttribute("outerText");
    }

    public WebElement getProductRegularPriceElement(WebElement product) {
        return product.findElement(By.cssSelector(".regular-price"));
    }

    public String getProductRegularPrice(WebElement product) {
        return getProductRegularPriceElement(product).getAttribute("outerText");
    }

    public WebElement getProductCampaignPriceElement(WebElement product) {
        return product.findElement(By.cssSelector(".campaign-price"));
    }

    public String getProductCampaignPrice(WebElement product) {
        return getProductCampaignPriceElement(product).getAttribute("outerText");
    }
}