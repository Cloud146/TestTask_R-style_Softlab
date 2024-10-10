package Pages;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RSBankPage {

    private WebDriver driver;

    public RSBankPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Веб элемент: Карточка «RS-Bank V.6»
     */
    @FindBy(xpath = "//a[@href = '/products/rs-bank-v-6/']")
    public WebElement rsBankV6Card;

    @Step("Кликнуть на карточку «RS-Bank V.6»")
    public RSBankV6Page clickOnRSBankV6Card(WebDriver driver){
        rsBankV6Card.click();
        return new RSBankV6Page(driver);
    }
}
