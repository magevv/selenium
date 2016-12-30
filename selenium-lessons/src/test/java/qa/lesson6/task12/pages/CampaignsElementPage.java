package qa.lesson6.task12.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.rmi.CORBA.Util;


public class CampaignsElementPage {

    WebDriver driver;

    public CampaignsElementPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillIn(String startDate,
                       String endDate,
                       String percent
                       ) {

        WebElement campaignsTable = driver.findElement(By.id("table-campaigns"));

        campaignsTable.findElement(By.name("campaigns[new_1][start_date]")).sendKeys(startDate);
        campaignsTable.findElement(By.name("campaigns[new_1][end_date]")).sendKeys(endDate);
        WebElement percentElement = campaignsTable.findElement(By.name("campaigns[new_1][percentage]"));
        Utils.clearAndSet(percentElement, percent);
    }

    public void remove() {
        //code
    }

}
