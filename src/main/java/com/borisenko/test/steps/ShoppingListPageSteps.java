package com.borisenko.test.steps;

import com.borisenko.test.pages.ShoppingListPage;
import org.hamcrest.Matchers;

import static org.junit.Assert.assertThat;

/**
 * Created by maxim on 21.04.18.
 */
public class ShoppingListPageSteps extends MainPageSteps{

    ShoppingListPage shoppingList;

    public ShoppingListPageSteps(ShoppingListPage shoppingList) {
        super(shoppingList);
        this.shoppingList = shoppingList;

    }

    public ShoppingListPageSteps createNewShoppingList(String listName)
    {
        ShoppingListPage.CreateListPopUp popUp =shoppingList.createNewList();
        popUp.addNewListName(listName);
        popUp.create();
        shoppingList.waitLoadPage();
        refreshPage();
        return this;
    }

    public ShoppingListPageSteps addItemToActiveShoppingList(String itemName){
        shoppingList.addItem(itemName);
        refreshPage();
        return this;
    }

    public ShoppingListPageSteps deleteShoppingList(String listName){
        shoppingList.deleteFirstListWithName(listName).confirm();
        refreshPage();
        return this;
    }

    public ShoppingListPageSteps checkExistItemInActiveShoppingList(String itemName){
        assertThat("Item "+ itemName+" is not exists in active shopping list",shoppingList.getItemShoppingList(itemName), Matchers.not(Matchers.empty()));
        return this;
    }
    public ShoppingListPageSteps checkNotExistShoppingList(String listName){
        assertThat("List "+ listName+" is exists ",shoppingList.getShoppingList(listName), Matchers.empty());
        return this;
    }

    @Override
    public ShoppingListPageSteps refreshPage() {
        driverManager.initPage(shoppingList);
        return this;
    }
}
