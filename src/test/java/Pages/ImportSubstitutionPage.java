package Pages;

import Helpers.OutputData;
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
    OutputData outputData = new OutputData();
    Waitings waitings = new Waitings();

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

    @Step("Проверка отображения и текста шапки страницы")
    public boolean headerCheck(){
        return pageActions.elementVisibleAndTextCheck(header, outputData.importSubstitutionHeaderText);
    }

    @Step("Проверка отображения и текста пунктов меню категорий")
    public boolean categoriesMenuCheck(){
        return pageActions.elementVisibleAndTextCheck(categoriesMenu, outputData.importSubstitutionCategoriesMenuText);
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


    @Step("Проверка отображения и текста блока категорий")
    public boolean categoriesBlockCheck(){
        return pageActions.elementVisibleAndTextCheck(categoriesBlock, outputData.importSubstitutionCategoriesBlockText);
    }


    @Step("Проверка отображения и текста блока «Почему стоит доверить проект по импортозамещению R-Style Softlab»")
    public boolean advantagesBlockCheck(){
        return pageActions.elementVisibleAndTextCheck(advantagesBlock, outputData.importSubstitutionAdvantagesBlockText);
    }

    @Step("Проверка отображения и текста блока «Наши клиенты")
    public boolean ourClientsBlockCheck(){
        return pageActions.elementVisibleAndTextCheck(ourClientsBlock, outputData.importSubstitutionOurClientsBlockText);
    }

    @Step("Проверка отображения и текста элемента подвала")
    public boolean footerCheck(){
        waitings.waitForVisibility(driver, footer, 5);
        return pageActions.elementVisibleAndTextCheck(footer, outputData.importSubstitutionFooterText);
    }
}
