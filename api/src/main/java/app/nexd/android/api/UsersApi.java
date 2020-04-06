package app.nexd.android.api;

import app.nexd.android.CollectionFormats.*;

import io.reactivex.Observable;
import io.reactivex.Completable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import app.nexd.android.api.model.UpdateUserDto;
import app.nexd.android.api.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UsersApi {
  /**
   * Get user profile of the requesting user
   * 
   * @return Observable&lt;User&gt;
   */
  @GET("users/me")
  Observable<User> userControllerFindMe();
    

  /**
   * Get user profile of a specific user
   * 
   * @param userId user id (required)
   * @return Observable&lt;User&gt;
   */
  @GET("users/{userId}")
  Observable<User> userControllerFindOne(
    @retrofit2.http.Path("userId") String userId
  );

  /**
   * Get all users
   * 
   * @return Observable&lt;List&lt;User&gt;&gt;
   */
  @GET("users")
  Observable<List<User>> userControllerGetAll();
    

  /**
   * Update profile of a specific user
   * 
   * @param userId user id (required)
   * @param updateUserDto  (required)
   * @return Observable&lt;User&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("users/{userId}")
  Observable<User> userControllerUpdate(
    @retrofit2.http.Path("userId") String userId, @retrofit2.http.Body UpdateUserDto updateUserDto
  );

  /**
   * Update profile of the requesting user
   * 
   * @param updateUserDto  (required)
   * @return Observable&lt;User&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("users/me")
  Observable<User> userControllerUpdateMyself(
    @retrofit2.http.Body UpdateUserDto updateUserDto
  );

}
