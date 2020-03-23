# CallsApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**callControllerDownload**](CallsApi.md#callControllerDownload) | **GET** api/call/download/{id} | 
[**callControllerIndex**](CallsApi.md#callControllerIndex) | **GET** api/call | 
[**callControllerInitUpload**](CallsApi.md#callControllerInitUpload) | **GET** api/call/upload | 
[**callControllerTranslated**](CallsApi.md#callControllerTranslated) | **PUT** api/call/translated/{id} | 
[**callControllerUpload**](CallsApi.md#callControllerUpload) | **POST** api/call/upload/{id} | 
[**callControllerWebhook**](CallsApi.md#callControllerWebhook) | **GET** api/call/webhook | 



## callControllerDownload

> callControllerDownload(id)



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
        Integer id = 56; // Integer | audio id
        try {
            apiInstance.callControllerDownload(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling CallsApi#callControllerDownload");
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
 **id** | **Integer**| audio id |

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


## callControllerInitUpload

> callControllerInitUpload()



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
            apiInstance.callControllerInitUpload();
        } catch (ApiException e) {
            System.err.println("Exception when calling CallsApi#callControllerInitUpload");
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
| **202** | Success |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |


## callControllerTranslated

> callControllerTranslated(id)



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
        Integer id = 56; // Integer | audio id
        try {
            apiInstance.callControllerTranslated(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling CallsApi#callControllerTranslated");
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
 **id** | **Integer**| audio id |

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
| **202** | Success |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |


## callControllerUpload

> callControllerUpload(id)



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
        Integer id = 56; // Integer | audio id
        try {
            apiInstance.callControllerUpload(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling CallsApi#callControllerUpload");
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
 **id** | **Integer**| audio id |

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
| **202** | Success |  -  |
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
| **202** | Success |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |

