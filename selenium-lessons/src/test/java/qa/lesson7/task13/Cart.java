package qa.lesson7.task13;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/*
 Сделайте сценарий для добавления товаров в корзину и удаления товаров из корзины.

 Сценарий должен состоять из следующих частей:

 1) открыть страницу какого-нибудь товара
 2) добавить его в корзину
 3) подождать, пока счётчик товаров в корзине обновится
 4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности
 в корзине было 3 единицы товара

 5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
 6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица */

public class Cart extends TestBase {

    private void waitUntilCartRefreshed() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement quantity = driver.findElement(By.cssSelector("#cart .quantity"));
        Integer expectedQuantity = Integer.parseInt(quantity.getText()) + 1;
        wait.until(ExpectedConditions.textToBePresentInElement(quantity, expectedQuantity.toString()));
    }

    private void waitUntilCartTableRefreshed() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement table = driver.findElement(By.cssSelector(".dataTable"));
        wait.until(ExpectedConditions.stalenessOf(table));
    }

    public void addToCart(int n) {
        for (int i = 0; i < n; i++) {
            openMainPage();
            driver.findElements(By.cssSelector("#box-most-popular li")).get(0).click();

            // This try-catch block handles products with mandatory option "size".
            try {
                new Select(driver.findElement(By.name("options[Size]"))).selectByValue("Small");
            } catch (Exception e) {
            }
            driver.findElement(By.name("add_cart_product")).click();
            waitUntilCartRefreshed();
        }
    }

    public void removeAllFromCart() {
        try {
            while (driver.findElement(By.name("remove_cart_item")).isEnabled()) {
                driver.findElement(By.name("remove_cart_item")).click();
                waitUntilCartTableRefreshed();
            }
        } catch (Exception e) {
        }
    }

    @Test
    public void testCart() {

        addToCart(3);
        driver.findElement(By.id("cart")).click();
        removeAllFromCart();
        WebElement emptyCart = driver.findElement(By.cssSelector("#checkout-cart-wrapper p"));
        Assert.assertTrue(emptyCart.getText().equals("There are no items in your cart."));

    }
}
