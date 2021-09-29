package PageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ReportsNTaxes {

    public static SelenideElement pageTab(String text) {
        return $(By.xpath("//button[text() = '" + text + "']")).shouldBe(appear, Duration.ofSeconds(40));
    }

    public static SelenideElement getReport() {
        return $(By.xpath("//button[text() = 'Заказать отчет / справку']"));
    }

    public static SelenideElement reportOption(String text) {
        return $(By.xpath("//div[@class = 'b-select__label' and contains(text(), '" + text + "')]"));
    }

    public static SelenideElement dropdownOption(String text) {
        return $(By.xpath("//ul[@class='b-select__list']//span[@title = '" + text + "']"));
    }

    public static SelenideElement pdfCheckBoxOption(String text) {
        return $(By.xpath("//div/*[text() = 'PDF']"));
    }

    public static SelenideElement reportHistoryStatusError(String text) {
        return $(By.xpath("//td[@class = 'b-table__col ']/div[contains(text(), '" + text + "')]/..//..//div[text() = 'Ошибка']"));
    }

    public static SelenideElement reportsNTaxesPageLoading() {
        return $(By.xpath("//div[contains(@class, 'b-loading--page')]"));
    }

    public static SelenideElement reportsNTaxesTaxesResultsPerYear() {
        return $(By.xpath("//span[@class='b-select__selected-label']"));
    }

    public static SelenideElement reportsNTaxesTaxesResultsPerYearValue(String value) {
        return $(By.xpath("//span[text() = '" + value + "']"));
    }

    public static ElementsCollection reportHistoryStatusReady(String reportType, String date) {
        return $$(By.xpath("(//td[@class = 'b-table__col ']/..//div[contains(text(),'" + reportType + "')]/../../..//div[contains(text(), '" + date + "')]/..//..//div[contains(text(), 'Готово')])"));
    }

    public static ElementsCollection securitiesTotalList() {
        return $$(By.xpath("//span[contains(text(), 'Итого')]/../../..//span[contains(@class, 'b-instrument-page__table-header--total')]"));
    }
}
