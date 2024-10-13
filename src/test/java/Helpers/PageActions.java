package Helpers;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PageActions {

    public Actions actions;


    @Step("Наведение курсора мыши на элемент")
    public void actionMoveToElement(WebDriver driver, WebElement element){
        actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    @Step("Получение кода цвета заднего фона и текста элемента")
    public String textAndBackgroundColorFromElement(WebElement element){
        return element.getCssValue("color") +"; " +element.getCssValue("background-color");
    }

    @Step("Переключение на последнюю вкладку браузера")
    public void getLastTab(WebDriver driver){
        for (String lastTab : driver.getWindowHandles()) {
            driver.switchTo().window(lastTab);
        }
    }

    @Step("Получение кода цвета заднего фона и текста элемента")
    public String textAndBackgroundColorFromElement1(Page page, String elementSelector) {
        // Выполняем JavaScript для получения цвета текста и фона
        return (String) page.evaluate("element => window.getComputedStyle(element).color + '; ' + window.getComputedStyle(element).backgroundColor",
                page.querySelector(elementSelector));
    }

    @Step("Переключение на последнюю вкладку браузера")
    public void getLastTab1(BrowserContext context, Page page) {
        int lastPageIndex = context.pages().size() - 1;

        page = context.pages().get(lastPageIndex);

    }
}
