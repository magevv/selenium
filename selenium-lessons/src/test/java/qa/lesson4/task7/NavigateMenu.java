package qa.lesson4.task7;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*
Сделайте сценарий, который выполняет следующие действия в учебном приложении litecart.

1) входит в панель администратора http://localhost/litecart/admin
2) прокликивает последовательно все пункты меню слева, включая вложенные пункты
3) для каждой страницы проверяет наличие заголовка
 */

public class NavigateMenu {

    WebDriver driver;

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        //driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        login();
    }

    @After
    public void stop() {
        driver.close();
        driver.quit();
        driver = null;
    }

    public void login() {
        driver.navigate().to("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    private void rotateAndClick(String selector) {
        List<WebElement> elements;

        elements = driver.findElements(By.cssSelector(selector));
        int counter = 1;
        for (WebElement element : elements) {
            driver.findElement(By.cssSelector(selector + ":nth-child(" + counter + ")")).click();

            Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());

            counter++;
            rotateAndClick(selector + " ul > li");
        }
    }

    @Test
    public void navigate() {
        rotateAndClick("#sidebar #box-apps-menu > li#app-");
    }
}