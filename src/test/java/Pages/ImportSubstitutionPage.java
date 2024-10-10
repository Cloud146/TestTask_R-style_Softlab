package Pages;

import Helpers.PageActions;
import Helpers.Waitings;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ImportSubstitutionPage {

    private WebDriver driver;
    PageActions pageActions = new PageActions();

    public ImportSubstitutionPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Веб элемент: Шапка страницы «Импортозамещение»
     */
    @FindBy(xpath = "//section[@class = 'site-header__inner']")
    public WebElement header;

    /**
     * Веб элемент: Меню категорий
     */
    @FindBy(xpath = "//div[@class = 'import-page__wrapper import-page__wrapper--top']")
    public WebElement categoriesMenu;

    /**
     * Веб элемент: Блок категорий «Все производители» с плашками ПО
     */
    @FindBy(xpath = "//div[@class = 'import-page__wrapper import-page__wrapper--top']/..")
    public WebElement categoriesBlock;

    /**
     * Веб элемент: Блок «Почему стоит доверить проект по импортозамещению R-Style Softlab»
     */
    @FindBy(xpath = "//section[@class = 'advantages']")
    public WebElement advantagesBlock;

    /**
     * Веб элемент: Блок «Наши клиенты»
     */
    @FindBy(xpath = "//section[@class = 'our-clients']")
    public WebElement ourClientsBlock;

    /**
     * Веб элемент: Подвал страницы «Импортозамещение» со ссылками
     */
    @FindBy(xpath = "//footer[@class = 'footer']")
    public WebElement footer;

    /**
     * Веб элемент: Кнопка категории «Все производители»
     */
    @FindBy(xpath = "//a[text() = 'Все производители']")
    public WebElement vendorsCategory;

    /**
     * Веб элемент: Кнопка категории «Программное обеспечение»
     */
    @FindBy(xpath = "//a[text() = 'Программное обеспечение']")
    public WebElement softwareCategory;

    /**
     * Веб элемент: Кнопка категории «Оборудование»
     */
    @FindBy(xpath = "//a[text() = 'Оборудование']")
    public WebElement equipmentCategory;

    /**
     * Веб элемент: Ссылка на страницу «RS‑Bank»
     */
    @FindBy(xpath = "//a[text() = 'RS‑Bank']")
    public WebElement rsBankLink;

    @Step("Получение пунктов шапки текстом")
    public String headerGetText(){
        return header.getText();
    }

    @Step("Получение пунктов меню категорий текстом")
    public String categoriesMenuGetText(){
        return categoriesMenu.getText();
    }

    @Step("Наведение на категорию «Программное обеспечение»")
    public ImportSubstitutionPage moveToSoftwareCategory(){
        pageActions.actionMoveToElement(driver, softwareCategory);
        return this;
    }

    @Step("Переход по ссылке на страницу «RS‑Bank»")
    public RSBankPage scrollDownAndClickRsBankLink(WebDriver driver){
        pageActions.actionMoveToElement(driver, rsBankLink);
        rsBankLink.click();
        return new RSBankPage(driver);
    }

    @Step("Получение текста с блока категорий")
    public String categoriesBlockGetText(){
        return categoriesBlock.getText();
    }

    @Step("Получение текста с блока «Почему стоит доверить проект по импортозамещению R-Style Softlab»")
    public String advantagesBlockGetText(){
        return advantagesBlock.getText();
    }

    @Step("Получение текста с блока «Наши клиенты»")
    public String ourClientsBlockGetText(){
        return ourClientsBlock.getText();
    }

    @Step("Получение текста с элемента подвала")
    public String footerGetText(){
        return footer.getText();
    }
}
