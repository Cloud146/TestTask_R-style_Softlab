package Helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PageActions {

    public JavascriptExecutor jse;
    public Actions actions;
    public Waitings waitings = new Waitings();


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

    @Step("Проверка отображения и совпадения текста элемента")
    public boolean elementVisibleAndTextCheck(WebElement element, String text){
        if (element.isDisplayed())
            return element.getText().equals(text);
        return false;
    }
}
