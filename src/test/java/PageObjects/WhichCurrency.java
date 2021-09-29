package PageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class WhichCurrency {

    public static SelenideElement currencyRubles() {
        return $(By.xpath("//div[text() = '₽']"));
    }

    public static SelenideElement currencyUsd() {
        return $(By.xpath("//div[text() = '$']"));
    }

    public static SelenideElement amountInput() {
        return $(By.xpath("//input[contains(@class, 'PureInput_input')]"));
    }

    public static SelenideElement continueButton() {
        return $(By.xpath("//div[text() = 'Продолжить']"));
    }

}
