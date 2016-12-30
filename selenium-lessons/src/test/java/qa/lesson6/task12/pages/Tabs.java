package qa.lesson6.task12.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Tabs {

    WebDriver driver;

    public Tabs(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTab(int tabNumber) {
        driver.findElement(By.cssSelector(".tabs li:nth-child(" + tabNumber + ")")).click();
    }
}