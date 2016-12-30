package qa.lesson6.task12.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Buttons {

    WebDriver driver;

    public Buttons(WebDriver driver) {
        this.driver = driver;
    }


    public void save() {
        driver.findElement(By.name("save")).click();
    }

    public void cancel() {
        driver.findElement(By.name("cancel")).click();
    }

    public void delete() {
        driver.findElement(By.name("delete")).click();
    }

}
