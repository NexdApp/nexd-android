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

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * CreateHelpRequestArticleDto
 */

public class CreateHelpRequestArticleDto {
  public static final String SERIALIZED_NAME_ARTICLE_ID = "articleId";
  @SerializedName(SERIALIZED_NAME_ARTICLE_ID)
  private Long articleId;

  public static final String SERIALIZED_NAME_ARTICLE_NAME = "articleName";
  @SerializedName(SERIALIZED_NAME_ARTICLE_NAME)
  private String articleName;

  /**
   * Language of the article, e.g. the user
   */
  @JsonAdapter(LanguageEnum.Adapter.class)
  public enum LanguageEnum {
    DE("de"),
    
    EN("en");

    private String value;

    LanguageEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static LanguageEnum fromValue(String value) {
      for (LanguageEnum b : LanguageEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<LanguageEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final LanguageEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public LanguageEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return LanguageEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_LANGUAGE = "language";
  @SerializedName(SERIALIZED_NAME_LANGUAGE)
  private LanguageEnum language;

  public static final String SERIALIZED_NAME_ARTICLE_COUNT = "articleCount";
  @SerializedName(SERIALIZED_NAME_ARTICLE_COUNT)
  private Long articleCount;

  public static final String SERIALIZED_NAME_UNIT_ID = "unitId";
  @SerializedName(SERIALIZED_NAME_UNIT_ID)
  private Long unitId;


  public CreateHelpRequestArticleDto articleId(Long articleId) {
    
    this.articleId = articleId;
    return this;
  }

   /**
   * Article ID received from the article list
   * @return articleId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Article ID received from the article list")

  public Long getArticleId() {
    return articleId;
  }


  public void setArticleId(Long articleId) {
    this.articleId = articleId;
  }


  public CreateHelpRequestArticleDto articleName(String articleName) {
    
    this.articleName = articleName;
    return this;
  }

   /**
   * Name of the article, in case a new one should be created
   * @return articleName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Name of the article, in case a new one should be created")

  public String getArticleName() {
    return articleName;
  }


  public void setArticleName(String articleName) {
    this.articleName = articleName;
  }


  public CreateHelpRequestArticleDto language(LanguageEnum language) {
    
    this.language = language;
    return this;
  }

   /**
   * Language of the article, e.g. the user
   * @return language
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Language of the article, e.g. the user")

  public LanguageEnum getLanguage() {
    return language;
  }


  public void setLanguage(LanguageEnum language) {
    this.language = language;
  }


  public CreateHelpRequestArticleDto articleCount(Long articleCount) {
    
    this.articleCount = articleCount;
    return this;
  }

   /**
   * Number of items
   * @return articleCount
  **/
  @ApiModelProperty(required = true, value = "Number of items")

  public Long getArticleCount() {
    return articleCount;
  }


  public void setArticleCount(Long articleCount) {
    this.articleCount = articleCount;
  }


  public CreateHelpRequestArticleDto unitId(Long unitId) {
    
    this.unitId = unitId;
    return this;
  }

   /**
   * Id of the unit
   * minimum: 0
   * @return unitId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Id of the unit")

  public Long getUnitId() {
    return unitId;
  }


  public void setUnitId(Long unitId) {
    this.unitId = unitId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateHelpRequestArticleDto createHelpRequestArticleDto = (CreateHelpRequestArticleDto) o;
    return Objects.equals(this.articleId, createHelpRequestArticleDto.articleId) &&
        Objects.equals(this.articleName, createHelpRequestArticleDto.articleName) &&
        Objects.equals(this.language, createHelpRequestArticleDto.language) &&
        Objects.equals(this.articleCount, createHelpRequestArticleDto.articleCount) &&
        Objects.equals(this.unitId, createHelpRequestArticleDto.unitId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(articleId, articleName, language, articleCount, unitId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateHelpRequestArticleDto {\n");
    sb.append("    articleId: ").append(toIndentedString(articleId)).append("\n");
    sb.append("    articleName: ").append(toIndentedString(articleName)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    articleCount: ").append(toIndentedString(articleCount)).append("\n");
    sb.append("    unitId: ").append(toIndentedString(unitId)).append("\n");
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

