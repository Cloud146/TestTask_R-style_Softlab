package Tests;

import Pages.ImportSubstitutionPage;
import Pages.MainPage;
import Pages.PageElements;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class MainPageTest extends BaseTest {

    private WebDriver driver;
    private MainPage mainPage;
    private PageElements pageElements;
    private ImportSubstitutionPage importSubstitutionPage;

    @BeforeClass
    public void openHomePage() throws IOException {
        driver = getDriver();
        mainPage = new MainPage(driver);
        pageElements = new PageElements(driver);
    }


    @Step("Шаг 1 - Вход на сайт https://www.softlab.ru/")
    @Test(priority = 1)
    public void mainPageMainElementsCheckTest() throws IOException {
        driver.get(configurationProvider.getMainPageURL());
        mainPage = new MainPage(driver);
        pageElements = new PageElements(driver);

        pageElements.acceptCookie();

        softAssert.assertTrue(mainPage.companyLogoCheck(), "Company logo not found");
        softAssert.assertTrue(pageElements.menuWrapperCheck(), "Menu wrapper not found");
        softAssert.assertTrue(pageElements.searchButtonCheck(), "Search button not found");
    }

    @Step("Шаг 2 - Навести курсор мыши на пункт меню «Решения»")
    @Test(priority = 2)
    public void solutionSubMenuCheckTest(){
        pageElements.extendSolutionSubMenu();

        Assert.assertTrue(pageElements.solutionSubMenuCheck(), "Solution submenu not found");
    }

    @Step("Шаг 3 - Перейти в меню «Решения > Импортозамещение»")
    @Test(priority = 3)
    public void importSubstitutionPageMainElementsCheckTest() throws IOException {
        importSubstitutionPage = pageElements.extendSolutionSubMenu()
                .openImportSubstitution(driver);

        softAssert.assertEquals(driver.getCurrentUrl(), configurationProvider.getImportSubstitutionPageURL());
    }
}
