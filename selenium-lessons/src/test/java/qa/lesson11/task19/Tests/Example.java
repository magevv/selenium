package qa.lesson11.task19.Tests;

import org.junit.Assert;
import org.junit.Test;
import qa.lesson11.task19.Pages.CartPage;

public class Example extends TestBase {

    @Test
    public void testCart() {
        CartPage cart = app.openHomePage().addToCart(3).openCart().removeAllFromCart();
        Assert.assertTrue(cart.isEmpty());
    }
}