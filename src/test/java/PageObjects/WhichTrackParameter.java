package PageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class WhichTrackParameter {

    public static SelenideElement notReadyToAnswer() {
        return $(By.xpath("//span[text() = 'Не готов ответить']"));
    }

    public static SelenideElement notReadyToAnalyze() {
        return $(By.xpath("//span[text() = 'Не готов тратить время на анализ. Предпочитаю доверить эту работу профессионалу']"));
    }

    public static SelenideElement needAnalytics() {
        return $(By.xpath("//span[text() = 'Решения принимаю сам, но мне потребуется аналитика, поддержка и свежие инвестиционные идеи']"));
    }

    public static SelenideElement allDecisionsMadeByMyself() {
        return $(By.xpath("//span[text() = 'Все решения я принимаю лично, включая и то, когда и какие ценные бумаги покупать']"));
    }

    public static SelenideElement finish() {
        return $(By.xpath("//button[@value = 'Завершить']"));
    }

    public static SelenideElement recommendationsTitle() {
        return $(By.xpath("//span[text() = 'Рекомендуемый портфель']"));
    }

}
