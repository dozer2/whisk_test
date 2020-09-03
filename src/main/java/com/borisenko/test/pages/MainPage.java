package com.borisenko.test.pages;


import com.borisenko.test.pages.elements.*;
import com.borisenko.test.util.conditions.IsNotExistElement;
import com.borisenko.test.util.conditions.WaitPageLoad;
import com.borisenko.test.util.selenium.DriverManager;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;


@FindBy(id = "app")
public  class MainPage {

    @FindBy(xpath = ".//div[contains(@id,'app')]/div[1]")
	private MainTopBar mainTopBar;

	@FindBy(xpath = ".//div[contains(@id,'app')]//*[name()='svg']//*[contains(@*,'spinner')]")
	private Element loadSpinner;

	public void clickToLinkOnSecondTabPanel()
	{
		mainTopBar.clickToLinkOnSecondTabPanel();

	}

	public void waitLoadPage(){
		DriverManager.getInstance().checkUntil(new IsNotExistElement(loadSpinner), Duration.ofSeconds(15));
	}


}
