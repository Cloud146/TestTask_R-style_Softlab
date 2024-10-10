package Pages;

import Helpers.PageActions;
import Helpers.Waitings;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private WebDriver driver;
    PageActions pageActions = new PageActions();
    Waitings waitings = new Waitings();

    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Веб элемент: Логотип компании
     */
    @FindBy(xpath = "//span[@class= 'header__logotype']")
    public WebElement companyLogo;

    /**
     * Веб элемент: Шапка главной страницы
     */
    @FindBy(xpath = "//section[@class = 'site-header__inner']")
    public WebElement mainPageHeader;

    /**
     * Веб элемент: Кнопка «Заказать консультацию»
     */
    @FindBy(xpath = "//a[@class = 'button']")
    public WebElement callbackButton;

    @Step("Проверка отображения логотипа компании")
    public boolean companyLogoCheck(){
        return companyLogo.isDisplayed();
    }

    @Step("Проверка отображения шапки главной страницы")
    public boolean headerCheck(){
        return mainPageHeader.isDisplayed();
    }

    @Step("Получение текста кнопки «Заказать консультацию»")
    public String callBackButtonGetText(){
        return callbackButton.getText();
    }
}


