package PageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public static SelenideElement loginField() {
        return $(By.xpath("//input[@name = 'Логин']"));
    }

    public static SelenideElement passwordField() {
        return $(By.xpath("//input[@name = 'Пароль']"));
    }

    public static SelenideElement submitButton() {
        return $(By.xpath("//button[@type = 'submit']"));
    }

    public static SelenideElement smsInputField() {
    return $(By.xpath("//input[@name='Код из SMS']"));
}


}
