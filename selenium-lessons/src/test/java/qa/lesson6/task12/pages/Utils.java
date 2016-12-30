package qa.lesson6.task12.pages;


import org.openqa.selenium.WebElement;

public class Utils {

    public static void clearAndSet(WebElement element, String content) {
        element.clear();
        element.sendKeys(content);
    }
}
