package qa.lesson6.task12.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class PricesTabPage {

    WebDriver driver;

    public PricesTabPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillIn(String purchasePrice,
                       String currency,
                       String price
                       ) {

        WebElement tabContent = driver.findElement(By.id("tab-prices"));

        WebElement priceElement = tabContent.findElement(By.name("purchase_price"));
        priceElement.clear();
        priceElement.sendKeys(purchasePrice);

        new Select(tabContent.findElement(By.name("purchase_price_currency_code"))).selectByValue(currency);

        priceElement = tabContent.findElement(By.name("prices[USD]"));
        priceElement.clear();
        priceElement.sendKeys(price);

    }

    public void addCampaign(String startDate,
                            String endDate,
                            String percent
                            ) {

        driver.findElement(By.id("add-campaign")).click();

        CampaignsElementPage campaignsElementPage = new CampaignsElementPage(driver);

        campaignsElementPage.fillIn(startDate, endDate, percent);
    }

}
