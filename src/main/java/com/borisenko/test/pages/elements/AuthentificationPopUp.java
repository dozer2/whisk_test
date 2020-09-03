package com.borisenko.test.pages.elements;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthentificationPopUp extends Element {

    @FindBy(xpath = ".//*[contains(@data-testid,'email-phone-number-auth-input')]//input")
   private Element inputEMailOrPhone;

    @FindBy(css = "[data-testid='auth-continue-button']")
    private Button authButton;

    @FindBy(css = "[class=*'recaptcha-checkbox']")
    private Element capchaCheckBox;

    @FindBy(xpath = ".//*[contains(@data-testid,'auth-password-input')]//input")
    private Element inputPassword;

    @FindBy(css = "[data-testid='auth-login-button']")
    private Button logInButton;

    public AuthentificationPopUp(WebElement webElement) {
        super(webElement);
    }

    public void setEmailOrPhoneNumber(String text)
    {
        inputEMailOrPhone.sendKeys(text);
    }
    public void setPassword(String text)
    {
        inputPassword.sendKeys(text);
    }

    public void clickAuthButton(){
        authButton.click();
    }

    public void clickLogInButton(){
        logInButton.click();

    }

    public void clickCapcha(){
    if(capchaCheckBox.getWebElement().isDisplayed())
        capchaCheckBox.click();
    }

}
