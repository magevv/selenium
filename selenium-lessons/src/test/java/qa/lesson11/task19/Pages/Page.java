package qa.lesson11.task19.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    WebDriver driver;
    WebDriverWait wait;

    Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    public void goToPreviousPage() {
        driver.navigate().back();
    }

}
