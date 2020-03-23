

# RequestEntity

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**street** | **String** |  |  [optional]
**number** | **String** |  |  [optional]
**zipCode** | **String** |  |  [optional]
**city** | **String** |  |  [optional]
**id** | **Integer** |  | 
**createdAt** | [**Date**](Date.md) |  | 
**requesterId** | **Integer** |  | 
**priority** | **String** |  | 
**additionalRequest** | **String** |  | 
**deliveryComment** | **String** |  | 
**phoneNumber** | **String** |  | 
**status** | [**StatusEnum**](#StatusEnum) |  | 
**articles** | [**List&lt;RequestArticle&gt;**](RequestArticle.md) |  | 
**requester** | [**User**](User.md) |  |  [optional]



## Enum: StatusEnum

Name | Value
---- | -----
PENDING | &quot;pending&quot;
ONGOING | &quot;ongoing&quot;
COMPLETED | &quot;completed&quot;



