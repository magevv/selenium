package qa.lesson1.task1;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleTest {

    @Test
    public void NavigateToSite() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://google.com");
        driver.close();
    }

}