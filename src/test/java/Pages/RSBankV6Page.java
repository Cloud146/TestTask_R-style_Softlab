package Pages;

import Helpers.PageActions;
import Helpers.Waitings;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RSBankV6Page {

    private WebDriver driver;
    PageActions pageActions = new PageActions();
    Waitings waitings = new Waitings();

    public RSBankV6Page(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Веб элемент: Карточка «Технические требования к программному и техническому обеспечению RS-Bank V.6»
     */
    @FindBy(xpath = "//div[contains(text(), 'Технические требования')]/..")
    public WebElement technicalDocumentationCard;

    /**
     * Веб элемент: Кнопка логотипа компании
     */
    @FindBy(xpath = "//div[@class = 'header__nav-top']")
    public WebElement companyLogoButton;

    @Step("Кликнуть на карточку «Технические требования к программному и техническому обеспечению RS-Bank V.6»")
    public void clickOnTechnicalDocumentationCard(){
        pageActions.actionMoveToElement(driver, technicalDocumentationCard);
        technicalDocumentationCard.click();
    }

    @Step("Кликнуть на кнопку логотипа компании")
    public MainPage clickOnCompanyLogo(WebDriver driver){
        companyLogoButton.click();
        return new MainPage(driver);
    }

}
