package de.andrestefanov.android.nearbuy.api;

import de.andrestefanov.android.nearbuy.ApiClient;
import java.math.BigDecimal;
import de.andrestefanov.android.nearbuy.api.model.ShoppingList;
import de.andrestefanov.android.nearbuy.api.model.ShoppingListFormDto;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ShoppingListApi
 */
public class ShoppingListApiTest {

    private ShoppingListApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(ShoppingListApi.class);
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void shoppingListControllerAddRequestToListTest() {
        BigDecimal shoppingListId = null;
        BigDecimal requestId = null;
        // ShoppingList response = api.shoppingListControllerAddRequestToList(shoppingListId, requestId);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void shoppingListControllerFindOneTest() {
        BigDecimal id = null;
        // ShoppingList response = api.shoppingListControllerFindOne(id);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void shoppingListControllerGetUserListsTest() {
        // List<ShoppingList> response = api.shoppingListControllerGetUserLists();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void shoppingListControllerInsertNewShoppingListTest() {
        ShoppingListFormDto shoppingListFormDto = null;
        // ShoppingList response = api.shoppingListControllerInsertNewShoppingList(shoppingListFormDto);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void shoppingListControllerUpdateShoppingListTest() {
        BigDecimal id = null;
        ShoppingListFormDto shoppingListFormDto = null;
        // ShoppingList response = api.shoppingListControllerUpdateShoppingList(id, shoppingListFormDto);

        // TODO: test validations
    }
}
