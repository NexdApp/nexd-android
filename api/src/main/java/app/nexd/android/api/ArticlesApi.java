package app.nexd.android.api;

import java.util.List;

import app.nexd.android.api.model.Article;
import app.nexd.android.api.model.CreateArticleDto;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ArticlesApi {
  /**
   * List articles
   * 
   * @return Observable&lt;List&lt;Article&gt;&gt;
   */
  @GET("article/articles")
  Observable<List<Article>> articlesControllerFindAll();
    

  /**
   * Create an article
   * 
   * @param xAdminSecret Secret to access the admin functions. (required)
   * @param createArticleDto  (required)
   * @return Observable&lt;Article&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("article/articles")
  Observable<Article> articlesControllerInsertOne(
    @retrofit2.http.Header("x-admin-secret") String xAdminSecret, @retrofit2.http.Body CreateArticleDto createArticleDto
  );

}
