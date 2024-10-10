package Tests;

import Pages.MainPage;
import Pages.PageElements;
import Pages.RSBankV6Page;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RSBankPageV6Test extends BaseTest{

    private WebDriver driver;
    private RSBankV6Page rsBankV6Page;
    private MainPage mainPage;
    private PageElements pageElements;

    @BeforeClass
    public void openRSBankV6Page() throws IOException {
        driver = getDriver();
        driver.get(configurationProvider.getRSBankV6PageURL());
        rsBankV6Page = new RSBankV6Page(driver);
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
