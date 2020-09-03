package com.borisenko.test.steps;

import com.borisenko.test.pages.HomePage;

/**
 * Created by maxim on 21.04.18.
 */
public class HomePageSteps extends MainPageSteps{

    HomePage homePage;

    public HomePageSteps(HomePage mainPage) {
        super(mainPage);
        this.homePage = mainPage;
    }



    @Override
    public HomePageSteps refreshPage() {
        driverManager.initPage(homePage);
        return this;
    }
}
