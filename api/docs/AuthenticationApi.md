# AuthenticationApi

All URIs are relative to *http://localhost:3001*

Method | HTTP request | Description
------------- | ------------- | -------------
[**authControllerLogin**](AuthenticationApi.md#authControllerLogin) | **POST** api/auth/login | 
[**authControllerRegister**](AuthenticationApi.md#authControllerRegister) | **POST** api/auth/register | 



## authControllerLogin

> ResponseTokenDto authControllerLogin(loginPayload)



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.models.*;
import app.nexd.android.api.AuthenticationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:3001");

        AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
        LoginPayload loginPayload = new LoginPayload(); // LoginPayload | 
        try {
            ResponseTokenDto result = apiInstance.authControllerLogin(loginPayload);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthenticationApi#authControllerLogin");
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
 **loginPayload** | [**LoginPayload**](LoginPayload.md)|  |

### Return type

[**ResponseTokenDto**](ResponseTokenDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Login |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |


## authControllerRegister

> ResponseTokenDto authControllerRegister(registerPayload)



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.models.*;
import app.nexd.android.api.AuthenticationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:3001");

        AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
        RegisterPayload registerPayload = new RegisterPayload(); // RegisterPayload | 
        try {
            ResponseTokenDto result = apiInstance.authControllerRegister(registerPayload);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthenticationApi#authControllerRegister");
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
 **registerPayload** | [**RegisterPayload**](RegisterPayload.md)|  |

### Return type

[**ResponseTokenDto**](ResponseTokenDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Successful Registration |  -  |
| **400** | Bad Request |  -  |
| **406** | Already exists |  -  |
