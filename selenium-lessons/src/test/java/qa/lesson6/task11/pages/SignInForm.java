package qa.lesson6.task11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.UUID;

public class SignInForm {

    WebDriver driver;
    WebElement form;

    public SignInForm(WebDriver driver) {
        this.driver = driver;
        form = driver.findElement(By.name("customer_form"));
    }

    public ArrayList<String> fillIn(String firstname,
                                             String lastname,
                                             String address1,
                                             String postcode,
                                             String city,
                                             String country,
                                             String phone,
                                             Boolean subscribe,
                                             String password) {

        form.findElement(By.name("firstname")).sendKeys(firstname);
        form.findElement(By.name("lastname")).sendKeys(lastname);
        form.findElement(By.name("address1")).sendKeys(address1);
        form.findElement(By.name("postcode")).sendKeys(postcode);
        form.findElement(By.name("city")).sendKeys(city);
        (new Select(form.findElement(By.name("country_code")))).selectByVisibleText(country);

        String email = UUID.randomUUID().toString() + "@email.com";

        form.findElement(By.name("email")).sendKeys(email);
        form.findElement(By.name("phone")).sendKeys(phone);

        WebElement checkbox = form.findElement(By.name("newsletter"));

        if ((!subscribe && isChecked(checkbox)) ||
                (subscribe && !isChecked(checkbox))) {
            checkbox.click();
        }

        form.findElement(By.name("newsletter")).click();

        form.findElement(By.name("password")).sendKeys(password);
        form.findElement(By.name("confirmed_password")).sendKeys(password);

        ArrayList<String> credentials = new ArrayList<>();
        credentials.add(email);
        credentials.add(password);

        return credentials;
    }

    // In case you want to specify email explicitly
    public ArrayList<String> fillIn(String firstname,
                                             String lastname,
                                             String address1,
                                             String postcode,
                                             String city,
                                             String country,
                                             String phone,
                                             Boolean newsletter,
                                             String email,
                                             String password) {
        fillIn(firstname, lastname, address1, postcode, city, country, phone, newsletter, password);
        form.findElement(By.name("email")).clear();
        form.findElement(By.name("email")).sendKeys(email);

        ArrayList<String> credentials = new ArrayList<>();
        credentials.add(email);
        credentials.add(password);

        return credentials;
    }

    public void submit() {
        form.findElement(By.name("create_account")).click();
    }

    private Boolean isChecked(WebElement checkbox) {

        if (checkbox.getAttribute("checked").equals("checked")) {
            return true;
        }
        return false;
    }

}
