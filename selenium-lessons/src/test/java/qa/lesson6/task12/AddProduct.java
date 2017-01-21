package qa.lesson6.task12;

import org.junit.Test;
import org.openqa.selenium.By;
import qa.lesson6.task12.pages.*;


/*
Сделайте сценарий для добавления нового товара (продукта) в учебном приложении litecart (в админке).

Для добавления товара нужно открыть меню Catalog, в правом верхнем углу нажать кнопку "Add New ProductPage",
заполнить поля с информацией о товаре и сохранить.

Достаточно заполнить только информацию на вкладках General, Information и Prices.
Скидки (Campains) на вкладке Prices можно не добавлять.

После сохранения товара нужно убедиться, что он появился в каталоге (в админке).
Клиентскую часть магазина можно не проверять.
*/

public class AddProduct extends TestBase {

    @Test
    public void signIn() {

        login();

        driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[2]")).click();

        driver.findElement(By.partialLinkText("Add New ProductPage")).click();

        Tabs tabs = new Tabs(driver);

        //Switch to General
        tabs.clickTab(1);

        GeneralTabPage generalTabPage = new GeneralTabPage(driver);
        generalTabPage.fillIn(
                0,
                "Duffy Duck",
                "12345",
                "Rubber Ducks",
                0,
                "10",
                "kg",
                "3-5 days",
                "Sold out",
                "files/daffyduck.png"
        );

        //Switch to Information
        tabs.clickTab(2);

        InfoTabPage infoTabPage = new InfoTabPage(driver);
        infoTabPage.fillIn(
                "ACME Corp.",
                "daffy, duck",
                "Daffy Duck is an animated cartoon character produced by Warner Bros.",
                "Styled as an anthropomorphic black duck, the character has appeared in cartoon series such as Looney Tunes and Merrie Melodies, where he usually has been depicted as the best friend and occasional arch-rival of Bugs Bunny. Daffy was one of the first of the new \"screwball\" characters that emerged in the late 1930s to replace traditional everyman characters who were more popular earlier in the decade,[citation needed] such as Mickey Mouse and Popeye. Daffy starred in 130 shorts in the golden age, making him the third-most frequent character in the Looney Tunes/Merrie Melodies cartoons, behind Bugs Bunny's 180 appearances and Porky Pig's 162 appearances.",
                "Daffy Duck");

        // Switch to Prices
        tabs.clickTab(4);

        PricesTabPage pricesTabPage = new PricesTabPage(driver);
        pricesTabPage.fillIn("100.56", "USD", "150.99");

        // There is a bug for Campaign prices. Dates are not saved (even manually).
        pricesTabPage.addCampaign("020200201711111", "231200201810101", "5");
        Buttons buttons = new Buttons(driver);
        buttons.save();

    }
}

