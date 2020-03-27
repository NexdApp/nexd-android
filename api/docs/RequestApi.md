# RequestApi

All URIs are relative to *http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**requestControllerGetAll**](RequestApi.md#requestControllerGetAll) | **GET** api/request | 
[**requestControllerGetSingleRequest**](RequestApi.md#requestControllerGetSingleRequest) | **GET** api/request/{requestId} | 
[**requestControllerInsertRequestWithArticles**](RequestApi.md#requestControllerInsertRequestWithArticles) | **POST** api/request | 
[**requestControllerMarkArticleAsDone**](RequestApi.md#requestControllerMarkArticleAsDone) | **PUT** api/request/{requestId}/{articleId} | 
[**requestControllerUpdateRequest**](RequestApi.md#requestControllerUpdateRequest) | **PUT** api/request/{requestId} | 



## requestControllerGetAll

> List&lt;RequestEntity&gt; requestControllerGetAll(onlyMine, zipCode)



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.RequestApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        RequestApi apiInstance = new RequestApi(defaultClient);
        String onlyMine = "onlyMine_example"; // String | if \"true\", only the requesting user requests will be replied.
        String zipCode = "zipCode_example"; // String | if set, only requests within the same zip code will be replied
        try {
            List<RequestEntity> result = apiInstance.requestControllerGetAll(onlyMine, zipCode);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling RequestApi#requestControllerGetAll");
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
 **onlyMine** | **String**| if \&quot;true\&quot;, only the requesting user requests will be replied. | [optional]
 **zipCode** | **String**| if set, only requests within the same zip code will be replied | [optional]

### Return type

[**List&lt;RequestEntity&gt;**](RequestEntity.md)

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


## requestControllerGetSingleRequest

> RequestEntity requestControllerGetSingleRequest(requestId)



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.RequestApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        RequestApi apiInstance = new RequestApi(defaultClient);
        BigDecimal requestId = new BigDecimal(); // BigDecimal | 
        try {
            RequestEntity result = apiInstance.requestControllerGetSingleRequest(requestId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling RequestApi#requestControllerGetSingleRequest");
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
 **requestId** | **BigDecimal**|  |

### Return type

[**RequestEntity**](RequestEntity.md)

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


## requestControllerInsertRequestWithArticles

> RequestEntity requestControllerInsertRequestWithArticles(requestFormDto)



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.RequestApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        RequestApi apiInstance = new RequestApi(defaultClient);
        RequestFormDto requestFormDto = new RequestFormDto(); // RequestFormDto | 
        try {
            RequestEntity result = apiInstance.requestControllerInsertRequestWithArticles(requestFormDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling RequestApi#requestControllerInsertRequestWithArticles");
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
 **requestFormDto** | [**RequestFormDto**](RequestFormDto.md)|  |

### Return type

[**RequestEntity**](RequestEntity.md)

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


## requestControllerMarkArticleAsDone

> RequestEntity requestControllerMarkArticleAsDone(requestId, articleId, requestArticleStatusDto)



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.RequestApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        RequestApi apiInstance = new RequestApi(defaultClient);
        BigDecimal requestId = new BigDecimal(); // BigDecimal | 
        BigDecimal articleId = new BigDecimal(); // BigDecimal | 
        RequestArticleStatusDto requestArticleStatusDto = new RequestArticleStatusDto(); // RequestArticleStatusDto | 
        try {
            RequestEntity result = apiInstance.requestControllerMarkArticleAsDone(requestId, articleId, requestArticleStatusDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling RequestApi#requestControllerMarkArticleAsDone");
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
 **requestId** | **BigDecimal**|  |
 **articleId** | **BigDecimal**|  |
 **requestArticleStatusDto** | [**RequestArticleStatusDto**](RequestArticleStatusDto.md)|  |

### Return type

[**RequestEntity**](RequestEntity.md)

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
| **404** | Request not found |  -  |


## requestControllerUpdateRequest

> RequestEntity requestControllerUpdateRequest(requestId, requestFormDto)



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.RequestApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        RequestApi apiInstance = new RequestApi(defaultClient);
        BigDecimal requestId = new BigDecimal(); // BigDecimal | 
        RequestFormDto requestFormDto = new RequestFormDto(); // RequestFormDto | 
        try {
            RequestEntity result = apiInstance.requestControllerUpdateRequest(requestId, requestFormDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling RequestApi#requestControllerUpdateRequest");
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
 **requestId** | **BigDecimal**|  |
 **requestFormDto** | [**RequestFormDto**](RequestFormDto.md)|  |

### Return type

[**RequestEntity**](RequestEntity.md)

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
| **404** | Request not found |  -  |

