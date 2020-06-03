# UsersApi

All URIs are relative to *https://api-staging.nexd.app:443/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**userControllerFindMe**](UsersApi.md#userControllerFindMe) | **GET** users/me | Get user profile of the requesting user
[**userControllerFindOne**](UsersApi.md#userControllerFindOne) | **GET** users/{userId} | Get user profile of a specific user
[**userControllerGetAll**](UsersApi.md#userControllerGetAll) | **GET** users | Get all users
[**userControllerUpdate**](UsersApi.md#userControllerUpdate) | **PUT** users/{userId} | Update profile of a specific user
[**userControllerUpdateMyself**](UsersApi.md#userControllerUpdateMyself) | **PUT** users/me | Update profile of the requesting user



## userControllerFindMe

> User userControllerFindMe()

Get user profile of the requesting user

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.UsersApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api-staging.nexd.app:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        UsersApi apiInstance = new UsersApi(defaultClient);
        try {
            User result = apiInstance.userControllerFindMe();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UsersApi#userControllerFindMe");
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


## userControllerFindOne

> User userControllerFindOne(userId)

Get user profile of a specific user

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.UsersApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api-staging.nexd.app:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        UsersApi apiInstance = new UsersApi(defaultClient);
        String userId = "userId_example"; // String | user id
        try {
            User result = apiInstance.userControllerFindOne(userId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UsersApi#userControllerFindOne");
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
 **userId** | **String**| user id |

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

> List&lt;User&gt; userControllerGetAll(xAdminSecret)

Get all users

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.UsersApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api-staging.nexd.app:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        UsersApi apiInstance = new UsersApi(defaultClient);
        String xAdminSecret = "xAdminSecret_example"; // String | Secret to access the admin functions.
        try {
            List<User> result = apiInstance.userControllerGetAll(xAdminSecret);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UsersApi#userControllerGetAll");
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
 **xAdminSecret** | **String**| Secret to access the admin functions. |

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

> User userControllerUpdate(userId, updateUserDto)

Update profile of a specific user

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.UsersApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api-staging.nexd.app:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        UsersApi apiInstance = new UsersApi(defaultClient);
        String userId = "userId_example"; // String | user id
        UpdateUserDto updateUserDto = new UpdateUserDto(); // UpdateUserDto | 
        try {
            User result = apiInstance.userControllerUpdate(userId, updateUserDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UsersApi#userControllerUpdate");
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
 **userId** | **String**| user id |
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


## userControllerUpdateMyself

> User userControllerUpdateMyself(updateUserDto)

Update profile of the requesting user

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.UsersApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api-staging.nexd.app:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        UsersApi apiInstance = new UsersApi(defaultClient);
        UpdateUserDto updateUserDto = new UpdateUserDto(); // UpdateUserDto | 
        try {
            User result = apiInstance.userControllerUpdateMyself(updateUserDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UsersApi#userControllerUpdateMyself");
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

