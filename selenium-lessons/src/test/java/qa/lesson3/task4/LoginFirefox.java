package qa.lesson3.task4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class LoginFirefox {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() {

       /* DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE, true);

        driver = new FirefoxDriver(
                new FirefoxBinary(new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe")),
                new FirefoxProfile(), caps);


        System.out.println(((HasCapabilities) driver).getCapabilities());

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 5);*/
    }

    @After
    public void stop() {
        driver.close();
        driver.quit();
        driver = null;
    }

    @Test
    public void login() {
        driver.navigate().to("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}