package PageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PersonalProfilePage {

    public static SelenideElement dateOfBirth() {
        return $(By.xpath("//div[@class = 'b-label-and-text']/*[contains(text(), 'Дата рождения')]/../div[2]"));
    }

    public static SelenideElement editAddress() {
        return $(By.xpath("//div[contains(@class, 'b-profile-addresses')]//div[contains(text(), 'Редактировать')]"));
    }

    public static SelenideElement editAddressModalTitle() {
        return $(By.xpath("//div[@class = 'b-profile-edit-container']//div[contains(text(), 'Адреса')]"));
    }

    public static SelenideElement modalYouHaveDraft() {
        return $(By.xpath("//div[@class = 'b-profile-draft-dialog']//div[text() = 'У вас есть сохраненный черновик с внесенными ранее данными']"))
                .shouldBe(appear, Duration.ofSeconds(30));
    }

    public static SelenideElement modalYouHaveDraftYesButton() {
        return $(By.xpath("//button[text() = 'Да']"));
    }

    public static SelenideElement modalYouHaveDraftNoButton() {
        return $(By.xpath("//button[text() = 'Нет (удалить черновик)']"));
    }

    public static SelenideElement modalAddressesTab(String text) {
        return $(By.xpath("//span[text() = '" + text + "']"));
    }

    public static SelenideElement modalAddressEditInputField(String text) {
        return $(By.xpath("//*[text() = '" + text + "']"));
    }

    public static SelenideElement modalAddressEditFocusedField() {
        return $(By.xpath("//input[@name = 'addressString']"));
    }

    public static SelenideElement modalCurrentAddressInputField() {
        return $(By.xpath("//*[text() = 'Адрес']"));
    }

    public static SelenideElement modalCurrentAddressInputContent() {
        return $(By.xpath("//input[@name='addressString']"));
    }

    public static SelenideElement modalCurrentTemporaryAddressExpiryDateInputField() {
        return $(By.xpath("//input[@name = 'Окончание срока действия регистрации']"));
    }

    public static SelenideElement modalCurrentIndexInputField() {
        return $(By.xpath("//input[@name = 'postIndex']"));
    }

    public static SelenideElement modalCurrentRegionInputField() {
        return $(By.xpath("//input[@name = 'region']"));
    }

    public static SelenideElement modalAddressAutocomplete() {
        return $(By.xpath("//div[@class='b-drop-content-container b-autocomplete__items-container']"));
    }

    public static SelenideElement modalAddressEnableKladrCheck() {
        return $(By.xpath("//button[text()='Включить']"));
    }

    public static ElementsCollection modalCurrentPostRadioButton() {
        return $$(By.xpath("//div[@class = 'b-radio-group__item-toggle']"));
    }

    public static SelenideElement modalAddressContinueInHeader() {
        return $(By.xpath("//div[@class ='b-profile-header__confirm-btn']"));
    }

    public static ElementsCollection personalProfileDataError() {
        return $$(By.xpath("//div[text() = 'Есть ошибки в данных']"));
    }

}
