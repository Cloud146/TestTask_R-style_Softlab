package PlaywtightPages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;


public class MainPage {

    private Page page;

    public MainPage(Page page){
        this.page = page;
    }

    /**
     * Селектор для логотипа компании
     */
    private String companyLogoSelector = "//span[@class= 'header__logotype']";


    /**
     * Селектор для шапки главной страницы
     */
    private String mainPageHeaderSelector = "//section[@class = 'site-header__inner']";

    /**
     * Селектор для кнопки «Заказать консультацию»
     */
    private String  callbackButtonSelector = "//a[@class = 'button']";

    @Step("Проверка отображения логотипа компании")
    public boolean companyLogoCheck(){
        return page.isVisible(companyLogoSelector);
    }

    @Step("Проверка отображения шапки главной страницы")
    public boolean headerCheck(){
        return page.isVisible(mainPageHeaderSelector);
    }

    @Step("Получение текста кнопки «Заказать консультацию»")
    public String callBackButtonGetText(){
        return page.innerText(callbackButtonSelector);
    }
}
