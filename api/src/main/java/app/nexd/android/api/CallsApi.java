package app.nexd.android.api;

import app.nexd.android.CollectionFormats.*;

import io.reactivex.Observable;
import io.reactivex.Completable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import app.nexd.android.api.model.Call;
import app.nexd.android.api.model.CallQueryDto;
import app.nexd.android.api.model.ConvertedHelpRequestDto;
import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CallsApi {
  /**
   * Returns all calls with the given parameters
   * 
   * @param callQueryDto  (required)
   * @return Observable&lt;List&lt;Call&gt;&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("call/calls")
  Observable<List<Call>> callsControllerCalls(
    @retrofit2.http.Body CallQueryDto callQueryDto
  );

  /**
   * Sets a call as converted to shopping list
   * 
   * @param sid call sid (required)
   * @param convertedHelpRequestDto  (required)
   * @return Observable&lt;Call&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("call/calls/{sid}/converted")
  Observable<Call> callsControllerConverted(
    @retrofit2.http.Path("sid") String sid, @retrofit2.http.Body ConvertedHelpRequestDto convertedHelpRequestDto
  );

  /**
   * Redirects the request to the stored record file.
   * 
   * @param sid  (required)
   * @return Observable&lt;ResponseBody&gt;
   */
  @GET("call/calls/{sid}/record")
  Observable<ResponseBody> callsControllerGetCallUrl(
    @retrofit2.http.Path("sid") String sid
  );

  /**
   * Returns available numbers
   * 
   * @return Observable&lt;String&gt;
   */
  @GET("call/number")
  Observable<String> callsControllerGetNumber();
    

}
