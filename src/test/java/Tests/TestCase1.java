package Tests;


import Pages.*;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase1 extends  BaseTest{

    private Page page;
    private MainPage mainPage;
    private PageElements pageElements;
    private ImportSubstitutionPage importSubstitutionPage;
    private RSBankPage rsBankPage;
    private RSBankV6Page rsBankV6Page;

    @BeforeClass
    public void openFirstPage(){
        page = getPage();
    }

    @Step("Шаг 1 - Вход на сайт https://www.softlab.ru/")
    @Test(priority = 1)
    public void mainPageMainElementsCheckTest() throws IOException {
        page.navigate(configurationProvider.getMainPageURL());
        mainPage = new MainPage(page);
        pageElements = new PageElements(page);

        //pageElements.acceptCookie();

        softAssert.assertTrue(mainPage.companyLogoCheck(), "Company logo not found");
        softAssert.assertTrue(pageElements.menuWrapperCheck(), "Menu wrapper not found");
        softAssert.assertTrue(pageElements.searchButtonCheck(), "Search button not found");
        softAssert.assertAll();
    }

    @Step("Шаг 2 - Навести курсор мыши на пункт меню «Решения»")
    @Test(priority = 2)
    public void solutionSubMenuCheckTest() {
        pageElements.extendSolutionSubMenu();
        softAssert.assertTrue(pageElements.solutionSubMenuCheck(), "Solution sub menu not found");
        softAssert.assertAll();
    }

    @Step("Шаг 3 - Перейти в меню «Решения > Импортозамещение»")
    @Test(priority = 3)
    public void importSubstitutionPageMainElementsCheckTest() throws IOException {
        importSubstitutionPage = pageElements.extendSolutionSubMenu()
                .openImportSubstitution(page);

        softAssert.assertEquals(page.url(), configurationProvider.getImportSubstitutionPageURL());

        softAssert.assertTrue(importSubstitutionPage.headerCheck(), "Header not found");
        softAssert.assertTrue(importSubstitutionPage.categoriesMenuCheck(), "Categories menu not found");
        softAssert.assertTrue(importSubstitutionPage.categoriesBlockCheck(), "Categories bloc not found");
        softAssert.assertTrue(importSubstitutionPage.advantagesBlockCheck(), "Advantages bloc not found");
        softAssert.assertTrue(importSubstitutionPage.ourClientsBlockCheck(), "Our clients bloc not found");
        softAssert.assertTrue(importSubstitutionPage.footerCheck(), "Footer not found");
        softAssert.assertAll();
    }

    @Step("Шаг 4 - Навести мышкой на пункт «Программное обеспечение» в меню категорий")
    @Test(priority = 4)
    public void hoverOnCategoryTextAndBackgroundColorCheckTest(){
        importSubstitutionPage.moveToSoftwareCategory();

        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(page, importSubstitutionPage.vendorsCategorySelector), outputData.blackBackgroundAndWhiteText);
        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(page, importSubstitutionPage.softwareCategorySelector), outputData.blackBackgroundAndWhiteText);
        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(page, importSubstitutionPage.equipmentCategorySelector), outputData.whiteBackgroundAndBlackText);

        softAssert.assertAll();
    }

    @Step("Шаг 5 - Отвести курсор мыши с категории «Программное обеспечение»")
    @Test(priority = 5)
    public void categoryTextAndBackgroundColorCheckTest(){
        page.hover(importSubstitutionPage.categoriesMenuSelector);

        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(page, importSubstitutionPage.vendorsCategorySelector), outputData.blackBackgroundAndWhiteText);
        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(page, importSubstitutionPage.softwareCategorySelector), outputData.whiteBackgroundAndBlackText);
        softAssert.assertEquals(pageActions.textAndBackgroundColorFromElement(page, importSubstitutionPage.equipmentCategorySelector), outputData.whiteBackgroundAndBlackText);

        softAssert.assertAll();
    }

    @Step("Шаг 6 - В «подвале» страницы перейти по ссылке «RS-Bank» в разделе «Продукты»")
    @Test(priority = 6)
    public void openRSBankPageTest() throws IOException {
        rsBankPage = importSubstitutionPage.scrollDownAndClickRsBankLink(page);

        Assert.assertEquals(page.url(), configurationProvider.getRSBankPageURL());
    }

    @Step("Шаг 7 - Кликнуть по плашке «RS-Bank V.6»")
    @Test(priority = 7)
    public void openRSBankV6PageTest() throws IOException {
        rsBankV6Page = rsBankPage.clickOnRSBankV6Card(page);

        Assert.assertEquals(page.url(), configurationProvider.getRSBankV6PageURL());
    }

    @Step("Шаг 8 - Кликнуть по плашке «Технические требования к программному и техническому обеспечению RS-Bank»")
    @Test(priority = 8)
    public void openTechnicalDocumentationPagePDFTest() throws IOException {
        Page newPage = page.waitForPopup(() -> {
            rsBankV6Page.clickOnTechnicalDocumentationCard();
        });
        newPage.waitForLoadState();
        Assert.assertEquals(newPage.url(), configurationProvider.getTechnicalDocumentationPDFURL());
    }

    @Step("Шаг 9 - Закрыть вкладку с PDF-файлом")
    @Test(priority = 9)
    public void closeTechnicalDocumentationPagePDFTest() throws IOException {
        Page currentPage = context.pages().get(context.pages().size() - 1);

        currentPage.close();

        Page previousPage = context.pages().get(context.pages().size() - 1);

        Assert.assertEquals(previousPage.url(), configurationProvider.getRSBankV6PageURL());
    }

    @Step("Шаг 10 - Нажать на логотип компании в левом верхнем углу")
    @Test(priority = 10)
    public void clickOnCompanyLogoAndCheckMainElementsTest() throws IOException {
        page.hover(pageElements.menuWrapperSelector);
        mainPage = rsBankV6Page.clickOnCompanyLogo(page);

        softAssert.assertEquals(page.url(), configurationProvider.getMainPageURL());
        softAssert.assertTrue(mainPage.companyLogoCheck(), "Company logo not found");
        softAssert.assertTrue(pageElements.menuWrapperCheck(), "Menu wrapper not found");
        softAssert.assertTrue(pageElements.searchButtonCheck(), "Search button not found");
        softAssert.assertTrue(mainPage.headerCheck(), "Header button not found");
        softAssert.assertTrue(mainPage.callBackButtonCheck(), "Callback button button not found");
        softAssert.assertAll();
    }
}
