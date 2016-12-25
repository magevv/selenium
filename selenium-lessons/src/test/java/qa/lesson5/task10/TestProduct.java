package qa.lesson5.task10;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import qa.lesson5.task10.pages.MainPage;
import qa.lesson5.task10.pages.ProductPage;

/*
Сделайте сценарий, который проверяет, что при клике на товар открывается правильная страница товара
в учебном приложении litecart.

1) Открыть главную страницу
2) Кликнуть по первому товару в категории Campaigns
3) Проверить, что открывается страница правильного товара

Более точно, проверить, что
а) совпадает текст названия товара
б) совпадает цена (обе цены)

Кроме того, проверить стили цены на главной странице и на странице товара -- первая цена серая,
зачёркнутая, маленькая, вторая цена красная жирная, крупная.
 */

public class TestProduct extends TestBase {

    @Test
    public void testProductData() {

        // Initialize page objects
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);

        openMainPage();

        mainPage.getFirstProductInCampaigns().click();

        // Test Product Data
        Assert.assertTrue(productPage.getProductURL().equals(baseURL + "/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1"));
        Assert.assertTrue(productPage.getProductName().equals("Yellow Duck"));
        Assert.assertTrue(productPage.getProductRegularPrice().equals("$20"));
        Assert.assertTrue(productPage.getProductCampaignPrice().equals("$18"));

    }

    @Test
    public void testProductStylesOnProductPage() {

        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);

        openMainPage();

        mainPage.getFirstProductInCampaigns().click();

        // Test Regular Price Style
        WebElement regularPrice = productPage.getProductRegularPriceElement();

        String regularPriceColor = productPage.getColor(regularPrice);
        String regularPriceFontSize = productPage.getFontSize(regularPrice);
        String regularPriceTag = productPage.getTag(regularPrice);

        Assert.assertTrue(regularPriceColor.equals("rgba(102, 102, 102, 1)"));
        Assert.assertTrue(regularPriceFontSize.equals("16px"));
        Assert.assertTrue(regularPriceTag.equals("s"));


        //Test Campaign Price Style
        WebElement campaignPrice = productPage.getProductCampaignPriceElement();

        String campaignPriceColor = productPage.getColor(campaignPrice);
        String campaignPriceFontSize = productPage.getFontSize(campaignPrice);
        String campaignPricePriceTag = productPage.getTag(campaignPrice);

        Assert.assertTrue(campaignPriceColor.equals("rgba(204, 0, 0, 1)"));
        Assert.assertTrue(campaignPriceFontSize.equals("22px"));
        Assert.assertTrue(campaignPricePriceTag.equals("strong"));
    }

    @Test
    public void testProductStylesOnMainPage() {

        MainPage mainPage = new MainPage(driver);

        openMainPage();

        WebElement product = mainPage.getFirstProductInCampaigns();

        // Test Regular Price Style
        WebElement regularPrice = mainPage.getProductRegularPriceElement(product);

        String regularPriceColor = mainPage.getColor(regularPrice);
        String regularPriceFontSize = mainPage.getFontSize(regularPrice);
        String regularPriceTag = mainPage.getTag(regularPrice);

        Assert.assertTrue(regularPriceColor.equals("rgba(119, 119, 119, 1)"));
        Assert.assertTrue(regularPriceFontSize.equals("14.4px"));
        Assert.assertTrue(regularPriceTag.equals("s"));


        //Test Campaign Price Style
        WebElement campaignPrice = mainPage.getProductCampaignPriceElement(product);

        String campaignPriceColor = mainPage.getColor(campaignPrice);
        String campaignPriceFontSize = mainPage.getFontSize(campaignPrice);
        String campaignPricePriceTag = mainPage.getTag(campaignPrice);

        Assert.assertTrue(campaignPriceColor.equals("rgba(204, 0, 0, 1)"));
        Assert.assertTrue(campaignPriceFontSize.equals("18px"));
        Assert.assertTrue(campaignPricePriceTag.equals("strong"));
    }
}