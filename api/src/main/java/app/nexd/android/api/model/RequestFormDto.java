/*
 * Nexd API
 * Swagger API description
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package app.nexd.android.api.model;

import java.util.Objects;
import java.util.Arrays;
import app.nexd.android.api.model.CreateRequestArticleDto;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * RequestFormDto
 */

public class RequestFormDto {
  public static final String SERIALIZED_NAME_STREET = "street";
  @SerializedName(SERIALIZED_NAME_STREET)
  private String street;

  public static final String SERIALIZED_NAME_NUMBER = "number";
  @SerializedName(SERIALIZED_NAME_NUMBER)
  private String number;

  public static final String SERIALIZED_NAME_ZIP_CODE = "zipCode";
  @SerializedName(SERIALIZED_NAME_ZIP_CODE)
  private String zipCode;

  public static final String SERIALIZED_NAME_CITY = "city";
  @SerializedName(SERIALIZED_NAME_CITY)
  private String city;

  public static final String SERIALIZED_NAME_ARTICLES = "articles";
  @SerializedName(SERIALIZED_NAME_ARTICLES)
  private List<CreateRequestArticleDto> articles = new ArrayList<CreateRequestArticleDto>();

  /**
   * Gets or Sets status
   */
  @JsonAdapter(StatusEnum.Adapter.class)
  public enum StatusEnum {
    PENDING("pending"),
    
    ONGOING("ongoing"),
    
    COMPLETED("completed");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<StatusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StatusEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return StatusEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private StatusEnum status = StatusEnum.PENDING;

  public static final String SERIALIZED_NAME_ADDITIONAL_REQUEST = "additionalRequest";
  @SerializedName(SERIALIZED_NAME_ADDITIONAL_REQUEST)
  private String additionalRequest;

  public static final String SERIALIZED_NAME_DELIVERY_COMMENT = "deliveryComment";
  @SerializedName(SERIALIZED_NAME_DELIVERY_COMMENT)
  private String deliveryComment;

  public static final String SERIALIZED_NAME_PHONE_NUMBER = "phoneNumber";
  @SerializedName(SERIALIZED_NAME_PHONE_NUMBER)
  private String phoneNumber;


  public RequestFormDto street(String street) {
    
    this.street = street;
    return this;
  }

   /**
   * Get street
   * @return street
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getStreet() {
    return street;
  }


  public void setStreet(String street) {
    this.street = street;
  }


  public RequestFormDto number(String number) {
    
    this.number = number;
    return this;
  }

   /**
   * Get number
   * @return number
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getNumber() {
    return number;
  }


  public void setNumber(String number) {
    this.number = number;
  }


  public RequestFormDto zipCode(String zipCode) {
    
    this.zipCode = zipCode;
    return this;
  }

   /**
   * Get zipCode
   * @return zipCode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getZipCode() {
    return zipCode;
  }


  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }


  public RequestFormDto city(String city) {
    
    this.city = city;
    return this;
  }

   /**
   * Get city
   * @return city
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getCity() {
    return city;
  }


  public void setCity(String city) {
    this.city = city;
  }


  public RequestFormDto articles(List<CreateRequestArticleDto> articles) {
    
    this.articles = articles;
    return this;
  }

  public RequestFormDto addArticlesItem(CreateRequestArticleDto articlesItem) {
    this.articles.add(articlesItem);
    return this;
  }

   /**
   * List of articles
   * @return articles
  **/
  @ApiModelProperty(required = true, value = "List of articles")

  public List<CreateRequestArticleDto> getArticles() {
    return articles;
  }


  public void setArticles(List<CreateRequestArticleDto> articles) {
    this.articles = articles;
  }


  public RequestFormDto status(StatusEnum status) {
    
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public StatusEnum getStatus() {
    return status;
  }


  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  public RequestFormDto additionalRequest(String additionalRequest) {
    
    this.additionalRequest = additionalRequest;
    return this;
  }

   /**
   * Get additionalRequest
   * @return additionalRequest
  **/
  @ApiModelProperty(required = true, value = "")

  public String getAdditionalRequest() {
    return additionalRequest;
  }


  public void setAdditionalRequest(String additionalRequest) {
    this.additionalRequest = additionalRequest;
  }


  public RequestFormDto deliveryComment(String deliveryComment) {
    
    this.deliveryComment = deliveryComment;
    return this;
  }

   /**
   * Get deliveryComment
   * @return deliveryComment
  **/
  @ApiModelProperty(required = true, value = "")

  public String getDeliveryComment() {
    return deliveryComment;
  }


  public void setDeliveryComment(String deliveryComment) {
    this.deliveryComment = deliveryComment;
  }


  public RequestFormDto phoneNumber(String phoneNumber) {
    
    this.phoneNumber = phoneNumber;
    return this;
  }

   /**
   * Get phoneNumber
   * @return phoneNumber
  **/
  @ApiModelProperty(required = true, value = "")

  public String getPhoneNumber() {
    return phoneNumber;
  }


  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestFormDto requestFormDto = (RequestFormDto) o;
    return Objects.equals(this.street, requestFormDto.street) &&
        Objects.equals(this.number, requestFormDto.number) &&
        Objects.equals(this.zipCode, requestFormDto.zipCode) &&
        Objects.equals(this.city, requestFormDto.city) &&
        Objects.equals(this.articles, requestFormDto.articles) &&
        Objects.equals(this.status, requestFormDto.status) &&
        Objects.equals(this.additionalRequest, requestFormDto.additionalRequest) &&
        Objects.equals(this.deliveryComment, requestFormDto.deliveryComment) &&
        Objects.equals(this.phoneNumber, requestFormDto.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(street, number, zipCode, city, articles, status, additionalRequest, deliveryComment, phoneNumber);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestFormDto {\n");
    sb.append("    street: ").append(toIndentedString(street)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    zipCode: ").append(toIndentedString(zipCode)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    articles: ").append(toIndentedString(articles)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    additionalRequest: ").append(toIndentedString(additionalRequest)).append("\n");
    sb.append("    deliveryComment: ").append(toIndentedString(deliveryComment)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
