package com.borisenko.test.steps;

import com.borisenko.test.pages.HomePage;
import com.borisenko.test.pages.LoginPage;
import com.borisenko.test.pages.elements.AuthentificationPopUp;
import com.borisenko.test.util.PropertyLoader;
import com.borisenko.test.util.conditions.WaitPageLoad;
import com.borisenko.test.util.selenium.DriverManager;

import java.time.Duration;

/**
 * Created by maxim on 21.04.18.
 */
public class LoginSteps extends BaseSteps<LoginSteps>{

    LoginPage loginPage = new LoginPage();


    public   LoginSteps openLoginForm()
    {
        driverManager.getWebDriver().get(PropertyLoader.loadProperty("site.url") );
        DriverManager.getInstance().checkUntil(new WaitPageLoad(), Duration.ofSeconds(30));
        refreshPage();
        return this;
    }

   public HomePageSteps firstLogin(String login,String password){
        AuthentificationPopUp popUp= loginPage.getAuthentificationPopUp();
        popUp.setEmailOrPhoneNumber(login);
        popUp.clickAuthButton();
        popUp.setPassword(password);
        popUp.clickLogInButton();
        DriverManager.getInstance().checkUntil(new WaitPageLoad(), Duration.ofSeconds(30));
        refreshPage();
        loginPage.startFirstCooking();
        HomePage homePage= new HomePage();
        driverManager.initPage(homePage);
        return new HomePageSteps(homePage);
   }

    @Override
    public LoginSteps refreshPage() {
        driverManager.initPage(loginPage);
        return this;
    }
}
