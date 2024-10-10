package Tests;

import Pages.ImportSubstitutionPage;
import Pages.RSBankPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ImportSubstitutionPageTest extends BaseTest {

    private WebDriver driver;
    private ImportSubstitutionPage importSubstitutionPage;
    private RSBankPage rsBankPage;

    @BeforeClass
    public void openImportSubstitutionPage() throws IOException {
        driver = getDriver();
        //driver.get(configurationProvider.getImportSubstitutionPageURL());
        importSubstitutionPage = new ImportSubstitutionPage(driver);
    }


    @Step("Шаг 3.5 -Проверка основных элементов страницы «Импортозамещение»")
    @Test(priority = 3)
    public void importSubstitutionPageMainElementsCheckTest() {
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
}
