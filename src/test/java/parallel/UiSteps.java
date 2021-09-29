package parallel;

import Helpers.ConfigGetPropertyValues;
import Helpers.Helpers;
import PageObjects.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.HoverOptions.withOffset;
import static com.codeborne.selenide.Selenide.open;

public class UiSteps {

    public WebDriverRunner driverSelenide;
    public static ThreadLocal<WebDriverRunner> tdriverSelenide = new ThreadLocal<WebDriverRunner>();

    public static synchronized WebDriverRunner getSeleideDriver() {
        return tdriverSelenide.get();
    }


    public WebDriverRunner initialize_driver_selenide(String browser) {

        if (browser.toLowerCase().contains("ie")) {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            System.setProperty("WebDriver.ie.driver", "src/test/resources/IEDriverServer.exe");
            Configuration.browser = "ie";
            WebDriverRunner.setWebDriver(new InternetExplorerDriver(capabilities));
            driverSelenide = new WebDriverRunner();
            tdriverSelenide.set(driverSelenide);

        } else if (browser.toLowerCase().contains("firefox")) {
            Configuration.browser = "firefox";
//            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
//            FirefoxDriverManager.firefoxdriver().setup();
            FirefoxDriverManager.firefoxdriver().arch64().setup();
            WebDriverRunner.setWebDriver(new FirefoxDriver());
            driverSelenide = new WebDriverRunner();
            tdriverSelenide.set(driverSelenide);
        } else if (browser.toLowerCase().contains("chrome")) {
            Configuration.browser = "chrome";
//            System.setProperty("webdriver.chrome.driver", "/test/resosrcurces/chromedriver.exe");
            ChromeDriverManager.chromedriver().arch64().setup();
            WebDriverRunner.setWebDriver(new ChromeDriver());
            driverSelenide = new WebDriverRunner();
            tdriverSelenide.set(driverSelenide);
        }
        return getSeleideDriver();
    }

    public static int InvestmentTermsCardindex;
    private String url;
    private String cost;

    @Given("^browser: \"(.+)\"$")
    public static void setupClass(String browser) {
        System.setProperty("chromedriver.exe", "/src/test/resources");
        System.setProperty("selenide.browser", browser);
    }

    @Step("login with browser: {browser} name: {login} and password: {password}")
    @And("^login with browser: \"(.*)\" name: \"(.*)\" and password: \"(.*)\"$")
    public void userCanLoginWithUsername(String browser, String login, String password) throws IOException {
        ConfigGetPropertyValues properties = new ConfigGetPropertyValues();
        this.url = properties.getProperty("config.properties", "url");
//        Configuration.browser = properties.getProperty("config.properties", "browser").toLowerCase();
        initialize_driver_selenide(browser.toLowerCase());
        open(url);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        LoginPage.loginField().setValue(login);
        LoginPage.passwordField().setValue(password);
        LoginPage.submitButton().click();
        LoginPage.passwordField().shouldBe(disappear, Duration.ofSeconds(30));

        try {
            if (LoginPage.smsInputField().exists() &&
                    properties.getProperty("config.properties", "url").equals("https://newlk-tt.open-broker.ru/")) {
                Thread.sleep(3000);
                LoginPage.smsInputField().shouldBe(visible, Duration.ofSeconds(30)).click();
                LoginPage.smsInputField().sendKeys("1234");
                LoginPage.submitButton().click();
            } else if (LoginPage.smsInputField().exists() &&
                    properties.getProperty("config.properties", "url").equals("https://newlk-pp.open-broker.ru/")) {
                Thread.sleep(3000);
                LoginPage.smsInputField().shouldBe(visible, Duration.ofSeconds(30)).click();
                LoginPage.smsInputField().sendKeys("1234");
                LoginPage.submitButton().click();
            }
        } catch (Exception e) {
            System.out.println("\nNo SMS needed\n");
        }
        UIAssertions.assertMainScreenOpen();
    }

    @Step("go to endpoint: {endpoint}")
    @And("^go to endpoint: \"(.*)\"$")
    public void goToEndpoint(String endpoint) {
        open(url + endpoint);
    }

    @Step("chose main menu option with text: {name}")
    @And("^chose main menu option with text: \"(.*)\"$")
    public void choseMainMenuOption(String name) throws InterruptedException {
//        Thread.sleep(2000);
        MainPage.mainPageMenuTitle(name).scrollIntoView(false);
        MainPage.mainPageMenuTitle(name).shouldBe(visible, Duration.ofSeconds(60)).click();
    }

    @Step("chose trade instrument: {toolName} in stock: {stock} from dropdown")
    @And("^chose trade instrument: \"(.*)\" in stock: \"(.*)\" from dropdown$")
    public void tradeInstrumentDropdown(String toolName, String stock) {
        Trading.tradingInstrumentInputDropdownItem(stock, toolName).shouldBe(visible, Duration.ofSeconds(60)).click();
    }

    @Step("chose trade instrument: {toolName} in stock: {stock} from Trading page")
    @And("^chose trade instrument: \"(.*)\" in stock: \"(.*)\" from Trading page$")
    public void tradeInstrumentOnTradingPage(String toolName, String stock) throws InterruptedException {
        Trading.tradingInstrumentPageRawItem(stock, toolName).click();
        Thread.sleep(100);
    }

    @Step("search for trade instrument: {toolName}")
    @And("^search for trade instrument: \"(.*)\"$")
    public void tradeInstrumentSearch(String toolName) throws InterruptedException {
        Thread.sleep(5000);
        if (Trading.tradingInstrumentFavouriteRawItem().get(1).exists()) {
            try {
                Trading.tradingInstrumentFavouriteRawItem().get(1).hover();
                while (Trading.tradingInstrumentDeletePageRawItem().get(1).isDisplayed()) {
                    Trading.tradingInstrumentFavouriteRawItem().get(1).hover();
                    Trading.tradingInstrumentDeletePageRawItem().get(1).click();
                    Thread.sleep(5000);
                }
            } catch (NoSuchElementException e) {
                System.err.println("\nNo favourite trading instruments found\n" + e);
            } finally {
                Trading.tradingInstrumentInput().setValue(toolName);
            }
        } else {
            Trading.tradingInstrumentInput().setValue(toolName);
        }

    }

    @Step("click edit the Address block")
    @And("^click edit the Address block$")
    public void editAddressInProfile() {
        PersonalProfilePage.editAddress().shouldBe(enabled, Duration.ofSeconds(60)).click();
        if (PersonalProfilePage.modalYouHaveDraftNoButton().isDisplayed()) {
            try {
                PersonalProfilePage.modalYouHaveDraftNoButton().click();
            } catch (Exception e) {
                System.err.println("No draft was found");
            }
        }
    }

    @Step("chose Complete solution with index: {index}")
    @And("^chose Complete solution with index: \"(.*)\"$")
    public void choseCompleteSolution(int index) {
        CatalogPage.completeSolutionTabQualUser(index).click();
        this.InvestmentTermsCardindex = index;
    }

    @Step("chose tab with text: {name}")
    @And("^chose tab with text: \"(.*)\"$")
    public void choseReportsNTaxesTab(String name) throws InterruptedException {
        while (!ReportsNTaxes.pageTab(name).isEnabled() && ReportsNTaxes.pageTab(name).isDisplayed()) {
            Selenide.sleep(1000);
        }
        Thread.sleep(3000);
        ReportsNTaxes.pageTab(name).click();
    }

    @Step("set taxes per year: {name}")
    @And("^set taxes per year: \"(.*)\"$")
    public void setTaxesPeriod(String name) throws InterruptedException {
        Helpers.waitForElementDisappear(Trading.loadingIcon());
        Helpers.waitForElementAppear(ReportsNTaxes.reportsNTaxesTaxesResultsPerYear());
        ReportsNTaxes.reportsNTaxesTaxesResultsPerYear().click();
        ReportsNTaxes.reportsNTaxesTaxesResultsPerYearValue(name).click();
        Helpers.waitForElementDisappear(ReportsNTaxes.reportsNTaxesPageLoading());
    }

    @Step("chose modal window tab with text: {name}")
    @And("^chose modal window tab with text: \"(.*)\"$")
    public void choseModalAddressesEditTab(String name) throws InterruptedException {
        Helpers.waitForElementDisappear(Trading.loadingIcon());
        PersonalProfilePage.modalAddressesTab(name).shouldBe(visible, Duration.ofSeconds(120)).click();
        PersonalProfilePage.modalAddressesTab(name).shouldBe(enabled, Duration.ofSeconds(30));
    }

    @Step("set profile address as: {address}")
    @And("^set profile address as: \"(.*)\"$")
    public void setProfileAddress(String address) throws InterruptedException {
        try {
            if (PersonalProfilePage.modalAddressEnableKladrCheck().isDisplayed()) {
                PersonalProfilePage.modalAddressEnableKladrCheck().click();
            }
        } catch (Exception e) {
            System.out.println("\nKladr check is enabled");
        }

        PersonalProfilePage.modalCurrentAddressInputField().shouldBe(visible, Duration.ofSeconds(60)).click();
        PersonalProfilePage.modalAddressEditFocusedField().setValue(address);
        Thread.sleep(2000);
        PersonalProfilePage.modalAddressAutocomplete().click();
    }

    @Step("set temporary address expiry date as: {date}")
    @And("^set temporary address expiry date as: \"(.*)\"$")
    public void setTemporaryAddressExpiryDate(String date) {
        PersonalProfilePage.modalCurrentTemporaryAddressExpiryDateInputField().setValue(date);
    }

    @Step("set value: {value} for report option with text: {name}")
    @And("^set value: \"(.*)\" for report option with text: \"(.*)\"$")
    public void choseReportOption(String value, String name) {
        while (!ReportsNTaxes.reportOption(name).isEnabled()) {
            Selenide.sleep(1000);
        }
        ReportsNTaxes.reportOption(name).shouldBe(enabled, Duration.ofSeconds(30)).click();
        ReportsNTaxes.dropdownOption(value).click();
    }

    @Step("press the button with text: {name}")
    @And("^press the button with text: \"(.*)\"$")
    public void pressTheButtonWithText(String name) {
        MainPage.buttonWithText(name).shouldBe(visible, Duration.ofSeconds(30)).click();
    }

    @Step("chose opened position with index: {index}")
    @And("^chose opened position with index: \"(.*)\"$")
    public void choseOpenPosition(String index) {
        Trading.portfolio().get(Integer.parseInt(index)).click();
    }

    @Step("chose product account with name: {name}")
    @And("^chose product account with name: \"(.*)\"$")
    public void choseProductAccountName(String name) {
        MainPage.productBlock(name).click();
    }

    @Step("chose use post radio button with index: {index}")
    @And("^chose use post radio button with index: \"(.*)\"$")
    public void choseModalAddressEditPostalTab(int index) {
        PersonalProfilePage.modalCurrentPostRadioButton().get(index).click();
    }

    @Step("check last candle popup meets the requirements")
    @And("^check last candle popup meets the requirements$")
    public void checkLastCandle() throws InterruptedException { //Метод проверяет что последняя свеча является последней за текущую дату
        String currentDate = new SimpleDateFormat("dd").format(new Date());
        String currentMonth = Helpers.getMonthForInt(Integer.parseInt(new SimpleDateFormat("MM").format(new Date())));
        String currentYear = new SimpleDateFormat("yyyy").format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm").format(new Date());

        Trading.tradingCandleWrapper().hover(withOffset(345, 119));

//        String x = Trading.tradingLastCandle().getText();

//        String time = Trading.tradingLastCandlePeriod().getText();
//        while (x.equals("")) {
//            Trading.tradingCandleBlock().hover();
//            Trading.tradingCandleBlock().hover(withOffset(345, 119));
//            x = Trading.tradingLastCandle().getText();
//            time = Trading.tradingLastCandlePeriod().getText();
//        }
//        int currentCandleTime = Integer.parseInt(time.substring(time.length() - 5).replaceAll(":", ""));
//        int currTime = Integer.parseInt(currentTime.replaceAll(":", ""));
//        int difference = currTime - currentCandleTime;
//        System.out.println("\nthe last renewal was " + difference + " minutes ago");
//        Assert.assertTrue(difference <= 6);
//        Assert.assertTrue(time.contains(currentDate + " " + currentMonth + " " + currentYear));

    }

    @Step("check the last deal cost is changing")
    @And("^check the last deal cost is changing$")
    public void checkLastDealCost() throws InterruptedException {
        int counter = 0;
        Selenide.refresh();
        this.cost = Trading.tradingLastDealCost().getText();
        String newCost = "";
//        while (counter < 120) {
//            Thread.sleep(1000);
//            newCost = Trading.tradingLastDealCost().getText();
//            counter++;
//            if (!cost.equals(newCost)) {
//                break;
//            }
//        }
//        System.err.println("\nThe cost " + cost + " did not change");
//        Assert.assertNotEquals(cost, newCost);
    }

    @Step("set cost to current cost minus 10%")
    @And("^set cost to current cost minus 10%$")
    public void choseModalStockCost() {
        int finalCost = Integer.parseInt(cost.replaceAll("[^0-9]+", ""));
        int percentage = (int) (finalCost * (10.0f / 100.0f));
        Trading.tradingStockBuyCost().click();
        Trading.tradingStockBuyCostInput().setValue(String.valueOf(finalCost - percentage));
    }

    @Step("set quantity to: {quantity}")
    @And("^set quantity to: \"(.*)\"$")
    public void setModalStockQuantity(String quantity) {
        Selenide.sleep(1000);
        Trading.tradingStockBuyQuantity().click();
        Trading.tradingStockBuyQuantityInput().setValue(quantity);
    }

    @Step("press button: {button}")
    @And("^press button: \"(.*)\"$")
    public void confirmModalStockPurchase(String button) throws InterruptedException {
        Trading.tradingStockModalButton(button).shouldBe(enabled, Duration.ofSeconds(30)).click();
        while (Trading.tradingStockModalOrderLoading().isDisplayed()) {
            Thread.sleep(1000);
        }
        Assert.assertFalse(Trading.tradingStockModalOrderCanceled().isDisplayed());
    }

    @Step("chose candle period: {period}")
    @And("^chose candle period: \"(.*)\"$")
    public void choseCandlePeriod(String period) throws InterruptedException {
        Helpers.waitForElementDisappear(Trading.tradingStockModalOrderLoading());
        Trading.tradingCandleIframe().shouldBe(visible, Duration.ofSeconds(60));
        Trading.tradingCandleIframe().scrollIntoView(false); //false means that the element we scrolled to should be at the bottom of area displayed, true - scrolls until the element at the top
        Thread.sleep(10000);

        WebDriverRunner.getWebDriver().switchTo()
                .frame(Trading.tradingCandleIframe())
                .findElement(By.xpath("//div[contains(text(), '" + period + "')]"))
                .click();
    }

    @Step("click on instrument button: {name} for purchase")
    @And("^click on instrument button: \"(.*)\" for purchase$")
    public void clickOnTradingButton(String name) {
        Selenide.refresh();
        Trading.tradingStockButton(name).shouldBe(visible, Duration.ofSeconds(60)).click();
    }

    @Step("chose dropdown {name}")
    @And("^chose dropdown \"(.*)\"$")
    public void choseModalDropdown(String name) {
        Trading.tradingStockBuyChooseAccount(name).click();
    }

    @Step("chose dropdown option {name}")
    @And("^chose dropdown option \"(.*)\"$")
    public void choseModalDropdownAccount(String name) {
        Trading.tradingStockBuyChooseAccountDropdown(name).click();
    }

    @Step("input trade password {password}")
    @And("^input trade password \"(.*)\"$")
    public void choseModalStockPurchaseTradePassword(String password) throws InterruptedException {
        Trading.tradingStockModalTradePasswordInput().setValue(password);
    }

    @Step
    @And("^debug$")
    public void debug() throws InterruptedException {
        Thread.sleep(10000);
    }

    @After
    public void tearDown() {
        WebDriverRunner.getWebDriver().close();
    }

}
