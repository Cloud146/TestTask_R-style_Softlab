package Pages;

import Helpers.OutputData;
import Helpers.PageActions;
import Helpers.Waitings;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageElements {

    private WebDriver driver;
    PageActions pageActions = new PageActions();
    Waitings waitings = new Waitings();
    OutputData outputData = new OutputData();

    public PageElements(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Веб элемент: Кнопка принятия Cookie
     */
    @FindBy(xpath = "//button[@class = 'button button--cookie cookieswarn__ok']")
    public WebElement cookieAcceptButton;

    /**
     * Веб элемент: Межстраничное верхнее меню
     */
    @FindBy(xpath = "//div[@class = 'js-menu header__menu-wrapper menu']")
    public WebElement menuWrapper;

    /**
     * Веб элемент: Межстраничная кнопка «Поиск»
     */
    @FindBy(xpath = "//div[@class = 'js-menu-search menu__search menu__search_tablet']")
    public WebElement searchButton;

    /**
     * Веб элемент: Пункт верхнего меню «Решения»
     */
    @FindBy(xpath = "//ul[contains(@class, 'menu__item-submenu_solutions')]/..")
    public WebElement solutionItem;

    /**
     * Веб элемент: Выпадающее меню пункта «Решения»
     */
    @FindBy(xpath = "//ul[contains(@class, 'menu__item-submenu_solutions')]")
    public WebElement solutionSubMenu;

    /**
     * Веб элемент: Пункт «Импортозамещение»выпадающего меню «Решения»
     */
    @FindBy(xpath = "//div[text() = \'Импортозамещение\']/../..")
    public WebElement importSubstitutionButton;


    @Step("Проверка отображения и текста пунктов верхнего меню")
    public boolean menuWrapperCheck(){
        return pageActions.elementVisibleAndTextCheck(menuWrapper, outputData.wrapperMenuText);
    }

    @Step("Проверка отображения кнопки поиска")
    public boolean searchButtonCheck(){
        return searchButton.isDisplayed();
    }

    @Step("Наведение и раскрытие выпадающего меню пункта «Решения»")
    public PageElements extendSolutionSubMenu(){
        pageActions.actionMoveToElement(driver, solutionItem);
        waitings.waitTimeForElement(5, driver, solutionSubMenu);
        return this;
    }

    @Step("Проверка отображения и текста пунктов выпадающего меню пункта «Решения»")
    public boolean solutionSubMenuCheck(){
        return pageActions.elementVisibleAndTextCheck(solutionSubMenu, outputData.wrapperSolutionSubMenuText);
    }


    @Step("Открытие пункта «Импортозамещение»")
    public ImportSubstitutionPage openImportSubstitution(WebDriver driver){
        importSubstitutionButton.click();
        return new ImportSubstitutionPage(driver);
    }

    @Step("Принятие Cookie")
    public PageElements acceptCookie(){
        waitings.waitTimeForElement(10, driver, cookieAcceptButton);
        cookieAcceptButton.click();
        return this;
    }
}
