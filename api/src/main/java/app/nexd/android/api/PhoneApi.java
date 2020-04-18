package app.nexd.android.api;

import app.nexd.android.CollectionFormats.*;

import io.reactivex.Observable;
import io.reactivex.Completable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import app.nexd.android.api.model.Call;
import app.nexd.android.api.model.HelpRequestCreateDto;
import app.nexd.android.api.model.PhoneNumberDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PhoneApi {
  /**
   * Creates a new help request for a call and creates a user for the phoneNumber
   * 
   * @param sid call sid (required)
   * @param helpRequestCreateDto  (required)
   * @return Observable&lt;Call&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("phone/calls/{sid}/help-request")
  Observable<Call> phoneControllerConverted(
    @retrofit2.http.Path("sid") String sid, @retrofit2.http.Body HelpRequestCreateDto helpRequestCreateDto
  );

  /**
   * Returns all calls with the given parameters
   * 
   * @param userId If included, filter by userId, \&quot;me\&quot; for the requesting user, otherwise all users are replied.  (optional)
   * @param limit  (optional)
   * @param converted true if you only want to query calls which are already converted to a        &#39;help request, false otherwise. Returns all calls if undefined. (optional)
   * @param country  (optional)
   * @param zip  (optional)
   * @param city  (optional)
   * @return Observable&lt;List&lt;Call&gt;&gt;
   */
  @GET("phone/calls")
  Observable<List<Call>> phoneControllerGetCalls(
    @retrofit2.http.Query("userId") String userId, @retrofit2.http.Query("limit") Long limit, @retrofit2.http.Query("converted") Boolean converted, @retrofit2.http.Query("country") String country, @retrofit2.http.Query("zip") String zip, @retrofit2.http.Query("city") String city
  );

  /**
   * Returns available numbers
   * 
   * @return Observable&lt;List&lt;PhoneNumberDto&gt;&gt;
   */
  @GET("phone/numbers")
  Observable<List<PhoneNumberDto>> phoneControllerGetNumbers();
    

}
