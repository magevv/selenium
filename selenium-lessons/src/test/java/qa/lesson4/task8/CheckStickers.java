package qa.lesson4.task8;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckStickers {

    WebDriver driver;

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void stop() {
        driver.close();
        driver.quit();
        driver = null;
    }

    @Test
    public void checkStickers() {
        driver.navigate().to("http://localhost/litecart/en/");
        List<WebElement> products = driver.findElements(By.className("product"));
        for (WebElement product : products) {
            Assert.assertTrue(product.findElements(By.className("sticker")).size() == 1);
        }
    }
}