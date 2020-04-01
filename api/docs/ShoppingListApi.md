# ShoppingListApi

All URIs are relative to *http://localhost:3001*

Method | HTTP request | Description
------------- | ------------- | -------------
[**shoppingListControllerAddRequestToList**](ShoppingListApi.md#shoppingListControllerAddRequestToList) | **PUT** api/shopping-list/{shoppingListId}/{requestId} | 
[**shoppingListControllerDeleteRequestFromList**](ShoppingListApi.md#shoppingListControllerDeleteRequestFromList) | **DELETE** api/shopping-list/{shoppingListId}/{requestId} | 
[**shoppingListControllerFindOne**](ShoppingListApi.md#shoppingListControllerFindOne) | **GET** api/shopping-list/{id} | 
[**shoppingListControllerGetUserLists**](ShoppingListApi.md#shoppingListControllerGetUserLists) | **GET** api/shopping-list | 
[**shoppingListControllerInsertNewShoppingList**](ShoppingListApi.md#shoppingListControllerInsertNewShoppingList) | **POST** api/shopping-list | 
[**shoppingListControllerUpdateShoppingList**](ShoppingListApi.md#shoppingListControllerUpdateShoppingList) | **PUT** api/shopping-list/{id} | 



## shoppingListControllerAddRequestToList

> ShoppingList shoppingListControllerAddRequestToList(shoppingListId, requestId)



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.ShoppingListApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:3001");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        ShoppingListApi apiInstance = new ShoppingListApi(defaultClient);
        BigDecimal shoppingListId = new BigDecimal(); // BigDecimal | 
        BigDecimal requestId = new BigDecimal(); // BigDecimal | 
        try {
            ShoppingList result = apiInstance.shoppingListControllerAddRequestToList(shoppingListId, requestId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ShoppingListApi#shoppingListControllerAddRequestToList");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **shoppingListId** | **BigDecimal**|  |
 **requestId** | **BigDecimal**|  |

### Return type

[**ShoppingList**](ShoppingList.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |
| **400** | Bad request |  -  |
| **401** | Unauthorized |  -  |
| **403** | This is not your shopping list |  -  |
| **404** | Shopping list not found |  -  |


## shoppingListControllerDeleteRequestFromList

> ShoppingList shoppingListControllerDeleteRequestFromList(shoppingListId, requestId)



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.ShoppingListApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:3001");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        ShoppingListApi apiInstance = new ShoppingListApi(defaultClient);
        BigDecimal shoppingListId = new BigDecimal(); // BigDecimal | 
        BigDecimal requestId = new BigDecimal(); // BigDecimal | 
        try {
            ShoppingList result = apiInstance.shoppingListControllerDeleteRequestFromList(shoppingListId, requestId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ShoppingListApi#shoppingListControllerDeleteRequestFromList");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **shoppingListId** | **BigDecimal**|  |
 **requestId** | **BigDecimal**|  |

### Return type

[**ShoppingList**](ShoppingList.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |
| **400** | Bad request |  -  |
| **401** | Unauthorized |  -  |
| **403** | This is not your shopping list |  -  |
| **404** | Shopping list not found |  -  |


## shoppingListControllerFindOne

> ShoppingList shoppingListControllerFindOne(id)



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.ShoppingListApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:3001");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        ShoppingListApi apiInstance = new ShoppingListApi(defaultClient);
        BigDecimal id = new BigDecimal(); // BigDecimal | 
        try {
            ShoppingList result = apiInstance.shoppingListControllerFindOne(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ShoppingListApi#shoppingListControllerFindOne");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **BigDecimal**|  |

### Return type

[**ShoppingList**](ShoppingList.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |
| **401** | Unauthorized |  -  |
| **403** | This is not your shopping list |  -  |
| **404** | Shopping list not found |  -  |


## shoppingListControllerGetUserLists

> List&lt;ShoppingList&gt; shoppingListControllerGetUserLists()



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.ShoppingListApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:3001");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        ShoppingListApi apiInstance = new ShoppingListApi(defaultClient);
        try {
            List<ShoppingList> result = apiInstance.shoppingListControllerGetUserLists();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ShoppingListApi#shoppingListControllerGetUserLists");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**List&lt;ShoppingList&gt;**](ShoppingList.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |
| **401** | Unauthorized |  -  |


## shoppingListControllerInsertNewShoppingList

> ShoppingList shoppingListControllerInsertNewShoppingList(shoppingListFormDto)



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.ShoppingListApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:3001");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        ShoppingListApi apiInstance = new ShoppingListApi(defaultClient);
        ShoppingListFormDto shoppingListFormDto = new ShoppingListFormDto(); // ShoppingListFormDto | 
        try {
            ShoppingList result = apiInstance.shoppingListControllerInsertNewShoppingList(shoppingListFormDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ShoppingListApi#shoppingListControllerInsertNewShoppingList");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **shoppingListFormDto** | [**ShoppingListFormDto**](ShoppingListFormDto.md)|  |

### Return type

[**ShoppingList**](ShoppingList.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Added a new shopping list |  -  |
| **400** | Bad request |  -  |
| **401** | Unauthorized |  -  |


## shoppingListControllerUpdateShoppingList

> ShoppingList shoppingListControllerUpdateShoppingList(id, shoppingListFormDto)



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.ShoppingListApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:3001");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        ShoppingListApi apiInstance = new ShoppingListApi(defaultClient);
        BigDecimal id = new BigDecimal(); // BigDecimal | 
        ShoppingListFormDto shoppingListFormDto = new ShoppingListFormDto(); // ShoppingListFormDto | 
        try {
            ShoppingList result = apiInstance.shoppingListControllerUpdateShoppingList(id, shoppingListFormDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ShoppingListApi#shoppingListControllerUpdateShoppingList");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **BigDecimal**|  |
 **shoppingListFormDto** | [**ShoppingListFormDto**](ShoppingListFormDto.md)|  |

### Return type

[**ShoppingList**](ShoppingList.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |
| **400** | Bad request |  -  |
| **401** | Unauthorized |  -  |
| **403** | This is not your shopping list |  -  |
| **404** | Shopping list not found |  -  |

