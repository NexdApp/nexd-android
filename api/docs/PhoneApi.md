# PhoneApi

All URIs are relative to *http://localhost:3001/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**phoneControllerConverted**](PhoneApi.md#phoneControllerConverted) | **POST** phone/calls/{sid}/help-request | Creates a new help request for a call and creates a user for the phoneNumber
[**phoneControllerGetCalls**](PhoneApi.md#phoneControllerGetCalls) | **GET** phone/calls | Returns all calls with the given parameters
[**phoneControllerGetNumbers**](PhoneApi.md#phoneControllerGetNumbers) | **GET** phone/numbers | Returns available numbers



## phoneControllerConverted

> Call phoneControllerConverted(sid, helpRequestCreateDto)

Creates a new help request for a call and creates a user for the phoneNumber

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.PhoneApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:3001/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        PhoneApi apiInstance = new PhoneApi(defaultClient);
        String sid = "sid_example"; // String | call sid
        HelpRequestCreateDto helpRequestCreateDto = new HelpRequestCreateDto(); // HelpRequestCreateDto | 
        try {
            Call result = apiInstance.phoneControllerConverted(sid, helpRequestCreateDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling PhoneApi#phoneControllerConverted");
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
 **helpRequestCreateDto** | [**HelpRequestCreateDto**](HelpRequestCreateDto.md)|  |

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
| **201** | Successful |  -  |
| **404** | Couldn&#39;t find call or help request |  -  |


## phoneControllerGetCalls

> List&lt;Call&gt; phoneControllerGetCalls(userId, limit, converted, country, zip, city)

Returns all calls with the given parameters

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.PhoneApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:3001/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        PhoneApi apiInstance = new PhoneApi(defaultClient);
        String userId = "userId_example"; // String | If included, filter by userId, \"me\" for the requesting user, otherwise all users are replied. 
        Long limit = 56L; // Long | 
        Boolean converted = true; // Boolean | true if you only want to query calls which are already converted to a        'help request, false otherwise. Returns all calls if undefined.
        String country = "country_example"; // String | 
        String zip = "zip_example"; // String | 
        String city = "city_example"; // String | 
        try {
            List<Call> result = apiInstance.phoneControllerGetCalls(userId, limit, converted, country, zip, city);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling PhoneApi#phoneControllerGetCalls");
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
 **userId** | **String**| If included, filter by userId, \&quot;me\&quot; for the requesting user, otherwise all users are replied.  | [optional]
 **limit** | **Long**|  | [optional]
 **converted** | **Boolean**| true if you only want to query calls which are already converted to a        &#39;help request, false otherwise. Returns all calls if undefined. | [optional]
 **country** | **String**|  | [optional]
 **zip** | **String**|  | [optional]
 **city** | **String**|  | [optional]

### Return type

[**List&lt;Call&gt;**](Call.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |


## phoneControllerGetNumbers

> List&lt;PhoneNumberDto&gt; phoneControllerGetNumbers()

Returns available numbers

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.models.*;
import app.nexd.android.api.PhoneApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:3001/api/v1");

        PhoneApi apiInstance = new PhoneApi(defaultClient);
        try {
            List<PhoneNumberDto> result = apiInstance.phoneControllerGetNumbers();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling PhoneApi#phoneControllerGetNumbers");
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

[**List&lt;PhoneNumberDto&gt;**](PhoneNumberDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** |  |  -  |

