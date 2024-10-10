package Tests;

import Pages.ImportSubstitutionPage;
import Pages.RSBankPage;
import Pages.RSBankV6Page;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class RSBankPageTest extends BaseTest{

    private WebDriver driver;
    private RSBankPage rsBankPage;
    private RSBankV6Page rsBankV6Page;

    @BeforeMethod
    public void openRSBankPage() throws IOException {
        driver = getDriver();
        driver.get(configurationProvider.getRSBankPageURL());
        rsBankPage = new RSBankPage(driver);
    }

    @Step("Шаг 7 - Кликнуть по плашке «RS-Bank V.6»")
    @Test(priority = 7)
    public void openRSBankV6PageTest() throws IOException {
        rsBankV6Page = rsBankPage.clickOnRSBankV6Card(driver);

        Assert.assertEquals(driver.getCurrentUrl(), configurationProvider.getRSBankV6PageURL());
    }

}
