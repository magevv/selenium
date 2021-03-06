package qa.lesson10.task17;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestBase {

    public EventFiringWebDriver driver;

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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new EventFiringWebDriver(new ChromeDriver(options));
        driver.register(new MyListener());
        //driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();
//        System.out.println(((HasCapabilities) driver).getCapabilities());
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void stop() {
        driver.close();
        driver.quit();
    }
}
