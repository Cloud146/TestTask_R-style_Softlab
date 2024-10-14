package Pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;


public class RSBankPage {

    private Page page;

    public RSBankPage(Page page){
        this.page = page;
    }


    /**
     * Селектор для элемента карточка «RS-Bank V.6»
     */
    public String rsBankV6CardSelector = "//a[@href = '/products/rs-bank-v-6/']";


    @Step("Кликнуть на карточку «RS-Bank V.6»")
    public RSBankV6Page clickOnRSBankV6Card(Page page){
        page.click(rsBankV6CardSelector);
        return new RSBankV6Page(page);
    }
}
