package PageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class WhatsYourPurpose {

    public static SelenideElement protectInvestments() {
        return $(By.xpath("//span[text() = 'Защитить активы от инфляции']"));
    }

    public static SelenideElement providePension() {
        return $(By.xpath("//span[text() = 'Обеспечить себе достойную пенсию']"));
    }

    public static SelenideElement childrenCare() {
        return $(By.xpath("//span[text() = 'Позаботиться о будущем детей']"));
    }

    public static SelenideElement speculate() {
        return $(By.xpath("//span[text() = 'Зарабатывать на биржевых спекуляциях']"));
    }

    public static SelenideElement financialReserve() {
        return $(By.xpath("//span[text() = 'Создать финансовый резерв']"));
    }

    public static SelenideElement passiveIncome() {
        return $(By.xpath("//span[text() = 'Обзавестись источником пассивного дохода']"));
    }

    public static SelenideElement bigFinancialInvestment() {
        return $(By.xpath("//span[text() = 'Реализовать крупное финансовое вложение']"));
    }

}
