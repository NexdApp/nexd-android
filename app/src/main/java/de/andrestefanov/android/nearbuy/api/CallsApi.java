package de.andrestefanov.android.nearbuy.api;

import de.andrestefanov.android.nearbuy.CollectionFormats.*;

import io.reactivex.Observable;
import io.reactivex.Completable;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CallsApi {
  /**
   * 
   * 
   * @param id audio id (required)
   * @return Completable
   */
  @GET("api/call/download/{id}")
  Completable callControllerDownload(
    @retrofit2.http.Path("id") Integer id
  );

  /**
   * 
   * 
   * @return Completable
   */
  @GET("api/call")
  Completable callControllerIndex();
    

  /**
   * 
   * 
   * @return Completable
   */
  @GET("api/call/upload")
  Completable callControllerInitUpload();
    

  /**
   * 
   * 
   * @param id audio id (required)
   * @return Completable
   */
  @PUT("api/call/translated/{id}")
  Completable callControllerTranslated(
    @retrofit2.http.Path("id") Integer id
  );

  /**
   * 
   * 
   * @param id audio id (required)
   * @return Completable
   */
  @POST("api/call/upload/{id}")
  Completable callControllerUpload(
    @retrofit2.http.Path("id") Integer id
  );

  /**
   * 
   * 
   * @return Completable
   */
  @GET("api/call/webhook")
  Completable callControllerWebhook();
    

}
