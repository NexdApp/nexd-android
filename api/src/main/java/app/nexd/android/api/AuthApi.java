package app.nexd.android.api;

import app.nexd.android.api.model.EmailPasswordResetDto;
import app.nexd.android.api.model.LoginDto;
import app.nexd.android.api.model.RegisterDto;
import app.nexd.android.api.model.TokenDto;
import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.GET;
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

  /**
   * Email password reset initiation
   * 
   * @param email  (required)
   * @return Completable
   */
  @GET("auth/reset_email_password_initiate/{email}")
  Completable authControllerResetEmailPasswordInitiate(
    @retrofit2.http.Path("email") String email
  );

  /**
   * Email password reset initiation
   * 
   * @param emailPasswordResetDto  (required)
   * @return Completable
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("auth/reset_email_password_complete")
  Completable authControllerResetPasswordComplete(
    @retrofit2.http.Body EmailPasswordResetDto emailPasswordResetDto
  );

}
