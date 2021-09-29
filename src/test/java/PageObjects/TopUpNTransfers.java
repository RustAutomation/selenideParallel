package PageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TopUpNTransfers {

    public static SelenideElement productListOption(String text) {
        return $(By.xpath("//a[contains(@class, 'b-product-transfer-links-list-item') and contains(text(),'" + text + "')]"));
    }

    public static SelenideElement getPaymentRequisites() {
        return $(By.xpath("//div[text() = 'Денежные средства']/..//*[contains(text(), 'Реквизиты')]"));
    }

}
