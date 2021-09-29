package PageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    public static SelenideElement titleLogo() {
        return $(By.xpath("//div[@title='Открытие. Инвестиции']"));
    }

    public static SelenideElement pageTitle() {
        return $(By.xpath("//div[contains(@class, 'header__title')]"));
    }

    public static SelenideElement periodEndDate() {
        return $(By.xpath("//div[@class ='b-sum__main b-results-per-period__date']/span[2]"));
    }

    public static ElementsCollection cards() {
        return $$(By.xpath("//div[@class='slick-track']/div"));
    }

    public static ElementsCollection topUp() {
        return $$(By.xpath("//button[text()='Пополнить']"));
    }

    public static ElementsCollection checkOut() {
        return $$(By.xpath("//button[text()='Узнать']"));
    }

    public static ElementsCollection refresh() {
        return $$(By.xpath("//button[text()='Обновить']"));
    }

    public static ElementsCollection ideaEnable() {
        return $$(By.xpath("//button[text()='Исполнить идею']"));
    }

    public static ElementsCollection instruments() {
        return $$(By.xpath("//div[contains(@class, 'b-instruments-data-res__head-row')]"));
    }

    public static ElementsCollection instrumentClasses() {
        return $$(By.xpath("//div[contains(@class, 'b-instruments-data__item')]"));
    }

    public static ElementsCollection tradeOrderList() {
        return $$(By.xpath("//tr[@class = 'b-table__row b-table__row--level-1']"));
    }

    public static ElementsCollection agreementsList() {
        return $$(By.xpath("//div[@class='b-layout b-product-agrements__list']"));
    }

    public static ElementsCollection toolsList() {
        return $$(By.xpath("//div[contains(@class, 'favorite-row__wrapper')]"));
    }

    public static ElementsCollection newsList() {
        return $$(By.xpath("//div[@class = 'b-analytics-news-list__desc']"));
    }

    public static ElementsCollection relevantIdeasList() {
        return $$(By.xpath("//tr[@class = 'b-table__row b-table__row--level-1']"));
    }

    public static ElementsCollection activesList() {
        return $$(By.xpath("//div[contains(@class, 'b-instruments-data__base-item')]//div[contains(@class, 'amount-by-currency')]"));
    }

    public static ElementsCollection accountsTabActivesList() {
        return $$(By.xpath("//div[@class = 'b-layout__row b-product__row-top']"));
    }

    public static SelenideElement productBlock(String text) {
        return $(By.xpath("//span[contains(text(), '" + text + "')]"));
    }

    public static ElementsCollection productBlockNameList() {
        return $$(By.xpath("//span[@class='b-product__name']"));
    }

    public static SelenideElement buttonWithText(String text) {
        return $(By.xpath("//button[text() = '" + text + "']"));
    }

    public static SelenideElement newsTitleName() {
        return $(By.xpath("//h1[@class = 'b-title']"));
    }

    public static SelenideElement productBlockName() {
        return $(By.xpath("//span[@class='b-product__name']"));
    }

    public static SelenideElement activesTextBlock() {
        return $(By.xpath("(//div[contains(@class, 'b-sum--xl')]/span)[1]"));
    }

    public static SelenideElement todaysResult() {
        return $(By.xpath("(//div[contains(@class, 'b-sum--xl')]/span)[2]"));
    }

    public static SelenideElement sumPerPeriodTitle() {
        return $(By.xpath("//div[contains(@class, 'b-results-per-period__row')]/h4[text()='Данные за период']"));
    }

    public static SelenideElement mainPageMenuTitle(String text) {
        return $(By.xpath("//span[contains(text(), '" + text + "')]")).shouldBe(visible, Duration.ofSeconds(20));
    }

    public static SelenideElement mainPageTitle() {
        return $(By.xpath("//div[contains(@class, 'b-header__title')]")).shouldBe(visible, Duration.ofSeconds(20));
    }


}
