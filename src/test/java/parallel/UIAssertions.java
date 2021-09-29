package parallel;

import Helpers.Helpers;
import PageObjects.*;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.qameta.allure.Step;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;

public class UIAssertions {
    private String dateInString;

    @Step("assert Portfolio is present on page")
    @And("^assert Portfolio is present on page$")
    public void assertPortfolioPresent() throws InterruptedException {
        Helpers.waitForElementDisappear(Trading.loadingIcon());
        Trading.portfolioItemsList().get(0).shouldBe(appear, Duration.ofSeconds(20));
        Assert.assertFalse(Trading.portfolioItemsList().isEmpty());
        Assert.assertFalse(Trading.portfolioTopUpButton().isEmpty());
    }

    @Step("assert Get payment requisites option is present on page")
    @And("^assert Get payment requisites option is present on page$")
    public void assertGetRequisitesPresent() throws InterruptedException {
        Helpers.waitForElementDisappear(Trading.loadingIcon());
        Assert.assertTrue(TopUpNTransfers.getPaymentRequisites().isDisplayed());
    }

    @Step("assert that address input field is not empty")
    @And("^assert that address input field is not empty$")
    public void assertPersonalDataAddressInputFieldNotEmpty() {
        PersonalProfilePage.modalCurrentAddressInputField().click();
        Assert.assertFalse(PersonalProfilePage.modalCurrentAddressInputContent().getValue().isEmpty());
    }

    @Step("assert Instrument list is present on page")
    @And("^assert Instrument list is present on page$")
    public void assertInstrumentsPresent() {
        Assert.assertFalse(MainPage.instruments().isEmpty());
    }

    @Step("assert Instrument Classes list is present on page")
    @And("^assert Instrument Classes list is present on page$")
    public void assertInstrumentClassesPresent() {
        Assert.assertFalse(MainPage.instrumentClasses().isEmpty());
    }

    @Step("assert Trade Order list is present on page")
    @And("^assert Trade Order list is present on page$")
    public void assertTradeOrderListPresent() {
        MainPage.tradeOrderList().get(0).shouldBe(appear, Duration.ofSeconds(20));
        Assert.assertFalse(MainPage.tradeOrderList().isEmpty());
    }

    @Step("assert Agreements list is present on page")
    @And("^assert Agreements list is present on page$")
    public void assertAgreementsListPresent() throws InterruptedException {
        Helpers.waitForElementDisappear(Trading.selfTradingLoading());
        MainPage.agreementsList().get(0).shouldBe(visible, Duration.ofSeconds(60));
        Assert.assertFalse(MainPage.agreementsList().isEmpty());
    }

    @Step("assert at least one tool is present on page")
    @And("^assert at least one tool is present on page$")
    public void assertToolsListPresent() {
        MainPage.toolsList().get(0).shouldBe(appear, Duration.ofSeconds(20));
        Assert.assertFalse(MainPage.toolsList().isEmpty());
    }

    @Step("assert News list is present on page")
    @And("^assert News list is present on page$")
    public void assertNewsListPresent() throws InterruptedException {
        Helpers.waitForElementDisappear(Trading.loadingIcon());
        MainPage.newsList().get(0).shouldBe(appear, Duration.ofSeconds(20));
        Assert.assertFalse(MainPage.newsList().isEmpty());
        MainPage.newsList().get(1).click();
        Assert.assertTrue(MainPage.newsTitleName().shouldBe(appear, Duration.ofSeconds(20)).isDisplayed());
    }

    @Step("assert raw with at least one option is present on page")
    @And("^assert raw with at least one option is present on page$")
    public void assertRelevantIdeasListPresent() throws InterruptedException {
        Thread.sleep(2000);
        Helpers.waitForElementDisappear(ReportsNTaxes.reportsNTaxesPageLoading());
        MainPage.relevantIdeasList().get(0).shouldBe(visible, Duration.ofSeconds(60));
        Assert.assertFalse(MainPage.relevantIdeasList().isEmpty());
    }

    @Step("assert profile data is present on page")
    @And("^assert profile data is present on page$")
    public void assertProfileDataPresent() {
        Assert.assertTrue(PersonalProfilePage.dateOfBirth().shouldBe(appear, Duration.ofSeconds(30)).isDisplayed());
    }

    @Step("assert Continue button in Header is present on page")
    @And("^assert Continue button in Header is present on page$")
    public void assertContinueInHeaderIsPresent() {
        while (!PersonalProfilePage.modalAddressContinueInHeader().isDisplayed()) {
            Selenide.sleep(3000);
        }
        Assert.assertTrue(PersonalProfilePage.modalAddressContinueInHeader().shouldBe(visible, Duration.ofSeconds(40)).isDisplayed());
    }

    @Step("assert Data error is missing on profile page")
    @And("^assert Data error is missing on profile page$")
    public void assertDataErrorMissing() {
        Selenide.sleep(1000);
        Assert.assertTrue(PersonalProfilePage.personalProfileDataError().isEmpty());
    }

    @Step("assert Complete solutions is present for non qualified user")
    @And("^assert Complete solutions is present for non qualified user$")
    public void assertCompleteSolutionsNonQualPresent() {
        CatalogPage.completeSolutionNonQualUser().get(0).shouldBe(appear, Duration.ofSeconds(20));
        Assert.assertFalse(CatalogPage.completeSolutionNonQualUser().isEmpty());
    }

    @Step("assert Complete solutions is present for qualified user")
    @And("^assert Complete solutions is present for qualified user$")
    public void assertCompleteSolutionsQualPresent() {
//        CatalogPage.completeSolutionQualUser().get(0).shouldBe(appear, Duration.ofSeconds(20));
//        Assert.assertFalse(CatalogPage.completeSolutionQualUser().isEmpty());
        Assert.assertTrue(CatalogPage.completeSolutionBlockQualUser().shouldBe(appear, Duration.ofSeconds(20)).isDisplayed());
    }

    @Step("assert Showcase cards list is present on page")
    @And("^assert Showcase cards list is present on page$")
    public void assertShowcaseCardsListPresent() {
        CatalogPage.showcaseCardsList().get(0).shouldBe(appear, Duration.ofSeconds(60));
        Assert.assertFalse(CatalogPage.showcaseCardsList().isEmpty());
    }

    @Step("assert Actives list is present on page")
    @And("^assert Actives list is present on page$")
    public void assertActivesListPresent() {
        Assert.assertFalse(MainPage.accountsTabActivesList().isEmpty());
    }

    @Step("assert that main screen opened")
    @And("^assert that main screen opened$")
    public static void assertMainScreenOpen() {
        MainPage.pageTitle().shouldBe(visible, Duration.ofSeconds(100));
        Assert.assertEquals("Обзор 360˚", MainPage.pageTitle().getText());
    }

    @Step("assert that common actives sum is >= 0")
    @And("^assert that common actives sum is >= 0$")
    public void assertActives() {
        MainPage.activesTextBlock().shouldBe(visible, Duration.ofSeconds(20));
        int y = Integer.parseInt(MainPage.activesTextBlock().getText().replaceAll("[^\\d.]", ""));
        Assert.assertTrue(y >= 0);
    }

    @Step("assert total securities price >= 0")
    @And("^assert total securities price >= 0$")
    public void assertSecuritiesTotals() throws InterruptedException {
        Helpers.waitForElementDisappear(Trading.loadingIcon());
        while (!ReportsNTaxes.securitiesTotalList().get(1).isDisplayed()) {
            Thread.sleep(1000);
        }
        double x = Double.parseDouble(ReportsNTaxes.securitiesTotalList()
                .get(1).getText().replaceAll(" ", "").replaceAll(",", "."));

        double y = Double.parseDouble(ReportsNTaxes.securitiesTotalList()
                .get(2).getText().replaceAll(" ", "").replaceAll(",", "."));
        Assert.assertTrue(x >= 0);
        Assert.assertTrue(y >= 0);
    }

    @Step("assert that text: {text} is present")
    @And("^assert that text: \"(.*)\" is present$")
    public void assertActives(String text) {
        Assert.assertEquals(text, MainPage.sumPerPeriodTitle().getText());
    }

    @Step("assert that accounts names are present")
    @And("^assert that accounts names are present$")
    public void assertAccountName() {
        Assert.assertFalse(MainPage.productBlockNameList().isEmpty());
    }

    @Step("assert that the End date equals current date")
    @And("^assert that the End date equals current date$")
    public void assertEndDateIsCurrent() {
        String pattern = "dd.MM.yyyy";
        String dateInString = new SimpleDateFormat(pattern).format(new Date());
        Assert.assertEquals(dateInString, MainPage.periodEndDate().getText());
    }

    @Step("assert investment terms card presence")
    @And("^assert investment terms card presence$")
    public void assertInvestmentTermsCardPresent() {
        Assert.assertTrue(CatalogPage.completeSolutionDetailedInvestTermsQualUser()
                .shouldBe(visible, Duration.ofSeconds(30))
                .isDisplayed());
    }

    @Step("assert report: {reportType} history status result is Ready")
    @And("^assert report: \"(.*)\" history status result is Ready$")
    public void assertReportHistoryStratusError(String reportType) throws InterruptedException {
        String pattern = "dd.MM.yyyy";
        this.dateInString = new SimpleDateFormat(pattern).format(new Date());
        Helpers.waitForElementDisappear(Trading.loadingIcon());
        int timer = 0;
        while (!ReportsNTaxes.reportHistoryStatusReady(reportType, dateInString).get(0).isDisplayed()) {
            Thread.sleep(1000);
            timer = timer + 15;
            if (ReportsNTaxes.reportHistoryStatusReady(reportType, dateInString).get(0).isDisplayed()) {
                System.out.println("\nThe report is ready");
                break;
            } else {
                Thread.sleep(15000);
                Selenide.refresh();
            }
            if(timer > 120) {
                System.err.println("The report await time exceeds two minutes");
                Assert.assertTrue(timer < 130);
            }
        }
        Selenide.sleep(2000);
    }
}
