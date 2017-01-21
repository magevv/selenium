package qa.lesson11.task19.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "add_cart_product")
    WebElement addToCart;

    @FindBy(name = "options[Size]")
    WebElement size;

    public ProductPage addToCart() {

        try {
            setSize("Small");
        } catch (Exception e) {
            // no size
        }
        addToCart.click();
        waitUntilCartRefreshed();

        return this;
    }

    public void setSize(String value) {
        new Select(size).selectByValue(value);
    }

    private void waitUntilCartRefreshed() {
        WebElement quantity = driver.findElement(By.cssSelector("#cart .quantity"));
        Integer expectedQuantity = Integer.parseInt(quantity.getText()) + 1;
        wait.until(ExpectedConditions.textToBePresentInElement(quantity, expectedQuantity.toString()));
    }
}
