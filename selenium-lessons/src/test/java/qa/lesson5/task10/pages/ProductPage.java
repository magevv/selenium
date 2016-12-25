package qa.lesson5.task10.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 Product Page
 */
public class ProductPage extends AbstractPage {

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductURL() {
        return driver.getCurrentUrl();
    }

    public String getProductName() {
        return driver.findElement(By.cssSelector("#box-product h1.title")).getAttribute("outerText");
    }

    public WebElement getProductRegularPriceElement() {
        return driver.findElement(By.cssSelector("#box-product .regular-price"));
    }

    public String getProductRegularPrice() {
        return driver.findElement(By.cssSelector("#box-product .regular-price")).getAttribute("outerText");
    }

    public WebElement getProductCampaignPriceElement() {
        return driver.findElement(By.cssSelector("#box-product .campaign-price"));
    }

    public String getProductCampaignPrice() {
        return driver.findElement(By.cssSelector("#box-product .campaign-price")).getAttribute("outerText");
    }

}