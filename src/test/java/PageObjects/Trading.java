package PageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Trading {

    public static ElementsCollection portfolio() {
        return $$(By.xpath("//div[contains(@class, 'b-title')]"));
    }

    public static ElementsCollection portfolioItemsList() {
        return $$(By.xpath("//div[@class='b-portfolios']/div"));
    }

    public static ElementsCollection portfolioTopUpButton() {
        return $$(By.xpath("//button[text()='Пополнить']"));
    }

    public static SelenideElement tradingInstrumentInput() {
        return $(By.xpath("//input[contains(@name, 'Введите название инструмента')]"));
    }

    public static SelenideElement tradingInstrumentInputDropdownItem(String stock, String toolName) {
        return $(By.xpath("//tr[contains(@class, 'b-table__row')]//div[contains(text(), '" + stock
                + "')]/../..//div[contains(text(), '" + toolName + "')]"));
    }

    public static SelenideElement tradingInstrumentPageRawItem(String stock, String toolName) {
        return $(By.xpath("//div/a[contains(text(), '" + toolName
                + "')]/../../div/span[contains(text(), '" + stock + "')]/../..//a[contains(@class, 'instrument-link')]"));
    }

    public static ElementsCollection tradingInstrumentDeletePageRawItem() {
        return $$(By.xpath("//div[@class = 'b-favorite-list__delete']"));
    }

    public static ElementsCollection tradingInstrumentFavouriteRawItem() {
        return $$(By.xpath("//div[@class = 'favorite-row']"));
    }

    public static SelenideElement tradingLastCandle() {
        return $(By.xpath("//div[contains(@style, 'solid; white-space: nowrap; z-index: 9999999')]"));
    }

    public static SelenideElement tradingLastCandlePeriod() {
        return $(By.xpath("//div[contains(@style, 'solid; white-space: nowrap; z-index: 9999999')]/strong"));
    }

    public static SelenideElement tradingCandleBlock() {
        return $(By.xpath("//div[@id='tv-chart']/iframe"));
    }

    public static SelenideElement tradingLastDealCost() {
        return $(By.xpath("//div[@class='price-indicator']"));
    }

    public static SelenideElement tradingCandlePeriod(String value) {
        //div[@class='layout__area--top']//div[contains(@title, '5 минут')][1]
        //iframe[contains(@name. 'tradingview')]//body[contains(@class, 'chart-page')]//div[contains(@class, 'toolbar')]//div[contains(text(), '5М')]
        return $(By.xpath("(//div[contains(@title, '" + value + "')])[1]/div"));
    }

    public static SelenideElement tradingCandleWrapper() {
        return $(By.xpath("//div[@class = 'chart-gui-wrapper']"));
    }

    public static SelenideElement tradingCandleIframe() {
        return $(By.xpath("//div[@id= 'tv-chart']/iframe[contains(@id, 'tradingview')]"));
    }

    public static SelenideElement tradingStockButton(String text) {
        return $(By.xpath("//span[text() = '" + text + "']"));
    }

    public static SelenideElement tradingStockBuyChooseAccount(String name) {
        return $(By.xpath("//div[contains(text(), '" + name + "')]"));
    }

    public static SelenideElement tradingStockBuyChooseAccountDropdown(String text) {

        if(text.toLowerCase().equals("iis")) {
            return $(By.xpath("//span[@class = 'b-select__list-item-label'][contains(text(), 'i')]"));
        } else if(text.toLowerCase().equals("not iis")) {
            return $(By.xpath("//span[@class = 'b-select__list-item-label'][not(contains(text(), 'i'))]"));
        } else {
            return $(By.xpath("//span[contains(text(),'" + text + "')]"));
        }
    }

    public static SelenideElement tradingStockBuyCost() {
        return $(By.xpath("//label[contains(text(), 'Цена покупки')]"));
    }

    public static SelenideElement tradingStockBuyQuantity() {
        return $(By.xpath("//label[contains(text(), 'Количество шт')]"));
    }

    public static SelenideElement tradingStockBuyQuantityInput() {
        return $(By.xpath("//label[contains(text(), 'Количество шт')]/..//input"));
    }

    public static SelenideElement tradingStockBuyCostInput() {
        return $(By.xpath("//label[contains(text(), 'Цена покупки')]/..//input"));
    }

    public static SelenideElement tradingStockModalButton(String name) {
        return $(By.xpath("//button[text() = '" + name + "']"));
    }

    public static SelenideElement tradingStockModalOrderCanceled() {
        return $(By.xpath("//div[contains(text(), 'Лимитная заявка отклонена')]"));
    }

    public static SelenideElement tradingStockModalOrderLoading() {
        return $(By.xpath("//img[@class = 'b-select__loading-icon']"));
    }

    public static SelenideElement loadingIcon() {
        return $(By.xpath("(//*[contains(@class, 'loading')])[2]"));
    }

    public static SelenideElement selfTradingLoading() {
        return $(By.xpath("//span[contains(@class, 'b-loading__img')]"));
    }

    public static SelenideElement tradingStockModalTradePasswordInput() {
        return $(By.xpath("//label[text() = 'Торговый пароль']/../input"));
    }

}
