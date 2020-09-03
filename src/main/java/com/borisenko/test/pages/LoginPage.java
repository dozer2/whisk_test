package com.borisenko.test.pages;


import com.borisenko.test.pages.elements.AuthentificationPopUp;
import com.borisenko.test.pages.elements.Element;
import com.borisenko.test.util.conditions.IsNotExistElement;
import com.borisenko.test.util.selenium.DriverManager;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

/**
 * Created by maxim on 21.04.18.
 */
public class LoginPage extends MainPage {


    @FindBy(css = "[data-testid='authentication-form']")
    private  AuthentificationPopUp authentificationPopUp;

    @FindBy(css = "[data-testid='community-onboarding-continue']")
    private  Element letsStart;

    @FindBy(xpath = ".//span[contains(text(),'You’ve been signed in, welcome back!')]")
    Element welcomeBack;

    public AuthentificationPopUp getAuthentificationPopUp() {
        return authentificationPopUp;
    }

    public void  startFirstCooking(){
        letsStart.click();
        DriverManager.getInstance().checkUntil(new IsNotExistElement(welcomeBack), Duration.ofSeconds(30));
    }

}
