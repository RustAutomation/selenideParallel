package PageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class WhatDoYouKnow {

    public static SelenideElement almostNothing() {
        return $(By.xpath("//span[text() = 'Почти ничего']"));
    }

    public static SelenideElement scopeVersed() {
        return $(By.xpath("//span[text() = 'Разбираюсь в теме']"));
    }

    public static SelenideElement qualifiedInvestor() {
        return $(By.xpath("//span[text() = 'Я квалифицированный инвестор"));
    }


}
