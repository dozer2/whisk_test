package com.borisenko.test.pages.elements;

import com.borisenko.test.util.selenium.IElement;
import org.openqa.selenium.*;

import java.util.List;

/**
 * Created by maxim on 21.04.18.
 */

    public class Element  implements IElement{

    protected WebElement webElement;

    protected By locator;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public By getLocator() {
        return locator;
    }

    public void setLocator(By locator) {
        this.locator = locator;
    }

    public void click() {
        webElement.click();
    }


    public void submit() {
        webElement.submit();
    }


    public void sendKeys(CharSequence... charSequences) {
        webElement.sendKeys(charSequences);
    }


    public void clear() {
    webElement.clear();
    }


    public String getTagName() {
        return webElement.getTagName();
    }


    public String getAttribute(String s) {
        return webElement.getAttribute(s);
    }


    public boolean isSelected() {
        return webElement.isSelected();
    }


    public boolean isEnabled() {
        return webElement.isEnabled();
    }


    public String getText() {
        return webElement.getText();
    }


    public List<WebElement> findElements(By by) {
        return webElement.findElements(by);
    }


    public WebElement findElement(By by) {
        return webElement.findElement(by);
    }


    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }


    public Point getLocation() {
        return webElement.getLocation();
    }


    public Dimension getSize() {
        return webElement.getSize();
    }


    public String getCssValue(String s) {
        return webElement.getCssValue(s);
    }


    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return webElement.getScreenshotAs(outputType);
    }
}
