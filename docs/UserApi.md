# UserApi

All URIs are relative to *http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**userControllerFindOne**](UserApi.md#userControllerFindOne) | **GET** api/user/{id} | 
[**userControllerGetAll**](UserApi.md#userControllerGetAll) | **GET** api/user | 
[**userControllerUpdate**](UserApi.md#userControllerUpdate) | **PUT** api/user/{id} | 



## userControllerFindOne

> User userControllerFindOne(id)



### Example

```java
// Import classes:
import de.andrestefanov.android.nearbuy.ApiClient;
import de.andrestefanov.android.nearbuy.ApiException;
import de.andrestefanov.android.nearbuy.Configuration;
import de.andrestefanov.android.nearbuy.auth.*;
import de.andrestefanov.android.nearbuy.models.*;
import de.andrestefanov.android.nearbuy.api.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        UserApi apiInstance = new UserApi(defaultClient);
        Integer id = 56; // Integer | user id
        try {
            User result = apiInstance.userControllerFindOne(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#userControllerFindOne");
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
 **id** | **Integer**| user id |

### Return type

[**User**](User.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |
| **400** | Bad Request |  -  |
| **404** | User not found |  -  |


## userControllerGetAll

> List&lt;User&gt; userControllerGetAll()



### Example

```java
// Import classes:
import de.andrestefanov.android.nearbuy.ApiClient;
import de.andrestefanov.android.nearbuy.ApiException;
import de.andrestefanov.android.nearbuy.Configuration;
import de.andrestefanov.android.nearbuy.auth.*;
import de.andrestefanov.android.nearbuy.models.*;
import de.andrestefanov.android.nearbuy.api.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        UserApi apiInstance = new UserApi(defaultClient);
        try {
            List<User> result = apiInstance.userControllerGetAll();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#userControllerGetAll");
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

[**List&lt;User&gt;**](User.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |


## userControllerUpdate

> User userControllerUpdate(id, updateUserDto)



### Example

```java
// Import classes:
import de.andrestefanov.android.nearbuy.ApiClient;
import de.andrestefanov.android.nearbuy.ApiException;
import de.andrestefanov.android.nearbuy.Configuration;
import de.andrestefanov.android.nearbuy.auth.*;
import de.andrestefanov.android.nearbuy.models.*;
import de.andrestefanov.android.nearbuy.api.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        UserApi apiInstance = new UserApi(defaultClient);
        Integer id = 56; // Integer | user id
        UpdateUserDto updateUserDto = new UpdateUserDto(); // UpdateUserDto | 
        try {
            User result = apiInstance.userControllerUpdate(id, updateUserDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#userControllerUpdate");
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
 **id** | **Integer**| user id |
 **updateUserDto** | [**UpdateUserDto**](UpdateUserDto.md)|  |

### Return type

[**User**](User.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful |  -  |
| **400** | Bad Request |  -  |
| **403** | Forbidden |  -  |
| **404** | User not found |  -  |

