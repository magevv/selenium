package qa.lesson2.task1;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SimpleTest {

    @Test
    public void NavigateToSite() throws MalformedURLException {
        //WebDriver driver = new ChromeDriver();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("internet explorer");
        caps.setPlatform(Platform.WINDOWS);
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
        //WebDriver driver2 = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
        driver.navigate().to("http://google.com");
        driver.close();
        driver.quit();
    }

}