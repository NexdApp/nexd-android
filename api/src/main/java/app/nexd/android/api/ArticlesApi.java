package app.nexd.android.api;

import java.util.List;

import app.nexd.android.api.model.Article;
import app.nexd.android.api.model.AvailableLanguages;
import app.nexd.android.api.model.CreateArticleDto;
import app.nexd.android.api.model.Unit;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ArticlesApi {
  /**
   * List articles
   * 
   * @param limit Maximum number of articles  (optional)
   * @param startsWith Starts with the given string. Empty string does not filter. (optional)
   * @param language  (optional)
   * @param onlyVerified true to only gets the list of curated articles (default: true) (optional)
   * @return Observable&lt;List&lt;Article&gt;&gt;
   */
  @GET("article/articles")
  Observable<List<Article>> articlesControllerFindAll(
    @retrofit2.http.Query("limit") Long limit, @retrofit2.http.Query("startsWith") String startsWith, @retrofit2.http.Query("language") AvailableLanguages language, @retrofit2.http.Query("onlyVerified") Boolean onlyVerified
  );

  /**
   * Get a list of units
   * 
   * @param language  (optional)
   * @return Observable&lt;List&lt;Unit&gt;&gt;
   */
  @GET("article/units")
  Observable<List<Unit>> articlesControllerGetUnits(
    @retrofit2.http.Query("language") AvailableLanguages language
  );

  /**
   * Create an article
   * 
   * @param createArticleDto  (required)
   * @return Observable&lt;Article&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("article/articles")
  Observable<Article> articlesControllerInsertOne(
    @retrofit2.http.Body CreateArticleDto createArticleDto
  );

}
