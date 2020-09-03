package com.borisenko.test.steps;

import com.borisenko.test.pages.MainPage;
import com.borisenko.test.pages.ShoppingListPage;

/**
 * Created by maxim on 21.04.18.
 */
public class MainPageSteps extends BaseSteps<MainPageSteps>{

    MainPage mainPage ;

    public MainPageSteps(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public  ShoppingListPageSteps openShoppingTab()
    {
        mainPage.clickToLinkOnSecondTabPanel();
        mainPage.waitLoadPage();
        ShoppingListPage shoppingListPage= new ShoppingListPage();
        driverManager.initPage(shoppingListPage);
        return new ShoppingListPageSteps(shoppingListPage);
    }

    @Override
    public MainPageSteps refreshPage() {
        driverManager.initPage(mainPage);
        return this;
    }
}
