package PlaywtightPages;

import Helpers.PageActions;
import Helpers.Waitings;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;

import java.util.Objects;


public class PageElements {

    private Page page;
    PageActions pageActions = new PageActions();
    Waitings waitings = new Waitings();

    public PageElements(Page page){
        this.page = page;
    }

    /**
     * Селектор веб-элемента кнопки принятия Cookie
     */
    private String cookieAcceptButtonSelector = "//button[@class = 'button button--cookie cookieswarn__ok']";


    /**
     * Селектор веб-элемента межстраничное верхнее меню
     */
    public String menuWrapperSelector = "//div[@class = 'js-menu header__menu-wrapper menu']";


    /**
     * Селектор веб-элемента межстраничная кнопка «Поиск»
     */
    private String searchButtonSelector = "//div[@class = 'js-menu-search menu__search menu__search_tablet']";


    /**
     * Селектор веб-элемента пункт верхнего меню «Решения»
     */
    private String solutionItemSelector = "//ul[contains(@class, 'menu__item-submenu_solutions')]/..";

    /**
     * Селектор веб-элемента выпадающее меню пункта «Решения»
     */
    private String solutionSubMenuSelector = "//ul[contains(@class, 'menu__item-submenu_solutions')]";

    /**
     * Селектор веб-элемента пункт «Импортозамещение»выпадающего меню «Решения»
     */
    private String importSubstitutionButtonSelector = "//div[text() = \'Импортозамещение\']/../..";

    @Step("Получение пунктов верхнего меню текстом")
    public String menuWrapperGetText(){
        return page.innerText(menuWrapperSelector);
    }

    @Step("Проверка отображения кнопки поиска")
    public boolean searchButtonCheck(){
        return page.isVisible(searchButtonSelector);
    }

    @Step("Наведение и раскрытие выпадающего меню пункта «Решения»")
    public PageElements extendSolutionSubMenu() {
        page.hover(solutionItemSelector);
        page.waitForSelector(solutionSubMenuSelector, new Page.WaitForSelectorOptions().setTimeout(5000));
//        page.waitForSelector(solutionSubMenuSelector, new Page.WaitForSelectorOptions()
//                .setTimeout(5000)
//                .setState(WaitForSelectorState.VISIBLE));
        return this;
    }

    @Step("Проверка отображения пунктов выпадающего меню пункта «Решения»")
    public boolean solutionSubMenuCheckText(){
        return page.isVisible(solutionSubMenuSelector);
    }

    @Step("Получение пунктов выпадающего меню пункта «Решения» текстом")
    public String solutionSubMenuGetText(){
        while (true){
            if (!Objects.equals(page.innerText(solutionSubMenuSelector), "")){
                return page.innerText(solutionSubMenuSelector);
            }
        }
    }


    @Step("Открытие пункта «Импортозамещение»")
    public ImportSubstitutionPage openImportSubstitution(Page page){
        page.click(importSubstitutionButtonSelector);
        return new ImportSubstitutionPage(page);
    }

    @Step("Принятие Cookie")
    public PageElements acceptCookie(){
        page.waitForSelector(cookieAcceptButtonSelector, new Page.WaitForSelectorOptions().setTimeout(1000));
        page.click(cookieAcceptButtonSelector);
        return this;
    }
}
