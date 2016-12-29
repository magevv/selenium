package qa.lesson6.task12;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import qa.lesson6.task11.TestBase;

import java.util.UUID;

/*
Сделайте сценарий для добавления нового товара (продукта) в учебном приложении litecart (в админке).

Для добавления товара нужно открыть меню Catalog, в правом верхнем углу нажать кнопку "Add New Product",
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


    }
}

