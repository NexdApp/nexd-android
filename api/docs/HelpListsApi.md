# HelpListsApi

All URIs are relative to *https://nexd-backend-staging.herokuapp.com:443/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**helpListsControllerAddHelpRequestToList**](HelpListsApi.md#helpListsControllerAddHelpRequestToList) | **PUT** help-lists/{helpListId}/help-request/{helpRequestId} | Add a help request to a help list
[**helpListsControllerDeleteHelpRequestFromHelpList**](HelpListsApi.md#helpListsControllerDeleteHelpRequestFromHelpList) | **DELETE** help-lists/{helpListId}/help-request/{helpRequestId} | Delete a help request from help list
[**helpListsControllerFindOne**](HelpListsApi.md#helpListsControllerFindOne) | **GET** help-lists/{helpListId} | Get a specific help list
[**helpListsControllerGetUserLists**](HelpListsApi.md#helpListsControllerGetUserLists) | **GET** help-lists | Get help lists of the requesting user
[**helpListsControllerInsertNewHelpList**](HelpListsApi.md#helpListsControllerInsertNewHelpList) | **POST** help-lists | Add a new help list for the current user
[**helpListsControllerModifyArticleInAllHelpRequests**](HelpListsApi.md#helpListsControllerModifyArticleInAllHelpRequests) | **PUT** help-lists/{helpListId}/article/{articleId} | Set/unset article done in all help requests
[**helpListsControllerModifyArticleInHelpRequest**](HelpListsApi.md#helpListsControllerModifyArticleInHelpRequest) | **PUT** help-lists/{helpListId}/help-request/{helpRequestId}/article/{articleId} | Set/unset articleDone of an article in a specific help request
[**helpListsControllerUpdateHelpLists**](HelpListsApi.md#helpListsControllerUpdateHelpLists) | **PUT** help-lists/{helpListId} | Modify a help list



## helpListsControllerAddHelpRequestToList

> HelpList helpListsControllerAddHelpRequestToList(helpListId, helpRequestId)

Add a help request to a help list

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.HelpListsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        HelpListsApi apiInstance = new HelpListsApi(defaultClient);
        Integer helpListId = 56; // Integer | Id of the help list
        Integer helpRequestId = 56; // Integer | Id of the help request
        try {
            HelpList result = apiInstance.helpListsControllerAddHelpRequestToList(helpListId, helpRequestId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HelpListsApi#helpListsControllerAddHelpRequestToList");
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
 **helpListId** | **Integer**| Id of the help list |
 **helpRequestId** | **Integer**| Id of the help request |

### Return type

[**HelpList**](HelpList.md)

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


## helpListsControllerDeleteHelpRequestFromHelpList

> HelpList helpListsControllerDeleteHelpRequestFromHelpList(helpListId, helpRequestId)

Delete a help request from help list

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.HelpListsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        HelpListsApi apiInstance = new HelpListsApi(defaultClient);
        Integer helpListId = 56; // Integer | Id of the help list
        Integer helpRequestId = 56; // Integer | Id of the help request
        try {
            HelpList result = apiInstance.helpListsControllerDeleteHelpRequestFromHelpList(helpListId, helpRequestId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HelpListsApi#helpListsControllerDeleteHelpRequestFromHelpList");
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
 **helpListId** | **Integer**| Id of the help list |
 **helpRequestId** | **Integer**| Id of the help request |

### Return type

[**HelpList**](HelpList.md)

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


## helpListsControllerFindOne

> HelpList helpListsControllerFindOne(helpListId)

Get a specific help list

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.HelpListsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        HelpListsApi apiInstance = new HelpListsApi(defaultClient);
        Long helpListId = 56L; // Long | Id of the help list
        try {
            HelpList result = apiInstance.helpListsControllerFindOne(helpListId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HelpListsApi#helpListsControllerFindOne");
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
 **helpListId** | **Long**| Id of the help list |

### Return type

[**HelpList**](HelpList.md)

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
| **403** | This is not your help list |  -  |
| **404** | Help list not found |  -  |


## helpListsControllerGetUserLists

> List&lt;HelpList&gt; helpListsControllerGetUserLists(userId)

Get help lists of the requesting user

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.HelpListsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        HelpListsApi apiInstance = new HelpListsApi(defaultClient);
        String userId = "userId_example"; // String | If included, filter by userId, otherwise by requesting user.
        try {
            List<HelpList> result = apiInstance.helpListsControllerGetUserLists(userId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HelpListsApi#helpListsControllerGetUserLists");
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
 **userId** | **String**| If included, filter by userId, otherwise by requesting user. | [optional]

### Return type

[**List&lt;HelpList&gt;**](HelpList.md)

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


## helpListsControllerInsertNewHelpList

> HelpList helpListsControllerInsertNewHelpList(helpListCreateDto)

Add a new help list for the current user

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.HelpListsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        HelpListsApi apiInstance = new HelpListsApi(defaultClient);
        HelpListCreateDto helpListCreateDto = new HelpListCreateDto(); // HelpListCreateDto | 
        try {
            HelpList result = apiInstance.helpListsControllerInsertNewHelpList(helpListCreateDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HelpListsApi#helpListsControllerInsertNewHelpList");
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
 **helpListCreateDto** | [**HelpListCreateDto**](HelpListCreateDto.md)|  |

### Return type

[**HelpList**](HelpList.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Added a new help list |  -  |
| **400** | Bad request |  -  |
| **401** | Unauthorized |  -  |


## helpListsControllerModifyArticleInAllHelpRequests

> HelpList helpListsControllerModifyArticleInAllHelpRequests(helpListId, articleId, articleDone)

Set/unset article done in all help requests

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.HelpListsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        HelpListsApi apiInstance = new HelpListsApi(defaultClient);
        Integer helpListId = 56; // Integer | Id of the help list
        Long articleId = 56L; // Long | Id of the article
        Boolean articleDone = true; // Boolean | true to set the article as \"bought\"
        try {
            HelpList result = apiInstance.helpListsControllerModifyArticleInAllHelpRequests(helpListId, articleId, articleDone);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HelpListsApi#helpListsControllerModifyArticleInAllHelpRequests");
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
 **helpListId** | **Integer**| Id of the help list |
 **articleId** | **Long**| Id of the article |
 **articleDone** | **Boolean**| true to set the article as \&quot;bought\&quot; |

### Return type

[**HelpList**](HelpList.md)

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


## helpListsControllerModifyArticleInHelpRequest

> HelpList helpListsControllerModifyArticleInHelpRequest(helpListId, helpRequestId, articleId, articleDone)

Set/unset articleDone of an article in a specific help request

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.HelpListsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        HelpListsApi apiInstance = new HelpListsApi(defaultClient);
        Integer helpListId = 56; // Integer | Id of the help list
        Integer helpRequestId = 56; // Integer | Id of the help request
        Integer articleId = 56; // Integer | Id of the article
        Boolean articleDone = true; // Boolean | true to set the article as \"bought\"
        try {
            HelpList result = apiInstance.helpListsControllerModifyArticleInHelpRequest(helpListId, helpRequestId, articleId, articleDone);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HelpListsApi#helpListsControllerModifyArticleInHelpRequest");
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
 **helpListId** | **Integer**| Id of the help list |
 **helpRequestId** | **Integer**| Id of the help request |
 **articleId** | **Integer**| Id of the article |
 **articleDone** | **Boolean**| true to set the article as \&quot;bought\&quot; |

### Return type

[**HelpList**](HelpList.md)

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


## helpListsControllerUpdateHelpLists

> HelpList helpListsControllerUpdateHelpLists(helpListId, helpListCreateDto)

Modify a help list

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.HelpListsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        HelpListsApi apiInstance = new HelpListsApi(defaultClient);
        Integer helpListId = 56; // Integer | Id of the help list
        HelpListCreateDto helpListCreateDto = new HelpListCreateDto(); // HelpListCreateDto | 
        try {
            HelpList result = apiInstance.helpListsControllerUpdateHelpLists(helpListId, helpListCreateDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HelpListsApi#helpListsControllerUpdateHelpLists");
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
 **helpListId** | **Integer**| Id of the help list |
 **helpListCreateDto** | [**HelpListCreateDto**](HelpListCreateDto.md)|  |

### Return type

[**HelpList**](HelpList.md)

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

