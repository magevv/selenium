package qa.lesson6.task12.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;


public class InfoTabPage {

    WebDriver driver;

    public InfoTabPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillIn(String manufacturer,
                       String keywords,
                       String shortDescr,
                       String descr,
                       String headTitle
                       ) {

        WebElement tabContent = driver.findElement(By.id("tab-information"));

        new Select(tabContent.findElement(By.name("manufacturer_id"))).selectByVisibleText(manufacturer);
        tabContent.findElement(By.name("keywords")).sendKeys(keywords);

        tabContent.findElement(By.name("short_description[en]")).sendKeys(shortDescr);

        // This is a cool way to paste large text using clipboard
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = new StringSelection(descr);
        clipboard.setContents(transferable,null);

        tabContent.findElement(By.name("description[en]")).sendKeys(Keys.CONTROL, "v");

        tabContent.findElement(By.name("head_title[en]")).sendKeys(headTitle);

        tabContent.findElement(By.name("meta_description[en]")).sendKeys(shortDescr);


    }

}
