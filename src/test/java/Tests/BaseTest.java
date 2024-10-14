package Tests;

import Helpers.ConfigurationProvider;
import Helpers.OutputData;
import Helpers.PageActions;
import com.microsoft.playwright.*;
import io.qameta.allure.Description;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Arrays;

public class BaseTest {

    private Playwright playwright;
    private Browser browser;
    public BrowserContext context;
    private Page page;

    SoftAssert softAssert = new SoftAssert();
    ConfigurationProvider configurationProvider = new ConfigurationProvider();
    OutputData outputData = new OutputData();
    PageActions pageActions = new PageActions();

    public Page getPage() {
        return page;
    }

    @Description("Открытие браузера с соответствующими настройками")
    @BeforeTest
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

    @Description("Закрытие браузера/Проверка выполнения теста")
    @AfterTest
    public void browserTearDown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
