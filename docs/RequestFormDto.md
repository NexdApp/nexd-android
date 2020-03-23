

# RequestFormDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**street** | **String** |  |  [optional]
**number** | **String** |  |  [optional]
**zipCode** | **String** |  |  [optional]
**city** | **String** |  |  [optional]
**articles** | [**List&lt;CreateRequestArticleDto&gt;**](CreateRequestArticleDto.md) | List of articles | 
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**additionalRequest** | **String** |  | 
**deliveryComment** | **String** |  | 
**phoneNumber** | **String** |  | 



## Enum: StatusEnum

Name | Value
---- | -----
PENDING | &quot;pending&quot;
ONGOING | &quot;ongoing&quot;
COMPLETED | &quot;completed&quot;



