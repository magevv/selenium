package qa.lesson8.task14;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

/*
 Сделайте сценарий, который проверяет, что ссылки на странице редактирования страны открываются в новом окне.

 Сценарий должен состоять из следующих частей:

 1) зайти в админку
 2) открыть пункт меню Countries (или страницу http://localhost/litecart/admin/?app=countries&doc=countries)
 3) открыть на редактирование какую-нибудь страну или начать создание новой
 4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой -- они ведут на внешние страницы
 и открываются в новом окне, именно это и нужно проверить.

 Конечно, можно просто убедиться в том, что у ссылки есть атрибут target="_blank".
 Но в этом упражнении требуется именно кликнуть по ссылке, чтобы она открылась в новом окне,
 потом переключиться в новое окно, закрыть его, вернуться обратно, и повторить эти действия для всех таких ссылок.

 Не забудьте, что новое окно открывается не мгновенно, поэтому требуется ожидание открытия окна. */


public class Windows extends TestBase {

    private ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.isEmpty() ? null : handles.iterator().next();
            }
        };
    }

    private String getNewWindowHandle(Set<String> oldWindows) {
        Set<String> handles = driver.getWindowHandles();
        handles.removeAll(oldWindows);
        return handles.isEmpty() ? null : handles.iterator().next();
    }

    // С использованием собственной реализации ожидания,
    // которая сразу возвращает handle нового окна (как в примере из лекции)
    @Test
    public void switchWindows1() {

        WebDriverWait wait = new WebDriverWait(driver, 5);

        login();
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> country = driver.findElements(By.cssSelector(".dataTable tr.row"));
        country.get(0).findElement(By.cssSelector("td:nth-child(7) a")).click();

        List<WebElement> links = driver.findElements(By.cssSelector(".fa-external-link"));
        for (WebElement link : links) {
            String mainWindow = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();
            link.click();
            String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

    // С использованием стандартного ожидания
    @Test
    public void switchWindows2() {

        WebDriverWait wait = new WebDriverWait(driver, 5);

        login();
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> country = driver.findElements(By.cssSelector(".dataTable tr.row"));
        country.get(0).findElement(By.cssSelector("td:nth-child(7) a")).click();

        List<WebElement> links = driver.findElements(By.cssSelector(".fa-external-link"));
        for (WebElement link : links) {
            String mainWindow = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();
            link.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(oldWindows.size() + 1));
            driver.switchTo().window(getNewWindowHandle(oldWindows));
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

}
