# CallsApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**callControllerIndex**](CallsApi.md#callControllerIndex) | **GET** api/call | 
[**callControllerListen**](CallsApi.md#callControllerListen) | **GET** api/call/listen | 
[**callControllerWebhook**](CallsApi.md#callControllerWebhook) | **GET** api/call/webhook | 



## callControllerIndex

> callControllerIndex()



### Example

```java
// Import classes:
import de.andrestefanov.android.nearbuy.ApiClient;
import de.andrestefanov.android.nearbuy.ApiException;
import de.andrestefanov.android.nearbuy.Configuration;
import de.andrestefanov.android.nearbuy.models.*;
import de.andrestefanov.android.nearbuy.api.CallsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        CallsApi apiInstance = new CallsApi(defaultClient);
        try {
            apiInstance.callControllerIndex();
        } catch (ApiException e) {
            System.err.println("Exception when calling CallsApi#callControllerIndex");
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

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |
| **401** | Unauthorized |  -  |


## callControllerListen

> callControllerListen()



### Example

```java
// Import classes:
import de.andrestefanov.android.nearbuy.ApiClient;
import de.andrestefanov.android.nearbuy.ApiException;
import de.andrestefanov.android.nearbuy.Configuration;
import de.andrestefanov.android.nearbuy.models.*;
import de.andrestefanov.android.nearbuy.api.CallsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        CallsApi apiInstance = new CallsApi(defaultClient);
        try {
            apiInstance.callControllerListen();
        } catch (ApiException e) {
            System.err.println("Exception when calling CallsApi#callControllerListen");
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

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |


## callControllerWebhook

> callControllerWebhook()



### Example

```java
// Import classes:
import de.andrestefanov.android.nearbuy.ApiClient;
import de.andrestefanov.android.nearbuy.ApiException;
import de.andrestefanov.android.nearbuy.Configuration;
import de.andrestefanov.android.nearbuy.models.*;
import de.andrestefanov.android.nearbuy.api.CallsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        CallsApi apiInstance = new CallsApi(defaultClient);
        try {
            apiInstance.callControllerWebhook();
        } catch (ApiException e) {
            System.err.println("Exception when calling CallsApi#callControllerWebhook");
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

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |

