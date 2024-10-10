package Tests;

import Pages.*;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase1 extends BaseTest{

    private WebDriver driver;
    private PageElements pageElements;
    private MainPage mainPage;
    private ImportSubstitutionPage importSubstitutionPage;
    private RSBankPage rsBankPage;
    private RSBankV6Page rsBankV6Page;

    @BeforeClass
    public void openFirstPage(){
        driver = getDriver();
    }

    @Step("Шаг 1 - Вход на сайт https://www.softlab.ru/")
    @Test(priority = 1)
    public void mainPageMainElementsCheckTest() throws IOException {
        driver.get(configurationProvider.getMainPageURL());
        mainPage = new MainPage(driver);
        pageElements = new PageElements(driver);

        pageElements.acceptCookie();

        softAssert.assertTrue(mainPage.companyLogoCheck(), "Company logo not found");
        softAssert.assertEquals(pageElements.menuWrapperGetText(), outputData.wrapperMenuText);
        softAssert.assertTrue(pageElements.searchButtonCheck(), "Search button not found");

        softAssert.assertAll();
    }

    @Step("Шаг 2 - Навести курсор мыши на пункт меню «Решения»")
    @Test(priority = 2)
    public void solutionSubMenuCheckTest(){
        pageElements.extendSolutionSubMenu();

        Assert.assertEquals(pageElements.solutionSubMenuGetText(), outputData.wrapperSolutionSubMenuText);
    }

    @Step("Шаг 3 - Перейти в меню «Решения > Импортозамещение»")
    @Test(priority = 3)
    public void importSubstitutionPageMainElementsCheckTest() throws IOException {
        importSubstitutionPage = pageElements.extendSolutionSubMenu()
                .openImportSubstitution(driver);

        softAssert.assertEquals(driver.getCurrentUrl(), configurationProvider.getImportSubstitutionPageURL());

        softAssert.assertEquals(importSubstitutionPage.headerGetText(), outputData.importSubstitutionHeaderText);
        softAssert.assertEquals(importSubstitutionPage.categoriesMenuGetText(), outputData.importSubstitutionCategoriesMenuText);
        softAssert.assertEquals(importSubstitutionPage.categoriesBlockGetText(), outputData.importSubstitutionCategoriesBlockText);
        softAssert.assertEquals(importSubstitutionPage.advantagesBlockGetText(), outputData.ImportSubstitutionAdvantagesBlockText);
        softAssert.assertEquals(importSubstitutionPage.ourClientsBlockGetText(), outputData.ImportSubstitutionOurClientsBlockText);
        softAssert.assertEquals(importSubstitutionPage.footerGetText(), outputData.ImportSubstitutionFooterText);

        softAssert.assertAll();
    }

    @Step("Шаг 4 - Навести мышкой на пункт «Программное обеспечение» в меню категорий")
    @Test(priority = 4)
    public void hoverOnCategoryTextAndBackgroundColorCheckTest(){
        importSubstitutionPage.moveToSoftwareCategory();

        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(importSubstitutionPage.vendorsCategory), outputData.blackBackgroundAndWhiteText);
        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(importSubstitutionPage.softwareCategory), outputData.blackBackgroundAndWhiteText);
        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(importSubstitutionPage.equipmentCategory), outputData.whiteBackgroundAndBlackText);

        softAssert.assertAll();
    }

    @Step("Шаг 5 - Отвести курсор мыши с категории «Программное обеспечение»")
    @Test(priority = 5)
    public void categoryTextAndBackgroundColorCheckTest(){
        pageActions.actionMoveToElement(driver, importSubstitutionPage.categoriesMenu);

        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(importSubstitutionPage.vendorsCategory), outputData.blackBackgroundAndWhiteText);
        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(importSubstitutionPage.softwareCategory), outputData.whiteBackgroundAndBlackText);
        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(importSubstitutionPage.equipmentCategory), outputData.whiteBackgroundAndBlackText);

        softAssert.assertAll();
    }

    @Step("Шаг 6 - В «подвале» страницы перейти по ссылке «RS-Bank» в разделе «Продукты»")
    @Test(priority = 6)
    public void openRSBankPageTest() throws IOException {
        rsBankPage = importSubstitutionPage.scrollDownAndClickRsBankLink(driver);

        Assert.assertEquals(driver.getCurrentUrl(), configurationProvider.getRSBankPageURL());
    }

    @Step("Шаг 7 - Кликнуть по плашке «RS-Bank V.6»")
    @Test(priority = 7)
    public void openRSBankV6PageTest() throws IOException {
        rsBankV6Page = rsBankPage.clickOnRSBankV6Card(driver);

        Assert.assertEquals(driver.getCurrentUrl(), configurationProvider.getRSBankV6PageURL());
    }

    @Step("Шаг 8 - Кликнуть по плашке «Технические требования к программному и техническому обеспечению RS-Bank»")
    @Test(priority = 8)
    public void openTechnicalDocumentationPagePDFTest() throws IOException {
        rsBankV6Page.clickOnTechnicalDocumentationCard();
        pageActions.getLastTab(driver);

        Assert.assertEquals(driver.getCurrentUrl(), configurationProvider.getTechnicalDocumentationPDFURL());
    }

    @Step("Шаг 9 - Закрыть вкладку с PDF-файлом")
    @Test(priority = 9)
    public void closeTechnicalDocumentationPagePDFTest() throws IOException {
        driver.close();
        pageActions.getLastTab(driver);

        Assert.assertEquals(driver.getCurrentUrl(), configurationProvider.getRSBankV6PageURL());
    }

    @Step("Шаг 10 - Нажать на логотип компании в левом верхнем углу")
    @Test(priority = 10)
    public void clickOnCompanyLogoAndCheckMainElementsTest() throws IOException {
        pageActions.actionMoveToElement(driver, pageElements.menuWrapper);
        mainPage = rsBankV6Page.clickOnCompanyLogo(driver);

        softAssert.assertEquals(driver.getCurrentUrl(), configurationProvider.getMainPageURL());
        softAssert.assertTrue(mainPage.companyLogoCheck(), "Company logo not found");
        softAssert.assertEquals(pageElements.menuWrapperGetText(), outputData.wrapperMenuText);
        softAssert.assertTrue(pageElements.searchButtonCheck(), "Search button not found");
        softAssert.assertTrue(mainPage.headerCheck());
        softAssert.assertEquals(mainPage.callBackButtonGetText(), "Заказать консультацию");
        softAssert.assertAll();
    }
}
