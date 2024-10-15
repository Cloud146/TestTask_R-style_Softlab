package Helpers;

import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import java.util.Objects;

public class PageActions {

    @Step("Получение кода цвета заднего фона и текста элемента")
    public String textAndBackgroundColorFromElement(Page page, String elementSelector) {
        return (String) page.evaluate("element => window.getComputedStyle(element).color + '; ' + window.getComputedStyle(element).backgroundColor",
                page.querySelector(elementSelector));
    }

    @Step("Проверка отображения и совпадения текста элемента")
    public boolean elementVisibleAndTextCheck(Page page, String selector, String outputData) {
        if (page.isVisible(selector)) {
            String actualText = "";
            int attempts = 0;

            while (attempts < 5) {  // Ограничиваем число попыток до 5
                if (!Objects.equals(page.innerText(selector), "")) {
                    actualText = page.innerText(selector);
                    break;
                }
                attempts++;
            }
            return actualText.equals(outputData);
        }
        return false;
    }

    @Attachment(value = "{screenshotName}", type = "image/png")
    @Step("Сделать скриншот страницы")
    public static byte[] takeScreenshot(Page page, String screenshotName) {
        return page.screenshot();
    }
}
