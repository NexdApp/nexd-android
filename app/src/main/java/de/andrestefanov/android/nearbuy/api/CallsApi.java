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
   * @return Completable
   */
  @GET("api/call")
  Completable callControllerIndex();
    

  /**
   * 
   * 
   * @return Completable
   */
  @GET("api/call/listen")
  Completable callControllerListen();
    

  /**
   * 
   * 
   * @return Completable
   */
  @GET("api/call/webhook")
  Completable callControllerWebhook();
    

}
