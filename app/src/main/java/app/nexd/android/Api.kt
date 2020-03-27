package app.nexd.android

import app.nexd.android.api.*
import app.nexd.android.api.model.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers.io
import okhttp3.logging.HttpLoggingInterceptor
import java.math.BigDecimal

lateinit var api: Api

class Api(private val apiClient: ApiClient = ApiClient("bearer")) :
    ArticlesApi,
    AuthenticationApi,
    CallsApi,
    DefaultApi,
    RequestApi,
    ShoppingListApi,
    UserApi {

    private val articlesApi : ArticlesApi

    private val authenticationApi : AuthenticationApi

    private val callsApi : CallsApi

    private val defaultApi : DefaultApi

    private val requestApi : RequestApi

    private val shoppingListApi : ShoppingListApi

    private val userApi : UserApi

    init {
        apiClient.okBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        articlesApi = apiClient.createService(ArticlesApi::class.java)
        authenticationApi = apiClient.createService(AuthenticationApi::class.java)
        callsApi = apiClient.createService(CallsApi::class.java)
        defaultApi = apiClient.createService(DefaultApi::class.java)
        requestApi = apiClient.createService(RequestApi::class.java)
        shoppingListApi = apiClient.createService(ShoppingListApi::class.java)
        userApi = apiClient.createService(UserApi::class.java)
    }

    override fun articlesControllerInsertOne(createArticleDto: CreateArticleDto?): Observable<Article> {
        return articlesApi.articlesControllerInsertOne(createArticleDto).subscribeOn(io())
    }

    override fun articlesControllerFindAll(): Observable<List<Article>> {
        return articlesApi.articlesControllerFindAll().subscribeOn(io())
    }

    override fun authControllerRegister(registerPayload: RegisterPayload?): Observable<ResponseTokenDto> {
        return authenticationApi.authControllerRegister(registerPayload).subscribeOn(io())
    }

    override fun authControllerLogin(loginPayload: LoginPayload?): Observable<ResponseTokenDto> {
        return authenticationApi.authControllerLogin(loginPayload).subscribeOn(io())
    }

    override fun callControllerDownload(id: Int?): Completable {
        return callsApi.callControllerDownload(id).subscribeOn(io())
    }

    override fun callControllerUpload(id: Int?): Completable {
        return callsApi.callControllerUpload(id).subscribeOn(io())
    }

    override fun callControllerTranslated(id: Int?): Completable {
        return callsApi.callControllerTranslated(id).subscribeOn(io())
    }

    override fun callControllerIndex(): Completable {
        return callsApi.callControllerIndex().subscribeOn(io())
    }

    override fun callControllerWebhook(): Completable {
        return callsApi.callControllerWebhook().subscribeOn(io())
    }

    override fun callControllerInitUpload(): Completable {
        return callsApi.callControllerInitUpload().subscribeOn(io())
    }

    override fun appControllerRoot(): Completable {
        return defaultApi.appControllerRoot().subscribeOn(io())
    }

    override fun requestControllerMarkArticleAsDone(
        requestId: BigDecimal?,
        articleId: BigDecimal?,
        requestArticleStatusDto: RequestArticleStatusDto?
    ): Observable<RequestEntity> {
        return requestApi.requestControllerMarkArticleAsDone(
            requestId,
            articleId,
            requestArticleStatusDto
        ).subscribeOn(io())
    }

    override fun requestControllerGetAll(
        onlyMine: String?,
        zipCode: String?
    ): Observable<List<RequestEntity>> {
        return requestApi.requestControllerGetAll(onlyMine, zipCode).subscribeOn(io())
    }

    override fun requestControllerGetSingleRequest(requestId: BigDecimal?): Observable<RequestEntity> {
        return requestApi.requestControllerGetSingleRequest(requestId).subscribeOn(io())
    }

    override fun requestControllerUpdateRequest(
        requestId: BigDecimal?,
        requestFormDto: RequestFormDto?
    ): Observable<RequestEntity> {
        return requestApi.requestControllerUpdateRequest(requestId, requestFormDto)
            .subscribeOn(io())
    }

    override fun requestControllerInsertRequestWithArticles(requestFormDto: RequestFormDto?): Observable<RequestEntity> {
        return requestApi.requestControllerInsertRequestWithArticles(requestFormDto)
            .subscribeOn(io())
    }

    override fun shoppingListControllerAddRequestToList(
        shoppingListId: BigDecimal?,
        requestId: BigDecimal?
    ): Observable<ShoppingList> {
        return shoppingListApi.shoppingListControllerAddRequestToList(shoppingListId, requestId)
            .subscribeOn(io())
    }

    override fun shoppingListControllerInsertNewShoppingList(shoppingListFormDto: ShoppingListFormDto?): Observable<ShoppingList> {
        return shoppingListApi.shoppingListControllerInsertNewShoppingList(shoppingListFormDto)
            .subscribeOn(io())
    }

    override fun shoppingListControllerDeleteRequestFromList(
        shoppingListId: BigDecimal?,
        requestId: BigDecimal?
    ): Observable<ShoppingList> {
        return shoppingListApi.shoppingListControllerDeleteRequestFromList(
            shoppingListId,
            requestId
        ).subscribeOn(io())
    }

    override fun shoppingListControllerGetUserLists(): Observable<List<ShoppingList>> {
        return shoppingListApi.shoppingListControllerGetUserLists().subscribeOn(io())
    }

    override fun shoppingListControllerUpdateShoppingList(
        id: BigDecimal?,
        shoppingListFormDto: ShoppingListFormDto?
    ): Observable<ShoppingList> {
        return shoppingListApi.shoppingListControllerUpdateShoppingList(id, shoppingListFormDto)
            .subscribeOn(io())
    }

    override fun shoppingListControllerFindOne(id: BigDecimal?): Observable<ShoppingList> {
        return shoppingListApi.shoppingListControllerFindOne(id).subscribeOn(io())
    }

    override fun userControllerUpdate(id: Int?, updateUserDto: UpdateUserDto?): Observable<User> {
        return userApi.userControllerUpdate(id, updateUserDto).subscribeOn(io())
    }

    override fun userControllerFindOne(id: Int?): Observable<User> {
        return userApi.userControllerFindOne(id).subscribeOn(io())
    }

    override fun userControllerGetAll(): Observable<List<User>> {
        return userApi.userControllerGetAll().subscribeOn(io())
    }

    fun setBearerToken(accessToken: String?) {
        this.apiClient.setBearerToken(accessToken)
    }


}