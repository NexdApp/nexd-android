package app.nexd.android.api;

import app.nexd.android.CollectionFormats.*;

import io.reactivex.Observable;
import io.reactivex.Completable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import app.nexd.android.api.model.LoginPayload;
import app.nexd.android.api.model.RegisterPayload;
import app.nexd.android.api.model.ResponseTokenDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AuthenticationApi {
  /**
   * 
   * 
   * @param loginPayload  (required)
   * @return Observable&lt;ResponseTokenDto&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/auth/login")
  Observable<ResponseTokenDto> authControllerLogin(
    @retrofit2.http.Body LoginPayload loginPayload
  );

  /**
   * 
   * 
   * @param registerPayload  (required)
   * @return Observable&lt;ResponseTokenDto&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/auth/register")
  Observable<ResponseTokenDto> authControllerRegister(
    @retrofit2.http.Body RegisterPayload registerPayload
  );

}
