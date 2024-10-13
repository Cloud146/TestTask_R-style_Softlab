package PlaywtightPages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class ImportSubstitutionPage {

    private Page page;

    public ImportSubstitutionPage(Page page){
        this.page = page;
    }

    /**
     * Селектор для элемента шапка страницы «Импортозамещение»
     */
    public String headerSelector = "//section[@class = 'site-header__inner']";

    /**
     * Селектор для элемента меню категорий
     */
    public String categoriesMenuSelector = "//div[@class = 'import-page__wrapper import-page__wrapper--top']";

    /**
     * Селектор для элемента блок категорий «Все производители» с плашками ПО
     */
    public String categoriesBlockSelector = "//div[@class = 'import-page__wrapper import-page__wrapper--top']/..";

    /**
     * Селектор для элемента блок «Почему стоит доверить проект по импортозамещению R-Style Softlab»
     */
    public String advantagesBlockSelector = "//section[@class = 'advantages']";

    /**
     * Селектор для элемента блок «Наши клиенты»
     */
    public String ourClientsBlockSelector = "//section[@class = 'our-clients']";

    /**
     * Селектор для элемента подвал страницы «Импортозамещение» со ссылками
     */
    public String footerSelector = "//footer[@class = 'footer']";

    /**
     * Селектор для элемента кнопка категории «Все производители»
     */
    public String vendorsCategorySelector = "//a[text() = 'Все производители']";

    /**
     * Селектор для элемента кнопка категории «Программное обеспечение»
     */
    public String softwareCategorySelector = "//a[text() = 'Программное обеспечение']";

    /**
     * Селектор для элемента кнопка категории «Оборудование»
     */
    public String equipmentCategorySelector = "//a[text() = 'Оборудование']";

    /**
     * Селектор для элемента ссылка на страницу «RS‑Bank»
     */
    public String rsBankLinkSelector = "//a[text() = 'RS‑Bank']";


    @Step("Получение пунктов шапки текстом")
    public String headerGetText(){
        return page.innerText(headerSelector);
    }

    @Step("Получение пунктов меню категорий текстом")
    public String categoriesMenuGetText(){
        return page.innerText(categoriesMenuSelector);
    }

    @Step("Наведение на категорию «Программное обеспечение»")
    public ImportSubstitutionPage moveToSoftwareCategory(){
        page.hover(softwareCategorySelector);
        return this;
    }

    @Step("Переход по ссылке на страницу «RS‑Bank»")
    public RSBankPage scrollDownAndClickRsBankLink(Page page){
        page.hover(rsBankLinkSelector);
        page.click(rsBankLinkSelector);
        return new RSBankPage(page);
    }

    @Step("Получение текста с блока категорий")
    public String categoriesBlockGetText(){
        return page.innerText(categoriesBlockSelector);
    }

    @Step("Получение текста с блока «Почему стоит доверить проект по импортозамещению R-Style Softlab»")
    public String advantagesBlockGetText(){
        return page.innerText(advantagesBlockSelector);
    }

    @Step("Получение текста с блока «Наши клиенты»")
    public String ourClientsBlockGetText(){
        return page.innerText(ourClientsBlockSelector);
    }

    @Step("Получение текста с элемента подвала")
    public String footerGetText(){
        return page.innerText(footerSelector);
    }
}
