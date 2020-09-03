package com.borisenko.test.util.conditions;

import com.borisenko.test.util.selenium.IElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created by maxim on 21.04.18.
 */
public class IsNotExistElement implements ExpectedCondition<Boolean> {

    IElement element;

    public IsNotExistElement(IElement element) {
        this.element = element;
    }

    @Override
    public Boolean apply(WebDriver webDriver) {
        try {
            return !element.getWebElement().isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException var3) {
            return Boolean.TRUE;
        }
    }

    public String toString() {
        return "element to no longer be visible: " + element.getClass().getSimpleName();
    }

}
