package qa.lesson11.task19.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "remove_cart_item")
    WebElement removeButton;

    @FindBy(css = "#checkout-cart-wrapper p")
    WebElement info;

    private void waitUntilCartTableRefreshed() {
        WebElement table = driver.findElement(By.cssSelector(".dataTable"));
        wait.until(ExpectedConditions.stalenessOf(table));
    }

    public CartPage removeFromCart() {
        removeButton.click();
        waitUntilCartTableRefreshed();
        return this;
    }

    public CartPage removeAllFromCart() {
        try {
            while (removeButton.isEnabled()) {
                removeFromCart();
            }
        } catch (Exception e) {
            // nothing to remove
        }
        return this;
    }

    public boolean isEmpty() {
        return info.getText().equals("There are no items in your cart.");
    }
}