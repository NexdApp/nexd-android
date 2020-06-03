# ArticlesApi

All URIs are relative to *https://api-staging.nexd.app:443/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**articlesControllerFindAll**](ArticlesApi.md#articlesControllerFindAll) | **GET** article/articles | List articles
[**articlesControllerGetUnits**](ArticlesApi.md#articlesControllerGetUnits) | **GET** article/units | Get a list of units
[**articlesControllerInsertOne**](ArticlesApi.md#articlesControllerInsertOne) | **POST** article/articles | Create an article



## articlesControllerFindAll

> List&lt;Article&gt; articlesControllerFindAll(limit, startsWith, contains, orderByPopularity, language, onlyVerified)

List articles

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.ArticlesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api-staging.nexd.app:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        ArticlesApi apiInstance = new ArticlesApi(defaultClient);
        Long limit = 56L; // Long | Maximum number of articles 
        String startsWith = "startsWith_example"; // String | Starts with the given string. Empty string does not filter.
        String contains = "contains_example"; // String | Contains with the given string. Empty string does not filter.
        Boolean orderByPopularity = true; // Boolean | If true, orders by the most frequent used articles first. Defaults to false.
        AvailableLanguages language = new AvailableLanguages(); // AvailableLanguages | 
        Boolean onlyVerified = true; // Boolean | true to only gets the list of curated articles (default: true)
        try {
            List<Article> result = apiInstance.articlesControllerFindAll(limit, startsWith, contains, orderByPopularity, language, onlyVerified);
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


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **limit** | **Long**| Maximum number of articles  | [optional]
 **startsWith** | **String**| Starts with the given string. Empty string does not filter. | [optional]
 **contains** | **String**| Contains with the given string. Empty string does not filter. | [optional]
 **orderByPopularity** | **Boolean**| If true, orders by the most frequent used articles first. Defaults to false. | [optional]
 **language** | [**AvailableLanguages**](.md)|  | [optional] [enum: de, en]
 **onlyVerified** | **Boolean**| true to only gets the list of curated articles (default: true) | [optional]

### Return type

[**List&lt;Article&gt;**](Article.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | All existing articles |  -  |
| **400** | Bad Request |  -  |


## articlesControllerGetUnits

> List&lt;Unit&gt; articlesControllerGetUnits(language)

Get a list of units

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.ArticlesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api-staging.nexd.app:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

        ArticlesApi apiInstance = new ArticlesApi(defaultClient);
        AvailableLanguages language = new AvailableLanguages(); // AvailableLanguages | 
        try {
            List<Unit> result = apiInstance.articlesControllerGetUnits(language);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ArticlesApi#articlesControllerGetUnits");
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
 **language** | [**AvailableLanguages**](.md)|  | [optional] [enum: de, en]

### Return type

[**List&lt;Unit&gt;**](Unit.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** |  |  -  |
| **400** | Bad Request |  -  |


## articlesControllerInsertOne

> Article articlesControllerInsertOne(createArticleDto)

Create an article

### Example

```java
// Import classes:
import app.nexd.android.ApiClient;
import app.nexd.android.ApiException;
import app.nexd.android.Configuration;
import app.nexd.android.auth.*;
import app.nexd.android.models.*;
import app.nexd.android.api.ArticlesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api-staging.nexd.app:443/api/v1");
        
        // Configure HTTP bearer authorization: bearer
        HttpBearerAuth bearer = (HttpBearerAuth) defaultClient.getAuthentication("bearer");
        bearer.setBearerToken("BEARER TOKEN");

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

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Created |  -  |
| **400** | Bad Request |  -  |

