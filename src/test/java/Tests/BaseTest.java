package Tests;

import Helpers.ConfigurationProvider;
import Helpers.OutputData;
import Helpers.PageActions;
import io.qameta.allure.Description;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;
    SoftAssert softAssert = new SoftAssert();
    ConfigurationProvider configurationProvider = new ConfigurationProvider();
    OutputData outputData = new OutputData();
    PageActions pageActions = new PageActions();

    public WebDriver getDriver(){
        return driver;
    }

    @Description("Открытие браузера с соответствующими настройками")
    @BeforeTest(enabled = true)
    public void browserSetUp(ITestContext context) throws IOException, IllegalAccessException {
        driver = new ChromeDriver(new ChromeOptions()
                .addArguments("--start-maximized")
                .addArguments("--ignore-certificate-errors")
                .addArguments("--disable-popup-blocking")
                .addArguments("--remote-allow-origins=**")
                .addArguments("--disable-extensions")
                .addArguments("--disable-notifications")
                .addArguments("--disable-infobars")
                .addArguments("--no-default-browser-check")
                .addArguments("--disable-first-run-ui")
                .addArguments("--disable-features=AutofillAssistant")
                .addArguments("--disable-features=TranslateUI")
                .addArguments("--disable-features=ChromeWhatsNewUI")
                .addArguments("--disable-features=ChromeTips")
                .addArguments("--no-first-run"));
        driver.manage().window().setSize(new Dimension(configurationProvider.getScreenWidth(), configurationProvider.getScreenHeight()));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Description("Закрытие браузера/Проверка выполнения теста")
    @AfterTest
    public void browserTearDown(){
        if (driver != null) {
            driver.quit();
        }
        softAssert.assertAll();
    }
}
