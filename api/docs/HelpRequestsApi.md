# HelpRequestsApi

All URIs are relative to *https://nexd-backend-staging.herokuapp.com:443/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**helpRequestsControllerAddArticleInHelpRequest**](HelpRequestsApi.md#helpRequestsControllerAddArticleInHelpRequest) | **PUT** help-requests/{helpRequestId}/article/{articleId} | Put an article to a help request, endpoint overrides.
[**helpRequestsControllerGetAll**](HelpRequestsApi.md#helpRequestsControllerGetAll) | **GET** help-requests | Get and filter for various help requests
[**helpRequestsControllerGetSingleRequest**](HelpRequestsApi.md#helpRequestsControllerGetSingleRequest) | **GET** help-requests/{helpRequestId} | Get a single help request by id
[**helpRequestsControllerInsertRequestWithArticles**](HelpRequestsApi.md#helpRequestsControllerInsertRequestWithArticles) | **POST** help-requests | Add a help request
[**helpRequestsControllerRemoveArticleInHelpRequest**](HelpRequestsApi.md#helpRequestsControllerRemoveArticleInHelpRequest) | **DELETE** help-requests/{helpRequestId}/article/{articleId} | Remove an article from a help request
[**helpRequestsControllerUpdateRequest**](HelpRequestsApi.md#helpRequestsControllerUpdateRequest) | **PUT** help-requests/{helpRequestId} | Modify a help request (e.g. address or articles)



## helpRequestsControllerAddArticleInHelpRequest

> HelpRequest helpRequestsControllerAddArticleInHelpRequest(helpRequestId, articleId, createOrUpdateHelpRequestArticleDto)

Put an article to a help request, endpoint overrides.

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.HelpRequestsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        HelpRequestsApi apiInstance = new HelpRequestsApi(defaultClient);
        Long helpRequestId = 56L; // Long | Id of the help request
        Long articleId = 56L; // Long | Id of the article
        CreateOrUpdateHelpRequestArticleDto createOrUpdateHelpRequestArticleDto = new CreateOrUpdateHelpRequestArticleDto(); // CreateOrUpdateHelpRequestArticleDto | 
        try {
            HelpRequest result = apiInstance.helpRequestsControllerAddArticleInHelpRequest(helpRequestId, articleId, createOrUpdateHelpRequestArticleDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HelpRequestsApi#helpRequestsControllerAddArticleInHelpRequest");
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
 **helpRequestId** | **Long**| Id of the help request |
 **articleId** | **Long**| Id of the article |
 **createOrUpdateHelpRequestArticleDto** | [**CreateOrUpdateHelpRequestArticleDto**](CreateOrUpdateHelpRequestArticleDto.md)|  |

### Return type

[**HelpRequest**](HelpRequest.md)

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
| **404** | Help request not found |  -  |


## helpRequestsControllerGetAll

> List&lt;HelpRequest&gt; helpRequestsControllerGetAll(userId, excludeUserId, zipCode, includeRequester, status)

Get and filter for various help requests

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.HelpRequestsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        HelpRequestsApi apiInstance = new HelpRequestsApi(defaultClient);
        String userId = "userId_example"; // String | If included, filter by userId, \"me\" for the requesting user, otherwise all users are replied. The excludeUserId query inverts the logic and excludes the given userId. 
        Boolean excludeUserId = true; // Boolean | If true, the given userId (in query) is excluded (and not filtered for as default). Requires the userId query.
        List<String> zipCode = Arrays.asList(); // List<String> | Filter by an array of zipCodes
        Boolean includeRequester = true; // Boolean | If \"true\", the requester object is included in each help request
        List<HelpRequestStatus> status = Arrays.asList(); // List<HelpRequestStatus> | Array of status to filter for
        try {
            List<HelpRequest> result = apiInstance.helpRequestsControllerGetAll(userId, excludeUserId, zipCode, includeRequester, status);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HelpRequestsApi#helpRequestsControllerGetAll");
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
 **userId** | **String**| If included, filter by userId, \&quot;me\&quot; for the requesting user, otherwise all users are replied. The excludeUserId query inverts the logic and excludes the given userId.  | [optional]
 **excludeUserId** | **Boolean**| If true, the given userId (in query) is excluded (and not filtered for as default). Requires the userId query. | [optional]
 **zipCode** | [**List&lt;String&gt;**](String.md)| Filter by an array of zipCodes | [optional]
 **includeRequester** | **Boolean**| If \&quot;true\&quot;, the requester object is included in each help request | [optional]
 **status** | [**List&lt;HelpRequestStatus&gt;**](HelpRequestStatus.md)| Array of status to filter for | [optional]

### Return type

[**List&lt;HelpRequest&gt;**](HelpRequest.md)

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


## helpRequestsControllerGetSingleRequest

> HelpRequest helpRequestsControllerGetSingleRequest(helpRequestId)

Get a single help request by id

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.HelpRequestsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        HelpRequestsApi apiInstance = new HelpRequestsApi(defaultClient);
        Long helpRequestId = 56L; // Long | Id of the help request
        try {
            HelpRequest result = apiInstance.helpRequestsControllerGetSingleRequest(helpRequestId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HelpRequestsApi#helpRequestsControllerGetSingleRequest");
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
 **helpRequestId** | **Long**| Id of the help request |

### Return type

[**HelpRequest**](HelpRequest.md)

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
| **404** | Request not found |  -  |


## helpRequestsControllerInsertRequestWithArticles

> HelpRequest helpRequestsControllerInsertRequestWithArticles(helpRequestCreateDto)

Add a help request

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.HelpRequestsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        HelpRequestsApi apiInstance = new HelpRequestsApi(defaultClient);
        HelpRequestCreateDto helpRequestCreateDto = new HelpRequestCreateDto(); // HelpRequestCreateDto | 
        try {
            HelpRequest result = apiInstance.helpRequestsControllerInsertRequestWithArticles(helpRequestCreateDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HelpRequestsApi#helpRequestsControllerInsertRequestWithArticles");
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
 **helpRequestCreateDto** | [**HelpRequestCreateDto**](HelpRequestCreateDto.md)|  |

### Return type

[**HelpRequest**](HelpRequest.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Add a complete request including articles. |  -  |
| **401** | Unauthorized |  -  |


## helpRequestsControllerRemoveArticleInHelpRequest

> HelpRequest helpRequestsControllerRemoveArticleInHelpRequest(helpRequestId, articleId)

Remove an article from a help request

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.HelpRequestsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        HelpRequestsApi apiInstance = new HelpRequestsApi(defaultClient);
        Long helpRequestId = 56L; // Long | Id of the help request
        Long articleId = 56L; // Long | Id of the article
        try {
            HelpRequest result = apiInstance.helpRequestsControllerRemoveArticleInHelpRequest(helpRequestId, articleId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HelpRequestsApi#helpRequestsControllerRemoveArticleInHelpRequest");
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
 **helpRequestId** | **Long**| Id of the help request |
 **articleId** | **Long**| Id of the article |

### Return type

[**HelpRequest**](HelpRequest.md)

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
| **404** | Help request not found |  -  |


## helpRequestsControllerUpdateRequest

> HelpRequest helpRequestsControllerUpdateRequest(helpRequestId, helpRequestCreateDto)

Modify a help request (e.g. address or articles)

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.HelpRequestsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        HelpRequestsApi apiInstance = new HelpRequestsApi(defaultClient);
        Long helpRequestId = 56L; // Long | Id of the help request
        HelpRequestCreateDto helpRequestCreateDto = new HelpRequestCreateDto(); // HelpRequestCreateDto | 
        try {
            HelpRequest result = apiInstance.helpRequestsControllerUpdateRequest(helpRequestId, helpRequestCreateDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HelpRequestsApi#helpRequestsControllerUpdateRequest");
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
 **helpRequestId** | **Long**| Id of the help request |
 **helpRequestCreateDto** | [**HelpRequestCreateDto**](HelpRequestCreateDto.md)|  |

### Return type

[**HelpRequest**](HelpRequest.md)

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
| **404** | Help request not found |  -  |

