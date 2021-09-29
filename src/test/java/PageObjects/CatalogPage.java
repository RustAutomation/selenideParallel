package PageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CatalogPage {
    public static ElementsCollection showcaseCardsList() {
        return $$(By.xpath("//div[@class = 'catalog-card ordinal-card']"));
    }

    public static ElementsCollection completeSolutionNonQualUser() {
        return $$(By.xpath("//div[@class = 'b-content-card b-layout__row']"));
    }

    public static ElementsCollection completeSolutionQualUser() {
        return $$(By.xpath("//div[@class = 'b-product-item b-sp--card-bg']"));
    }

    public static SelenideElement completeSolutionBlockQualUser() {
        return $(By.xpath("//div[@class='b-layout-row']//*[contains(text(),'Готовые решения')]"));
    }

    public static SelenideElement completeSolutionTabQualUser(int index) {
        return $(By.xpath("//div[@class = 'slick-list']//div[@data-index = '" + index + "']"));
    }

    public static SelenideElement completeSolutionDetailedInvestTermsQualUser() {
        return $(By.xpath("//div//*[contains(text(),  'Рекомендуемый срок') or contains(text(), 'Срок инвестирования')]"));
    }

    public static ElementsCollection completeSolutionDetailedInvestTermsContentQualUser() {
        return $$(By.xpath("//div[@class = 'b-sp-product-card__footer_params-item']//div[@class='b-icon-with__content']"));
    }


}
