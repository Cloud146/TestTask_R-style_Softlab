package Pages;

import Helpers.OutputData;
import Helpers.PageActions;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.util.Objects;


public class MainPage {

    private Page page;
    OutputData outputData = new OutputData();
    PageActions pageActions = new PageActions();

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
        while (true){
            if (!Objects.equals(page.isVisible(companyLogoSelector), false)){
                return page.isVisible(companyLogoSelector);
            }
        }
    }

    @Step("Проверка отображения шапки главной страницы")
    public boolean headerCheck(){
        return page.isVisible(mainPageHeaderSelector);
    }

    @Step("Проверка отображения и текста кнопки «Заказать консультацию»")
    public boolean callBackButtonCheck(){
        return pageActions.elementVisibleAndTextCheck(page, callbackButtonSelector, outputData.callBackButtonText );
    }
}
