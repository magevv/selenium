package qa.lesson5.task9;

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

import java.util.*;
import java.util.concurrent.TimeUnit;

/*
Сделайте сценарии, которые проверяют сортировку стран и геозон (штатов) в учебном приложении litecart.

1) на странице http://localhost/litecart/admin/?app=countries&doc=countries
а) проверить, что страны расположены в алфавитном порядке
б) для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и там проверить,
что зоны расположены в алфавитном порядке

2) на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке
 */

public class CheckCountriesSorting {


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

    /**
    Method tests if list of strings passed as an argument is alphabetically sorted.
     */
    public boolean isSorted(List<String> s) {
        List<String> sorted = new ArrayList<>();
        sorted.addAll(s);
        Collections.sort(sorted);
        return sorted.equals(s);
    }

    @Test
    public void testSortingCountries() {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        WebElement form = driver.findElement(By.name("countries_form"));
        List<WebElement> list = form.findElements(By.cssSelector(".dataTable tr.row > td:nth-child(5)"));
        List<String> countries = new ArrayList<>();
        for (WebElement element : list) {
            countries.add(element.getText());
        }
        Assert.assertTrue(isSorted(countries));
    }

    @Test
    public void testSortingGeozones1() {

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<WebElement> tableCountries = driver.findElements(By.cssSelector(".dataTable tr"));

        int sizeTableCountries = tableCountries.size();

        for (int i = 2; i < sizeTableCountries; i++) {

            WebElement row = driver.findElement(By.cssSelector(".dataTable tr:nth-child(" + i + ")"));
            String geoNum = row.findElement(By.cssSelector("td:nth-child(6)")).getAttribute("textContent");

            System.out.print(row.findElement(By.cssSelector("td:nth-child(5)")).getAttribute("textContent"));

            if (!geoNum.equals("0")) {

                row.findElement(By.cssSelector("td:nth-child(5) a")).click();
                List<WebElement> list = driver.findElements(By.cssSelector("#table-zones tr > td:nth-child(3)"));
                LinkedList<String> zones = new LinkedList<>();

                for (WebElement element : list) {
                    zones.add(element.getText());
                }

                zones.removeLast();

                System.out.print(" (" + zones.size() + ") : ");
                for (String zone : zones) {
                    System.out.print(zone + ", ");
                }

                Assert.assertTrue(isSorted(zones));
                driver.navigate().back();
            }

            System.out.println();
        }
    }

    @Test
    public void testSortingGeozones2() {

        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> tableCountries = driver.findElements(By.cssSelector(".dataTable tr"));

        int sizeTableCountries = tableCountries.size();

        for (int i = 2; i < sizeTableCountries; i++) {

            WebElement row = driver.findElement(By.cssSelector(".dataTable tr:nth-child(" + i + ")"));

            System.out.print(row.findElement(By.cssSelector("td:nth-child(3)")).getAttribute("textContent"));

            row.findElement(By.cssSelector("td:nth-child(3) a")).click();

            List<WebElement> list = driver.findElements(By.cssSelector("#table-zones tr > td:nth-child(3) option:checked"));
            LinkedList<String> zones = new LinkedList<>();

            for (WebElement element : list) {
                zones.add(element.getText());
            }

            System.out.print(" (" + zones.size() + ") : ");
            for (String zone : zones) {
                System.out.print(zone + ", ");
            }

            Assert.assertTrue(isSorted(zones));
            driver.navigate().back();


            System.out.println();
        }
    }
}