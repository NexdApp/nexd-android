# ArticlesApi

All URIs are relative to *http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**articlesControllerFindAll**](ArticlesApi.md#articlesControllerFindAll) | **GET** api/articles | 
[**articlesControllerInsertOne**](ArticlesApi.md#articlesControllerInsertOne) | **POST** api/articles | 



## articlesControllerFindAll

> List&lt;Article&gt; articlesControllerFindAll()



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.models.*;
import app.nexd.android.api.ArticlesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com");

        ArticlesApi apiInstance = new ArticlesApi(defaultClient);
        try {
            List<Article> result = apiInstance.articlesControllerFindAll();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ArticlesApi#articlesControllerFindAll");
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

[**List&lt;Article&gt;**](Article.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | All existing articles |  -  |
| **400** | Bad Request |  -  |


## articlesControllerInsertOne

> Article articlesControllerInsertOne(createArticleDto)



### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.models.*;
import app.nexd.android.api.ArticlesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://nexd-api-alb-1107636132.eu-central-1.elb.amazonaws.com");

        ArticlesApi apiInstance = new ArticlesApi(defaultClient);
        CreateArticleDto createArticleDto = new CreateArticleDto(); // CreateArticleDto | 
        try {
            Article result = apiInstance.articlesControllerInsertOne(createArticleDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ArticlesApi#articlesControllerInsertOne");
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
 **createArticleDto** | [**CreateArticleDto**](CreateArticleDto.md)|  |

### Return type

[**Article**](Article.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Created |  -  |
| **400** | Bad Request |  -  |

