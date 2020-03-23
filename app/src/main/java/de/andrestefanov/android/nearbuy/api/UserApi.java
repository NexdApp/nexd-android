package de.andrestefanov.android.nearbuy.api;

import de.andrestefanov.android.nearbuy.CollectionFormats.*;

import io.reactivex.Observable;
import io.reactivex.Completable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import de.andrestefanov.android.nearbuy.api.model.UpdateUserDto;
import de.andrestefanov.android.nearbuy.api.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserApi {
  /**
   * 
   * 
   * @param id user id (required)
   * @return Observable&lt;User&gt;
   */
  @GET("api/user/{id}")
  Observable<User> userControllerFindOne(
    @retrofit2.http.Path("id") Integer id
  );

  /**
   * 
   * 
   * @return Observable&lt;List&lt;User&gt;&gt;
   */
  @GET("api/user")
  Observable<List<User>> userControllerGetAll();
    

  /**
   * 
   * 
   * @param id user id (required)
   * @param updateUserDto  (required)
   * @return Observable&lt;User&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("api/user/{id}")
  Observable<User> userControllerUpdate(
    @retrofit2.http.Path("id") Integer id, @retrofit2.http.Body UpdateUserDto updateUserDto
  );

}
