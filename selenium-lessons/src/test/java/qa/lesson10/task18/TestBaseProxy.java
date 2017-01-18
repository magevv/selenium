package qa.lesson10.task18;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestBaseProxy {

    public WebDriver driver;

    public String baseURL = "http://localhost/litecart";

    public void login() {
        driver.navigate().to(baseURL + "/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    public void openMainPage() {
        driver.navigate().to(baseURL + "/en/");
    }

    /**
     * Method tests if list of strings passed as an argument is alphabetically sorted.
     */
    public boolean isSorted(List<String> s) {
        List<String> sorted = new ArrayList<>();
        sorted.addAll(s);
        Collections.sort(sorted);
        return sorted.equals(s);
    }

    @Before
    public void start() {
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("localhost:8888");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("proxy", proxy);

        driver = new ChromeDriver(caps);
        System.out.println(((HasCapabilities) driver).getCapabilities());
    }

    @After
    public void stop() {
        driver.close();
        driver.quit();
    }
}
