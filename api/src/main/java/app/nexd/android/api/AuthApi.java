package app.nexd.android.api;

import app.nexd.android.api.model.LoginDto;
import app.nexd.android.api.model.RegisterDto;
import app.nexd.android.api.model.TokenDto;
import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthApi {
  /**
   * Login by email and password 
   * 
   * @param loginDto  (required)
   * @return Observable&lt;TokenDto&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("auth/login")
  Observable<TokenDto> authControllerLogin(
    @retrofit2.http.Body LoginDto loginDto
  );

  /**
   * Not yet implemented, token refresh
   * 
   * @param tokenDto  (required)
   * @return Observable&lt;TokenDto&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("auth/refresh")
  Observable<TokenDto> authControllerRefreshToken(
    @retrofit2.http.Body TokenDto tokenDto
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
