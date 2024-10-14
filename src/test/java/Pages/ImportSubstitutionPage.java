package Pages;

import Helpers.OutputData;
import Helpers.PageActions;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class ImportSubstitutionPage {

    private Page page;
    OutputData outputData = new OutputData();
    PageActions pageActions = new PageActions();

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

    @Step("Проверка отображения и текста пунктов шапки")
    public boolean headerCheck(){
        return pageActions.elementVisibleAndTextCheck(page, headerSelector, outputData.importSubstitutionHeaderText);
    }

    @Step("Проверка отображения и текста пунктов меню категорий")
    public boolean categoriesMenuCheck(){
        return pageActions.elementVisibleAndTextCheck(page, categoriesMenuSelector, outputData.importSubstitutionCategoriesMenuText);
    }

    @Step("Проверка отображения и текста блока категорий")
    public boolean categoriesBlockCheck(){
        return pageActions.elementVisibleAndTextCheck(page, categoriesBlockSelector, outputData.importSubstitutionCategoriesBlockText);
    }

    @Step("Проверка отображения и текста блока «Почему стоит доверить проект по импортозамещению R-Style Softlab»")
    public boolean advantagesBlockCheck(){
        return pageActions.elementVisibleAndTextCheck(page, advantagesBlockSelector, outputData.importSubstitutionAdvantagesBlockText);
    }

    @Step("Проверка отображения и текста блока «Наши клиенты»")
    public boolean ourClientsBlockCheck(){
        return pageActions.elementVisibleAndTextCheck(page, ourClientsBlockSelector, outputData.importSubstitutionOurClientsBlockText);
    }

    @Step("Проверка отображения и текста элемента подвала")
    public boolean footerCheck(){
        return pageActions.elementVisibleAndTextCheck(page, footerSelector, outputData.importSubstitutionFooterText);
    }
}
