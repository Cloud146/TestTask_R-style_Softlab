package PlaywtightPages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;


public class RSBankV6Page {

    private Page page;

    public RSBankV6Page(Page page){
        this.page = page;
    }

    /**
     * Селектор для элемента карточка «Технические требования к программному и техническому обеспечению RS-Bank V.6»
     */
    public String technicalDocumentationCardSelector = "//div[contains(text(), 'Технические требования')]/..";

    /**
     * Селектор для элемента кнопка логотипа компании
     */
    public String companyLogoButtonSelector = "//div[@class = 'header__nav-top']";


    @Step("Кликнуть на карточку «Технические требования к программному и техническому обеспечению RS-Bank V.6»")
    public void clickOnTechnicalDocumentationCard(){
        page.hover(technicalDocumentationCardSelector);
        page.click(technicalDocumentationCardSelector);
    }

    @Step("Кликнуть на кнопку логотипа компании")
    public MainPage clickOnCompanyLogo(Page page){
        page.click(companyLogoButtonSelector);
        return new MainPage(page);
    }
}
