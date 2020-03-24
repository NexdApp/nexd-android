# AuthenticationApi

All URIs are relative to *http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**authControllerLogin**](AuthenticationApi.md#authControllerLogin) | **POST** api/auth/login | 
[**authControllerRegister**](AuthenticationApi.md#authControllerRegister) | **POST** api/auth/register | 



## authControllerLogin

> ResponseTokenDto authControllerLogin(loginPayload)



### Example

```java
// Import classes:
import de.andrestefanov.android.nearbuy.ApiClient;
import de.andrestefanov.android.nearbuy.ApiException;
import de.andrestefanov.android.nearbuy.Configuration;
import de.andrestefanov.android.nearbuy.models.*;
import de.andrestefanov.android.nearbuy.api.AuthenticationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com");

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
import de.andrestefanov.android.nearbuy.ApiClient;
import de.andrestefanov.android.nearbuy.ApiException;
import de.andrestefanov.android.nearbuy.Configuration;
import de.andrestefanov.android.nearbuy.models.*;
import de.andrestefanov.android.nearbuy.api.AuthenticationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com");

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

