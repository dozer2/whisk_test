package com.borisenko.test.pages;

import com.borisenko.test.steps.LoginSteps;
import com.borisenko.test.steps.ShoppingListPageSteps;
import com.borisenko.test.util.PropertyLoader;
import com.borisenko.test.util.selenium.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by maxim on 21.04.18.
 */
public class ShoppingListTestShadow {

    private static final String SCREENSHOT_FOLDER = "target/screenshots/";
    private static final String SCREENSHOT_FORMAT = ".png";
    private String login= PropertyLoader.loadProperty("site.login");
    private String password= PropertyLoader.loadProperty("site.password");

    ShoppingListPageSteps shoppingListPageSteps;
    String shoppingListName="List at "+ LocalTime.now().toString();

    @DataProvider
    public Object[][] food() {
        return new Object[][]{
                {"potatos"},
                {"bread"},
                {"candy"},
                {"meet"},
                {"milk"}
        };
    }

   @BeforeClass
   public void init(){
       shoppingListPageSteps=new LoginSteps().openLoginForm()
               .firstLogin(login,password)
               .openShoppingTab()
               .createNewShoppingList(shoppingListName);

   }

    @Test(dataProvider = "food",priority = 1)
    public void addItemsToShoppingList(String itemName){
        shoppingListPageSteps
                .addItemToActiveShoppingList(itemName)
                .checkExistItemInActiveShoppingList(itemName);
    }
   // bad practics, because each test must be atomic. Also ordering - bad practics (
    @Test(priority = 2)
    public void deleteToShoppingList(){
        shoppingListPageSteps
                .deleteShoppingList(shoppingListName)
                .checkNotExistShoppingList(shoppingListName);
    }

    @AfterClass
    public void tearDown(){
        DriverManager.getInstance().closeWebDriver();
    }


    @AfterMethod
    public void setScreenshot(ITestResult result) {
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

    }


}
