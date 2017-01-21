package qa.lesson11.task19.General;

import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import qa.lesson11.task19.Pages.HomePage;

public class Application {

    WebDriver driver;

    public String baseURL = "http://localhost/litecart";

    public Application() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        System.out.println(((HasCapabilities) driver).getCapabilities());
    }

    public HomePage openHomePage() {
        driver.navigate().to(baseURL + "/en/");
        return new HomePage(driver);
    }

    public void quit() {
        driver.quit();
    }

}
