package qa.lesson11.task19.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "cart")
    WebElement cart;

    public ProductPage openMostPopularProduct() {
        driver.findElements(By.cssSelector("#box-most-popular li")).get(0).click();
        return new ProductPage(driver);
    }

    public HomePage addToCart(int n) {
        for (int i = 0; i < n; i++) {
            openMostPopularProduct().addToCart().goToPreviousPage();
        }
        return this;
    }

    public CartPage openCart() {
        cart.click();
        return new CartPage(driver);
    }
}