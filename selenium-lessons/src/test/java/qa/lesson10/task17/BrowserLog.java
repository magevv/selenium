package qa.lesson10.task17;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;

import java.util.List;

/*
Сделайте сценарий, который проверяет, не появляются ли в логе браузера сообщения при открытии страниц
в учебном приложении, а именно -- страниц товаров в каталоге в административной панели.

Сценарий должен состоять из следующих частей:

1) зайти в админку
2) открыть каталог, категорию, которая содержит товары
(страница http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1)
3) последовательно открывать страницы товаров и проверять, не появляются ли в логе браузера сообщения (любого уровня)
 */

public class BrowserLog extends TestBase {

    @Test
    public void checkBrowserLog() {
        LogEntries log;
        login();
        driver.navigate().to(baseURL + "/admin/?app=catalog&doc=catalog");

        List<WebElement> rows = driver.findElements(By.cssSelector(".dataTable tr"));

        for (int i = 3; i < rows.size(); i++) {
            driver.findElement(By.cssSelector(".dataTable tr:nth-child(" + i + ") td:nth-child(3) a")).click();

            if(driver.findElement(By.cssSelector("h1")).getText().equals("Catalog")) {
                rows = driver.findElements(By.cssSelector(".dataTable tr"));
                continue;
            }

            driver.navigate().back();
            log = driver.manage().logs().get("browser");
            Assert.assertTrue("Something found in the log!", log.getAll().isEmpty());
        }
    }
}