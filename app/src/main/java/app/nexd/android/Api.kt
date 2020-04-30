package app.nexd.android

import app.nexd.android.BuildConfig.API_BASE_URL
import app.nexd.android.api.*
import app.nexd.android.network.BackendErrorInterceptor
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import io.reactivex.schedulers.Schedulers.io
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class Api(
    private val preferences: Preferences,
    private val apiClient: ApiClient = NexdApiClient(preferences)
) :
    AuthApi by apiClient.createService(AuthApi::class.java),
    ArticlesApi by apiClient.createService(ArticlesApi::class.java),
    HelpListsApi by apiClient.createService(HelpListsApi::class.java),
    HelpRequestsApi by apiClient.createService(HelpRequestsApi::class.java),
    UsersApi by apiClient.createService(UsersApi::class.java),
    PhoneApi by apiClient.createService(PhoneApi::class.java)


private class NexdApiClient(private val preferences: Preferences) : ApiClient() {

    override fun createDefaultAdapter() {
        super.createDefaultAdapter()

        val baseUrl = if (API_BASE_URL.endsWith("/")) API_BASE_URL else "$API_BASE_URL/"

        adapterBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(io()))
            .addConverterFactory(ScalarsConverterFactory.create())

        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        okBuilder.addInterceptor(loggingInterceptor)

        okBuilder.addInterceptor(BackendErrorInterceptor())

        // auth interceptor
        okBuilder.addInterceptor(Interceptor { chain ->
            var request = chain.request()

            preferences.getToken()?.let {
                request = request.newBuilder()
                    .header("Authorization", "Bearer $it")
                    .build()
            }

            chain.proceed(request)
        })

        okBuilder.addInterceptor(OkHttpProfilerInterceptor())
    }
}