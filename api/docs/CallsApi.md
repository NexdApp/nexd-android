# CallsApi

All URIs are relative to *https://nexd-backend-staging.herokuapp.com:443/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**callsControllerCalls**](CallsApi.md#callsControllerCalls) | **GET** call/calls | Returns all calls with the given parameters
[**callsControllerConverted**](CallsApi.md#callsControllerConverted) | **PUT** call/calls/{sid}/converted | Sets a call as converted to shopping list
[**callsControllerGetCallUrl**](CallsApi.md#callsControllerGetCallUrl) | **GET** call/calls/{sid}/record | Redirects the request to the stored record file.
[**callsControllerGetNumber**](CallsApi.md#callsControllerGetNumber) | **GET** call/number | Returns available numbers



## callsControllerCalls

> List&lt;Call&gt; callsControllerCalls(callQueryDto)

Returns all calls with the given parameters

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.CallsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        CallsApi apiInstance = new CallsApi(defaultClient);
        CallQueryDto callQueryDto = new CallQueryDto(); // CallQueryDto | 
        try {
            List<Call> result = apiInstance.callsControllerCalls(callQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CallsApi#callsControllerCalls");
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
 **callQueryDto** | [**CallQueryDto**](CallQueryDto.md)|  |

### Return type

[**List&lt;Call&gt;**](Call.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |
| **401** | Unauthorized |  -  |


## callsControllerConverted

> Call callsControllerConverted(sid, convertedHelpRequestDto)

Sets a call as converted to shopping list

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.CallsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        CallsApi apiInstance = new CallsApi(defaultClient);
        String sid = "sid_example"; // String | call sid
        ConvertedHelpRequestDto convertedHelpRequestDto = new ConvertedHelpRequestDto(); // ConvertedHelpRequestDto | 
        try {
            Call result = apiInstance.callsControllerConverted(sid, convertedHelpRequestDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CallsApi#callsControllerConverted");
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
 **sid** | **String**| call sid |
 **convertedHelpRequestDto** | [**ConvertedHelpRequestDto**](ConvertedHelpRequestDto.md)|  |

### Return type

[**Call**](Call.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |
| **401** | Unauthorized |  -  |
| **404** | Couldn&#39;t find call or help request |  -  |


## callsControllerGetCallUrl

> File callsControllerGetCallUrl(sid)

Redirects the request to the stored record file.

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.CallsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        CallsApi apiInstance = new CallsApi(defaultClient);
        String sid = "sid_example"; // String | 
        try {
            File result = apiInstance.callsControllerGetCallUrl(sid);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CallsApi#callsControllerGetCallUrl");
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
 **sid** | **String**|  |

### Return type

[**File**](File.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: audio/x-wav

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |
| **401** | Unauthorized |  -  |
| **404** | Recording not found. |  -  |


## callsControllerGetNumber

> String callsControllerGetNumber()

Returns available numbers

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.models.*;
import app.nexd.android.api.CallsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");

        CallsApi apiInstance = new CallsApi(defaultClient);
        try {
            String result = apiInstance.callsControllerGetNumber();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CallsApi#callsControllerGetNumber");
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

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: applicaton/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |

