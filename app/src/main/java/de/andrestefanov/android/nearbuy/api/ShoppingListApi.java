package de.andrestefanov.android.nearbuy.api;

import de.andrestefanov.android.nearbuy.CollectionFormats.*;

import io.reactivex.Observable;
import io.reactivex.Completable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import java.math.BigDecimal;
import de.andrestefanov.android.nearbuy.api.model.ShoppingList;
import de.andrestefanov.android.nearbuy.api.model.ShoppingListFormDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ShoppingListApi {
  /**
   * 
   * 
   * @param shoppingListId  (required)
   * @param requestId  (required)
   * @return Observable&lt;ShoppingList&gt;
   */
  @PUT("api/shopping-list/{shoppingListId}/{requestId}")
  Observable<ShoppingList> shoppingListControllerAddRequestToList(
    @retrofit2.http.Path("shoppingListId") BigDecimal shoppingListId, @retrofit2.http.Path("requestId") BigDecimal requestId
  );

  /**
   * 
   * 
   * @param shoppingListId  (required)
   * @param requestId  (required)
   * @return Observable&lt;ShoppingList&gt;
   */
  @DELETE("api/shopping-list/{shoppingListId}/{requestId}")
  Observable<ShoppingList> shoppingListControllerDeleteRequestFromList(
    @retrofit2.http.Path("shoppingListId") BigDecimal shoppingListId, @retrofit2.http.Path("requestId") BigDecimal requestId
  );

  /**
   * 
   * 
   * @param id  (required)
   * @return Observable&lt;ShoppingList&gt;
   */
  @GET("api/shopping-list/{id}")
  Observable<ShoppingList> shoppingListControllerFindOne(
    @retrofit2.http.Path("id") BigDecimal id
  );

  /**
   * 
   * 
   * @return Observable&lt;List&lt;ShoppingList&gt;&gt;
   */
  @GET("api/shopping-list")
  Observable<List<ShoppingList>> shoppingListControllerGetUserLists();
    

  /**
   * 
   * 
   * @param shoppingListFormDto  (required)
   * @return Observable&lt;ShoppingList&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/shopping-list")
  Observable<ShoppingList> shoppingListControllerInsertNewShoppingList(
    @retrofit2.http.Body ShoppingListFormDto shoppingListFormDto
  );

  /**
   * 
   * 
   * @param id  (required)
   * @param shoppingListFormDto  (required)
   * @return Observable&lt;ShoppingList&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("api/shopping-list/{id}")
  Observable<ShoppingList> shoppingListControllerUpdateShoppingList(
    @retrofit2.http.Path("id") BigDecimal id, @retrofit2.http.Body ShoppingListFormDto shoppingListFormDto
  );

}
