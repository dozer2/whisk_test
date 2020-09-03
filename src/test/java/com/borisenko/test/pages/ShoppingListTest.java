package com.borisenko.test.pages;

import com.borisenko.test.steps.LoginSteps;
import com.borisenko.test.util.PropertyLoader;
import com.borisenko.test.util.selenium.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by maxim on 21.04.18.
 */
public class ShoppingListTest {

    private static final String SCREENSHOT_FOLDER = "target/screenshots/";
    private static final String SCREENSHOT_FORMAT = ".png";
    private String login= PropertyLoader.loadProperty("site.login");
    private String password= PropertyLoader.loadProperty("site.password");


    @Test
    public void addItemsToShoppingList(){
        List<String> items = Arrays.asList("banana","pineapple","bread","apple","meet");
        new LoginSteps().openLoginForm()
                .firstLogin(login,password)
                .openShoppingTab()
                .createNewShoppingList("List at "+ LocalTime.now().toString())
                .addItemToActiveShoppingList(items.get(0))
                .addItemToActiveShoppingList(items.get(1))
                .addItemToActiveShoppingList(items.get(2))
                .addItemToActiveShoppingList(items.get(3))
                .addItemToActiveShoppingList(items.get(4))
                .checkExistItemInActiveShoppingList(items.get(0))
                .checkExistItemInActiveShoppingList(items.get(2))
                .checkExistItemInActiveShoppingList(items.get(3))
                .checkExistItemInActiveShoppingList(items.get(3));
    }

    @Test
    public void deleteToShoppingList(){
        String shoppingListName="List at "+ LocalTime.now().toString();
        new LoginSteps().openLoginForm()
                .firstLogin(login,password)
                .openShoppingTab()
                .createNewShoppingList(shoppingListName)
                .deleteShoppingList(shoppingListName)
                .checkNotExistShoppingList(shoppingListName);
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            try {
                WebDriver returned = new Augmenter().augment(DriverManager.getInstance().getWebDriver());
                if (returned != null) {
                    File f = ((TakesScreenshot) returned).getScreenshotAs(OutputType.FILE);
                    try {
                        FileUtils.copyFile(f,
                                new File(SCREENSHOT_FOLDER + result.getName() + SCREENSHOT_FORMAT));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (ScreenshotException se) {
                se.printStackTrace();
            }
        }
        DriverManager.getInstance().closeWebDriver();
    }


}
