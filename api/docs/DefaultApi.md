# DefaultApi

All URIs are relative to *https://nexd-backend-staging.herokuapp.com:443/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**appControllerGetHello**](DefaultApi.md#appControllerGetHello) | **GET**  | 



## appControllerGetHello

> String appControllerGetHello()



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.models.*;
import app.nexd.android.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://nexd-backend-staging.herokuapp.com:443/api/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        try {
            String result = apiInstance.appControllerGetHello();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#appControllerGetHello");
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
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** |  |  -  |

