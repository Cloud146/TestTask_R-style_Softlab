package Cucumber.Steps;

import Helpers.ConfigurationProvider;
import Helpers.OutputData;
import Helpers.PageActions;
import Pages.*;
import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Arrays;

public class Steps {
    private Playwright playwright;
    private Browser browser;
    public BrowserContext context;
    private Page page;

    SoftAssert softAssert = new SoftAssert();
    ConfigurationProvider configurationProvider = new ConfigurationProvider();
    OutputData outputData = new OutputData();
    PageActions pageActions = new PageActions();

    private MainPage mainPage;
    private PageElements pageElements;
    private ImportSubstitutionPage importSubstitutionPage;
    private RSBankPage rsBankPage;
    private RSBankV6Page rsBankV6Page;

    @Given("У пользователя открыт браузер")
    public void browserSetUp() throws IOException {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(Arrays.asList(
                        "--ignore-certificate-errors",
                        "--disable-popup-blocking",
                        "--remote-allow-origins=**",
                        "--disable-extensions",
                        "--disable-notifications",
                        "--disable-infobars",
                        "--no-default-browser-check",
                        "--disable-first-run-ui",
                        "--disable-features=AutofillAssistant",
                        "--disable-features=TranslateUI",
                        "--disable-features=ChromeWhatsNewUI",
                        "--disable-features=ChromeTips",
                        "--no-first-run"
                ))
        );

        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(configurationProvider.getScreenWidth(), configurationProvider.getScreenHeight())
        );

        page = context.newPage();
        page.setViewportSize(configurationProvider.getScreenWidth(), configurationProvider.getScreenHeight());
    }

    @After
    public void browserTearDown() {
        softAssert.assertAll();
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @When("Пользователь открывает страницу сайта")
    public void openMainPage() throws IOException {
        page.navigate(configurationProvider.getMainPageURL());
        mainPage = new MainPage(page);
        pageElements = new PageElements(page);
    }

    @Then("Отображается логотип компании")
    public void companyLogoCheck() {
        softAssert.assertTrue(mainPage.companyLogoCheck(), "Company logo not found");
    }

    @And("Отображается меню с пунктами")
    public void menuWrapperCheck() {
        softAssert.assertTrue(pageElements.menuWrapperCheck(), "Menu wrapper not found");
    }

    @And("Отображается кнопка поиска")
    public void searchButtonCheck() {
        softAssert.assertTrue(pageElements.searchButtonCheck(), "Search button not found");
    }

    @When("Пользователь наводит курсор на меню «Решения»")
    public void extendSolutionSubMenu() {
        pageElements.extendSolutionSubMenu();
    }

    @Then("Отображается подменю «Решения»")
    public void solutionSubMenuCheck() {
        softAssert.assertTrue(pageElements.solutionSubMenuCheck(), "Solution sub menu not found");
    }

    @When("Пользователь нажимает на пункт подменю «Импортозамещение»")
    public void openImportSubstitutionPage() {
        pageElements.extendSolutionSubMenu();
        importSubstitutionPage = pageElements.extendSolutionSubMenu()
                .openImportSubstitution(page);
    }

    @Then("Открывается страница «Импортозамещение ПО»")
    public void checkImportSubstitutionPageURL() throws IOException {
        softAssert.assertEquals(page.url(), configurationProvider.getImportSubstitutionPageURL());
    }

    @And("Отображается шапка страницы")
    public void headerCheck() {
        softAssert.assertTrue(importSubstitutionPage.headerCheck(), "Header not found");
    }

    @And("Отображается меню категорий")
    public void categoriesMenuCheck() {
        softAssert.assertTrue(importSubstitutionPage.categoriesMenuCheck(), "Categories menu not found");
    }

    @And("Отображается блок «Все производители»")
    public void categoriesBlockCheck() {
        softAssert.assertTrue(importSubstitutionPage.categoriesBlockCheck(), "Categories bloc not found");
    }

    @And("Отображается блок «Почему стоит доверить проект по импортозамещению R-Style Softlab»")
    public void advantagesBlockCheck() {
        softAssert.assertTrue(importSubstitutionPage.advantagesBlockCheck(), "Advantages bloc not found");
    }

    @And("Отображается блок «Наши клиенты»")
    public void ourClientsBlockCheck() {
        softAssert.assertTrue(importSubstitutionPage.ourClientsBlockCheck(), "Our clients bloc not found");
    }

    @And("Отображается «подвал» страницы")
    public void footerCheck() {
        softAssert.assertTrue(importSubstitutionPage.footerCheck(), "Footer not found");
    }

    @Given("Пользователь открывает страницу сайта «Импортозамещение»")
    public void openImportSubstitutionURL() throws IOException {
        page.navigate(configurationProvider.getImportSubstitutionPageURL());
        importSubstitutionPage = new ImportSubstitutionPage(page);
    }

    @When("Пользователь наводит курсор на пункт «Программное обеспечение» в меню категорий")
    public void moveToSoftwareCategory() {
        importSubstitutionPage.moveToSoftwareCategory();
    }

    @Then("Пункт меню меняет цвет фона с белого на черный, а цвет текста на белый")
    public void textAndBackgroundOnHoverSoftwareCategoryCheck() {
        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(page, importSubstitutionPage.softwareCategorySelector), outputData.blackBackgroundAndWhiteText);
    }

    @And("Остальные пункты остаются без изменений")
    public void textAndBackgroundOthersCategoriesCheck() {
        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(page, importSubstitutionPage.vendorsCategorySelector), outputData.blackBackgroundAndWhiteText);
        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(page, importSubstitutionPage.equipmentCategorySelector), outputData.whiteBackgroundAndBlackText);

    }

    @Given("Пользователь навёл курсор на пункт «Программное обеспечение» в меню категорий")
    public void openURLAndMoveToSoftwareCategory() throws IOException {
        openImportSubstitutionURL();
        moveToSoftwareCategory();
    }

    @When("Пользователь отводит курсор с пункта «Программное обеспечение» в меню категорий")
    public void moveAwayFromSoftwareCategory() {
        page.hover(importSubstitutionPage.categoriesMenuSelector);
    }

    @Then("Пункт меню изменил цвет фона с чёрного на белый, а цвет текста на чёрный")
    public void textAndBackgroundSoftwareCategoryCheck() {
        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(page, importSubstitutionPage.softwareCategorySelector), outputData.whiteBackgroundAndBlackText);
    }

    @When("Пользователь опускается в подвал страницы и открывает ссылку «RS-Bank»")
    public void scrollDownAndClickRsBankLin() {
        rsBankPage = importSubstitutionPage.scrollDownAndClickRsBankLink(page);
    }

    @Then("Открывается страница «Автоматизированные банковские системы АБС»")
    public void checkRSBankPageURL() throws IOException {
        softAssert.assertEquals(page.url(), configurationProvider.getRSBankPageURL());
    }

    @Given("Пользователь открывает страницу сайта «Автоматизированные банковские системы АБС»")
    public void openRSBankPage() throws IOException {
        page.navigate(configurationProvider.getRSBankPageURL());
        rsBankPage = new RSBankPage(page);
    }

    @When("Пользователь кликает по плашке «RS-Bank V.6»")
    public void clickOnRSBankV6Card() {
        rsBankV6Page = rsBankPage.clickOnRSBankV6Card(page);
    }

    @Then("Открывается страница «Автоматизированная банковская система для многофилиальных банков RS‑Bank V.6»")
    public void checkRSBankV6PageURL() throws IOException {
        softAssert.assertEquals(page.url(), configurationProvider.getRSBankV6PageURL());
    }

    @Given("Пользователь открывает страницу сайта «Автоматизированная банковская система для многофилиальных банков RS‑Bank V.6»")
    public void openRSBankV6Page() throws IOException {
        page.navigate(configurationProvider.getRSBankV6PageURL());
        rsBankV6Page = new RSBankV6Page(page);
    }

    Page newPage;

    @When("Пользователь кликает по плашке «Технические требования к программному и техническому обеспечению RS-Bank»")
    public void clickOnTechnicalDocumentationCard() {
        newPage = page.waitForPopup(() -> {
            rsBankV6Page.clickOnTechnicalDocumentationCard();
        });
        newPage.waitForLoadState();
    }

    @Then("В новой вкладке открывается содержимое PDF-файла  «Требования к техническому и программному обеспечению RS-Bank V.6»")
    public void checkTechnicalDocumentationPDFURL() throws IOException {
        softAssert.assertEquals(newPage.url(), configurationProvider.getTechnicalDocumentationPDFURL());
    }

    @Given("Пользователь находится на новой вкладке PDF-файла  «Требования к техническому и программному обеспечению RS-Bank V.6»")
    public void openTechnicalDocumentationCard() throws IOException {
        openRSBankV6Page();
        clickOnTechnicalDocumentationCard();
    }

    Page previousPage;

    @When("Пользователь закрывает эту вкладку")
    public void пользовательЗакрываетЭтуВкладку() {
        Page currentPage = context.pages().get(context.pages().size() - 1);

        currentPage.close();

        previousPage = context.pages().get(context.pages().size() - 1);
    }

    @Then("Отображается страница «Автоматизированная банковская система для многофилиальных банков RS‑Bank V.6»")
    public void checkPreviousPage() throws IOException {
        softAssert.assertEquals(previousPage.url(), configurationProvider.getRSBankV6PageURL());
    }

    @When("Пользователь кликает по логотипу компании в левом верхнем углу")
    public void clickOnCompanyLogo() {
        mainPage = rsBankV6Page.clickOnCompanyLogo(page);
        pageElements = new PageElements(page);
    }

    @Then("Открывается страница главная страница сайта")
    public void checkMainPageURL() throws IOException {
        softAssert.assertEquals(page.url(), configurationProvider.getMainPageURL());
    }

    @And("Отображается шапка страницы с кнопкой «Заказать консультацию»")
    public void callBackButtonCheck() {
        softAssert.assertTrue(mainPage.callBackButtonCheck(), "Callback button button not found");
    }
}
