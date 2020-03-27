# DefaultApi

All URIs are relative to *http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**appControllerRoot**](DefaultApi.md#appControllerRoot) | **GET** api | 



## appControllerRoot

> appControllerRoot()



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
        defaultClient.setBasePath("http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        try {
            apiInstance.appControllerRoot();
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#appControllerRoot");
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

