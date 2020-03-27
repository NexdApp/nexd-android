package app.nexd.android.api;

import app.nexd.android.CollectionFormats.*;

import io.reactivex.Observable;
import io.reactivex.Completable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import java.math.BigDecimal;
import app.nexd.android.api.model.RequestArticleStatusDto;
import app.nexd.android.api.model.RequestEntity;
import app.nexd.android.api.model.RequestFormDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RequestApi {
  /**
   * 
   * 
   * @param onlyMine if \&quot;true\&quot;, only the requesting user requests will be replied. (optional)
   * @param zipCode if set, only requests within the same zip code will be replied (optional)
   * @return Observable&lt;List&lt;RequestEntity&gt;&gt;
   */
  @GET("api/request")
  Observable<List<RequestEntity>> requestControllerGetAll(
    @retrofit2.http.Query("onlyMine") String onlyMine, @retrofit2.http.Query("zipCode") String zipCode
  );

  /**
   * 
   * 
   * @param requestId  (required)
   * @return Observable&lt;RequestEntity&gt;
   */
  @GET("api/request/{requestId}")
  Observable<RequestEntity> requestControllerGetSingleRequest(
    @retrofit2.http.Path("requestId") BigDecimal requestId
  );

  /**
   * 
   * 
   * @param requestFormDto  (required)
   * @return Observable&lt;RequestEntity&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/request")
  Observable<RequestEntity> requestControllerInsertRequestWithArticles(
    @retrofit2.http.Body RequestFormDto requestFormDto
  );

  /**
   * 
   * 
   * @param requestId  (required)
   * @param articleId  (required)
   * @param requestArticleStatusDto  (required)
   * @return Observable&lt;RequestEntity&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("api/request/{requestId}/{articleId}")
  Observable<RequestEntity> requestControllerMarkArticleAsDone(
    @retrofit2.http.Path("requestId") BigDecimal requestId, @retrofit2.http.Path("articleId") BigDecimal articleId, @retrofit2.http.Body RequestArticleStatusDto requestArticleStatusDto
  );

  /**
   * 
   * 
   * @param requestId  (required)
   * @param requestFormDto  (required)
   * @return Observable&lt;RequestEntity&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("api/request/{requestId}")
  Observable<RequestEntity> requestControllerUpdateRequest(
    @retrofit2.http.Path("requestId") BigDecimal requestId, @retrofit2.http.Body RequestFormDto requestFormDto
  );

}
