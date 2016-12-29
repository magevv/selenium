package qa.lesson6.task11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginForm {

    WebDriver driver;

    public LoginForm(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    public void clickNewUserLink() {
        driver.findElement(By.linkText("New customers click here")).click();
    }
}
