package qa.lesson3.task4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginIE {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() {


        driver = new InternetExplorerDriver();

        System.out.println(((HasCapabilities) driver).getCapabilities());

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 5);
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
        //wait.withTimeout(5, TimeUnit.SECONDS);
        driver.findElement(By.name("login")).click();

    }
}