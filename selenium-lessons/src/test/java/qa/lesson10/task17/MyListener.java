package qa.lesson10.task17;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;

public class MyListener extends AbstractWebDriverEventListener {

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println(by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println(by + " found");
        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File screenshot = new File("screen" + System.currentTimeMillis() + ".png");
            Files.copy(tempFile, screenshot);
            System.out.println(screenshot.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        System.out.println(throwable);
        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File screenshot = new File("screen.png");
            Files.copy(tempFile, screenshot);
            System.out.println(screenshot.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
