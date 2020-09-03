package com.borisenko.test.util.conditions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created by maxim on 22.04.18.
 */
public class WaitPageLoad implements ExpectedCondition<Boolean> {

@Override public Boolean apply(WebDriver webDriver){
    return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").toString().equals("complete");
        }

public String toString(){
        return "page not loaded";
        }
        }
