package com.borisenko.test.pages.elements;

import com.borisenko.test.util.conditions.IsNotExistElement;
import com.borisenko.test.util.selenium.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class Button  extends Element{

   @FindBy(xpath = ".//button[starts-with(@data-testid,'auth-continue-button')]/div[2]/div")
   private  Element loader;

    public Button(WebElement webElement) {
        super(webElement);
    }

    @Override
    public void click() {
        super.click();
        untilLoad();
    }

    private void untilLoad()
    {
        DriverManager.getInstance().checkUntil(new IsNotExistElement(loader), Duration.ofSeconds(10));
    }
}
