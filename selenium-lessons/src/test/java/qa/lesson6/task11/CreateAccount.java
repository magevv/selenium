package qa.lesson6.task11;

import org.junit.Test;
import org.openqa.selenium.By;
import qa.lesson6.task11.pages.LoginForm;
import qa.lesson6.task11.pages.SignInForm;

import java.util.ArrayList;

/*
Сделайте сценарий для регистрации нового пользователя в учебном приложении litecart
(не в админке, а в клиентской части магазина).

Сценарий должен состоять из следующих частей:

1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты
(чтобы не конфликтовало с ранее созданными пользователями),
2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
3) повторный вход в только что созданную учётную запись,
4) и ещё раз выход.*/

public class CreateAccount extends TestBase {

    @Test
    public void signIn() {

        openMainPage();

        LoginForm loginForm = new LoginForm(driver);

        loginForm.clickNewUserLink();

        SignInForm signInForm = new SignInForm(driver);

        ArrayList<String> credentials = signInForm.fillIn("Jane", "Doe",
                "Amosova str, 12a", "1111", "Odense",
                "Denmark", "+3555555555", false, "12345");

        signInForm.submit();

        driver.findElement(By.linkText("Logout")).click();

        loginForm.login(credentials.get(0), credentials.get(1));

        driver.findElement(By.linkText("Logout")).click();
    }
}

