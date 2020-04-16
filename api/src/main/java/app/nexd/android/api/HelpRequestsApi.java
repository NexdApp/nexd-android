package app.nexd.android.api;

import app.nexd.android.CollectionFormats.*;

import io.reactivex.Observable;
import io.reactivex.Completable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import app.nexd.android.api.model.CreateOrUpdateHelpRequestArticleDto;
import app.nexd.android.api.model.HelpRequest;
import app.nexd.android.api.model.HelpRequestCreateDto;
import app.nexd.android.api.model.HelpRequestStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface HelpRequestsApi {
  /**
   * Put an article to a help request, endpoint overrides.
   * 
   * @param helpRequestId Id of the help request (required)
   * @param articleId Id of the article (required)
   * @param createOrUpdateHelpRequestArticleDto  (required)
   * @return Observable&lt;HelpRequest&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("help-requests/{helpRequestId}/article/{articleId}")
  Observable<HelpRequest> helpRequestsControllerAddArticleInHelpRequest(
    @retrofit2.http.Path("helpRequestId") Long helpRequestId, @retrofit2.http.Path("articleId") Long articleId, @retrofit2.http.Body CreateOrUpdateHelpRequestArticleDto createOrUpdateHelpRequestArticleDto
  );

  /**
   * Get and filter for various help requests
   * 
   * @param userId If included, filter by userId, \&quot;me\&quot; for the requesting user, otherwise all users are replied. The excludeUserId query inverts the logic and excludes the given userId.  (optional)
   * @param excludeUserId If true, the given userId (in query) is excluded (and not filtered for as default). Requires the userId query. (optional)
   * @param zipCode Filter by an array of zipCodes (optional, default to new ArrayList&lt;String&gt;())
   * @param includeRequester If \&quot;true\&quot;, the requester object is included in each help request (optional)
   * @param status Array of status to filter for (optional, default to new ArrayList&lt;HelpRequestStatus&gt;())
   * @return Observable&lt;List&lt;HelpRequest&gt;&gt;
   */
  @GET("help-requests")
  Observable<List<HelpRequest>> helpRequestsControllerGetAll(
    @retrofit2.http.Query("userId") String userId, @retrofit2.http.Query("excludeUserId") Boolean excludeUserId, @retrofit2.http.Query("zipCode") List<String> zipCode, @retrofit2.http.Query("includeRequester") Boolean includeRequester, @retrofit2.http.Query("status") List<HelpRequestStatus> status
  );

  /**
   * Get a single help request by id
   * 
   * @param helpRequestId Id of the help request (required)
   * @return Observable&lt;HelpRequest&gt;
   */
  @GET("help-requests/{helpRequestId}")
  Observable<HelpRequest> helpRequestsControllerGetSingleRequest(
    @retrofit2.http.Path("helpRequestId") Long helpRequestId
  );

  /**
   * Add a help request
   * 
   * @param helpRequestCreateDto  (required)
   * @return Observable&lt;HelpRequest&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("help-requests")
  Observable<HelpRequest> helpRequestsControllerInsertRequestWithArticles(
    @retrofit2.http.Body HelpRequestCreateDto helpRequestCreateDto
  );

  /**
   * Remove an article from a help request
   * 
   * @param helpRequestId Id of the help request (required)
   * @param articleId Id of the article (required)
   * @return Observable&lt;HelpRequest&gt;
   */
  @DELETE("help-requests/{helpRequestId}/article/{articleId}")
  Observable<HelpRequest> helpRequestsControllerRemoveArticleInHelpRequest(
    @retrofit2.http.Path("helpRequestId") Long helpRequestId, @retrofit2.http.Path("articleId") Long articleId
  );

  /**
   * Modify a help request (e.g. address or articles)
   * 
   * @param helpRequestId Id of the help request (required)
   * @param helpRequestCreateDto  (required)
   * @return Observable&lt;HelpRequest&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("help-requests/{helpRequestId}")
  Observable<HelpRequest> helpRequestsControllerUpdateRequest(
    @retrofit2.http.Path("helpRequestId") Long helpRequestId, @retrofit2.http.Body HelpRequestCreateDto helpRequestCreateDto
  );

}
