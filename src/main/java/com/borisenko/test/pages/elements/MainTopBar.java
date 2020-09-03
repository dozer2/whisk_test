package com.borisenko.test.pages.elements;

import com.borisenko.test.util.conditions.WaitPageLoad;
import com.borisenko.test.util.selenium.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class MainTopBar  extends Element{

    @FindBy(css = "[data-testid='shopping-list-nav-link']")
    private  Element shoppingListLink;

    public MainTopBar(WebElement webElement) {
        super(webElement);
    }

    public void clickToLinkOnSecondTabPanel()
        {
            shoppingListLink.click();
            DriverManager.getInstance().checkUntil(new WaitPageLoad(), Duration.ofSeconds(30));
        }
}
