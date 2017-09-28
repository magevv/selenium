package qa.applitool;

import com.applitools.eyes.Eyes;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppliTool {

    final static String KEY = "wNgyNcD1RbJznJYrcsodxKYN1afTgJTLnnioFx9M0RM110";
    String appName = "TEST";
    String testName = "TEST";

    @Test
    public void NavigateToSite() {
        WebDriver driver = new ChromeDriver();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        caps.setPlatform(Platform.WINDOWS);

        Eyes eyes = new Eyes();
        eyes.setApiKey(KEY);
        eyes.setForceFullPageScreenshot(true);

        try{
            driver = eyes.open(driver, appName, testName);
            driver.navigate().to("http://itc.ua");
            //eyes.checkRegion(By.cssSelector("html"), true);
            eyes.checkWindow();
            eyes.close();

        } finally {
            eyes.abortIfNotClosed();
            driver.close();
            driver.quit();
        }




    }

}