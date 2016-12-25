package qa.lesson5.task10.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {

    WebDriver driver;

    public AbstractPage() {}

    public String getColor(WebElement element) {
        return element.getCssValue("color");
    }

    public String getFontSize(WebElement element) {
        return element.getCssValue("font-size");
    }

    public String getTag(WebElement element) {
        return element.getAttribute("localName");
    }

}