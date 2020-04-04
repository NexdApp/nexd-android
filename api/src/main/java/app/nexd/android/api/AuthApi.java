package app.nexd.android.api;

import app.nexd.android.CollectionFormats.*;

import io.reactivex.Observable;
import io.reactivex.Completable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import app.nexd.android.api.model.RegisterDto;
import app.nexd.android.api.model.TokenDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AuthApi {
  /**
   * Login by email and password 
   * 
   * @return Observable&lt;TokenDto&gt;
   */
  @POST("auth/login")
  Observable<TokenDto> authControllerLogin();
    

  /**
   * Not yet implemented, token refresh
   * 
   * @param registerDto  (required)
   * @return Observable&lt;TokenDto&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("auth/refresh")
  Observable<TokenDto> authControllerRefreshToken(
    @retrofit2.http.Body RegisterDto registerDto
  );

  /**
   * Register with email and password 
   * 
   * @param registerDto  (required)
   * @return Observable&lt;TokenDto&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("auth/register")
  Observable<TokenDto> authControllerRegister(
    @retrofit2.http.Body RegisterDto registerDto
  );

}
