package com.borisenko.test.pages;


import com.borisenko.test.pages.elements.Button;
import com.borisenko.test.pages.elements.Element;
import com.borisenko.test.util.selenium.DriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ShoppingListPage extends MainPage {


    @FindBy(css = "[data-testid='create-new-shopping-list-button']")
    private Element createNewList;


    @FindBy(css = "[data-testid='desktop-add-item-autocomplete']")
    private Element itemInput;


    @FindBy(xpath = ".//form")
    private CreateListPopUp createListPopUp;

    @FindBy(xpath = ".//div[contains(@class,'modal-mobile-popup')]//.")
    private DeleteListPopUp deleteListPopUp;

    @FindBy(xpath = ".//div[contains(@data-testid,'shopping-lists-list-name')]/../..")
    private List<ShoppingList> shoppingList;

    @FindBy(css = "[data-testid='shopping-list-item']")
    private List<ShoppingItem> itemsList;

    public CreateListPopUp createNewList() {
        createNewList.click();
        DriverManager.getInstance().checkUntil(ExpectedConditions.not(ExpectedConditions.invisibilityOf(createListPopUp.getWebElement())), Duration.ofSeconds(10));
        DriverManager.getInstance().initPage(this);
        return createListPopUp;

    }

    public DeleteListPopUp deleteFirstListWithName(String listName) {
        ShoppingList list = getShoppingList(listName).stream().findFirst().orElseThrow(() -> new RuntimeException("List with name " + listName + " not found and was not delete"));
        list.delete();
        DriverManager.getInstance().checkUntil(ExpectedConditions.not(ExpectedConditions.invisibilityOf(deleteListPopUp.getWebElement())), Duration.ofSeconds(10));
        DriverManager.getInstance().initPage(this);
        return deleteListPopUp;
    }

    public List<ShoppingList> getShoppingList(String listName) {
        return shoppingList.stream().filter(x -> x.getName().compareTo(listName) == 0).collect(Collectors.toList());
    }

    public List<ShoppingItem> getItemShoppingList(String itemName) {
        return itemsList.stream().filter(x -> x.getName().compareTo(itemName) == 0).collect(Collectors.toList());
    }

    public void addItem(String itemName) {
        itemInput.clear();
        itemInput.sendKeys(itemName);
        itemInput.sendKeys(Keys.ENTER);
        // because i can't find locator for uniq define new item in item list, and wait it exist. It's hardcode timeout (
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static class CreateListPopUp extends Element {
        @FindBy(xpath = ".//*[contains(@data-testid,'create-new-shopping-list-name-input')]//input")
        private Element name;

        @FindBy(css = "[data-testid='create-new-shopping-list-cancel-button']")
        private Button cancel;

        @FindBy(css = "[data-testid='create-new-shopping-list-create-button']")
        private Button create;


        public CreateListPopUp(WebElement webElement) {
            super(webElement);
        }

        public void addNewListName(String listName) {
            name.clear();
            name.sendKeys(listName);
        }

        public void cancel() {
            cancel.click();
        }

        public void create() {
            create.click();
        }
    }

    public static class DeleteListPopUp extends Element {

        @FindBy(css = "[data-testid='cancel-button']")
        private Button cancel;

        @FindBy(css = "[data-testid='confirm-delete-button']")
        private Button confirm;


        public DeleteListPopUp(WebElement webElement) {
            super(webElement);
        }

        public void cancel() {
            cancel.click();
        }

        public void confirm() {
            confirm.click();
        }
    }

    public static class ShoppingList extends Element {

        @FindBy(css = "[data-testid='shopping-lists-list-name']")
        private Element name;

        @FindBy(xpath = ".//button")
        private Element menuButton;

        @FindBy(css = "[data-testid='shopping-list-delete-menu-button']")
        private Element deleteButton;


        public ShoppingList(WebElement webElement) {
            super(webElement);

        }

        public String getName() {
            return name.getText();
        }

        public void delete() {
            menuButton.click();
            DriverManager.getInstance().checkUntil(ExpectedConditions.not(ExpectedConditions.invisibilityOf(deleteButton.getWebElement())), Duration.ofSeconds(10));
            deleteButton.click();

        }

    }


    public static class ShoppingItem extends Element {

        @FindBy(css = "[data-testid='shopping-list-item-name']")
        private Element name;


        public ShoppingItem(WebElement webElement) {
            super(webElement);

        }

        public String getName() {
            return name.getText();
        }
    }
}
