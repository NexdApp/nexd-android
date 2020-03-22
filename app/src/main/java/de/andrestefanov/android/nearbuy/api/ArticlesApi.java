package de.andrestefanov.android.nearbuy.api;

import de.andrestefanov.android.nearbuy.CollectionFormats.*;

import io.reactivex.Observable;
import io.reactivex.Completable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import de.andrestefanov.android.nearbuy.api.model.Article;
import de.andrestefanov.android.nearbuy.api.model.CreateArticleDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ArticlesApi {
  /**
   * 
   * 
   * @return Observable&lt;List&lt;Article&gt;&gt;
   */
  @GET("api/articles")
  Observable<List<Article>> articlesControllerFindAll();
    

  /**
   * 
   * 
   * @param createArticleDto  (required)
   * @return Observable&lt;Article&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/articles")
  Observable<Article> articlesControllerInsertOne(
    @retrofit2.http.Body CreateArticleDto createArticleDto
  );

}
