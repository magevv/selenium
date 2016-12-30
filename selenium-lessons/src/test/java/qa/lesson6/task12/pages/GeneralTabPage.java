package qa.lesson6.task12.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;


public class GeneralTabPage {

    WebDriver driver;

    public GeneralTabPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillIn(int status,
                       String name,
                       String code,
                       String category,
                       int gender,
                       String quantity,
                       String quantityUnit,
                       String deliveryStatus,
                       String soldOutStatus,
                       String filepath) {

        WebElement tabContent = driver.findElement(By.id("tab-general"));

        tabContent.findElements(By.name("status")).get(status).click();

        tabContent.findElement(By.name("name[en]")).sendKeys(name);
        tabContent.findElement(By.name("code")).sendKeys(code);
        tabContent.findElement(By.cssSelector("input[data-name='" + category + "'")).click();
        tabContent.findElements(By.name("product_groups[]")).get(gender).click();

        WebElement quantityElement = tabContent.findElement(By.name("quantity"));
        Utils.clearAndSet(quantityElement, quantity);

        new Select(tabContent.findElement(By.name("quantity_unit_id"))).selectByVisibleText(quantityUnit);
        new Select(tabContent.findElement(By.name("delivery_status_id"))).selectByVisibleText(deliveryStatus);
        new Select(tabContent.findElement(By.name("sold_out_status_id"))).selectByVisibleText(soldOutStatus);

        File file = new File(filepath);
        tabContent.findElement(By.name("new_images[]")).sendKeys(file.getAbsolutePath());

        tabContent.findElement(By.name("date_valid_from")).sendKeys("12082017");
        tabContent.findElement(By.name("date_valid_to")).sendKeys("01012020");

    }

}
