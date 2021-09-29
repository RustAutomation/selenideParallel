package PageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class WhichApproach {

    public static SelenideElement strategySlider() {
        return $(By.xpath("//div[contains(@class, 'InputRange_sliderThumb')]"));
    }

    public static SelenideElement sliderLowRisk() {
        return $(By.xpath("//div[contains(@class, 'InputRange_sliderDots')]/div[1]"));
    }

    public static SelenideElement sliderMediumRisk() {
        return $(By.xpath("//div[contains(@class, 'InputRange_sliderDots')]/div[2]"));
    }

    public static SelenideElement sliderHighRisk() {
        return $(By.xpath("//div[contains(@class, 'InputRange_sliderDots')]/div[3]"));
    }

    public static SelenideElement sliderMaximumRisk() {
        return $(By.xpath("//div[contains(@class, 'InputRange_sliderDots')]/div[4]"));
    }

}
