package app.nexd.android

import app.nexd.android.api.*
import app.nexd.android.api.model.*
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.logging.HttpLoggingInterceptor
import java.math.BigDecimal

lateinit var api: Api

class Api(private val apiClient: ApiClient = ApiClient("bearer")) :
    AuthApi,
    ArticlesApi,
    HelpListsApi,
    HelpRequestsApi,
    UsersApi {

    private val authApi: AuthApi

    private val articlesApi: ArticlesApi

    private val helpListsApi: HelpListsApi

    private val helpRequestsApi: HelpRequestsApi

    private val usersApi: UsersApi

    init {
        apiClient.okBuilder
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        if (!BuildConfig.API_BASE_URL.isBlank()) {
            apiClient.adapterBuilder = apiClient.adapterBuilder
                .baseUrl(BuildConfig.API_BASE_URL)
        }

        articlesApi = apiClient.createService(ArticlesApi::class.java)
        authApi = apiClient.createService(AuthApi::class.java)
        helpListsApi = apiClient.createService(HelpListsApi::class.java)
        helpRequestsApi = apiClient.createService(HelpRequestsApi::class.java)
        usersApi = apiClient.createService(UsersApi::class.java)
    }

    fun setBearerToken(accessToken: String?) {
        this.apiClient.setBearerToken(accessToken)
    }

    override fun authControllerRegister(registerDto: RegisterDto?): Observable<TokenDto> {
        return authApi.authControllerRegister(registerDto).subscribeOn(Schedulers.io())
    }

    override fun authControllerLogin(): Observable<TokenDto> {
        return authApi.authControllerLogin().subscribeOn(Schedulers.io())
    }

    override fun authControllerRefreshToken(registerDto: RegisterDto?): Observable<TokenDto> {
        return authApi.authControllerRefreshToken(registerDto).subscribeOn(Schedulers.io())
    }

    override fun articlesControllerInsertOne(createArticleDto: CreateArticleDto?): Observable<Article> {
        return articlesApi.articlesControllerInsertOne(createArticleDto).subscribeOn(Schedulers.io())
    }

    override fun articlesControllerFindAll(): Observable<MutableList<Article>> {
        return articlesApi.articlesControllerFindAll().subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerFindOne(helpListId: BigDecimal?): Observable<HelpList> {
        return helpListsApi.helpListsControllerFindOne(helpListId).subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerUpdateHelpLists(
        helpListId: BigDecimal?,
        helpListCreateDto: HelpListCreateDto?
    ): Observable<HelpList> {
        return helpListsApi.helpListsControllerUpdateHelpLists(helpListId, helpListCreateDto).subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerModifyArticleInHelpRequest(
        helpListId: BigDecimal?,
        helpRequestId: BigDecimal?,
        articleId: Any?,
        articleDone: String?
    ): Observable<HelpList> {
        return helpListsApi.helpListsControllerModifyArticleInHelpRequest(helpListId, helpRequestId, articleId, articleDone).subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerGetUserLists(userId: String?): Observable<List<HelpList>> {
        return helpListsApi.helpListsControllerGetUserLists(userId).subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerDeleteHelpRequestFromHelpList(
        helpListId: BigDecimal?,
        helpRequestId: BigDecimal?
    ): Observable<HelpList> {
        return helpListsApi.helpListsControllerDeleteHelpRequestFromHelpList(helpListId, helpRequestId).subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerModifyArticleInAllHelpRequests(
        helpListId: BigDecimal?,
        articleId: Any?,
        articleDone: Any?
    ): Observable<HelpList> {
        return helpListsApi.helpListsControllerModifyArticleInAllHelpRequests(helpListId, articleId, articleDone).subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerInsertNewHelpList(helpListCreateDto: HelpListCreateDto?): Observable<HelpList> {
        return helpListsApi.helpListsControllerInsertNewHelpList(helpListCreateDto).subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerAddHelpRequestToList(
        helpListId: BigDecimal?,
        helpRequestId: BigDecimal?
    ): Observable<HelpList> {
        return helpListsApi.helpListsControllerAddHelpRequestToList(helpListId, helpRequestId).subscribeOn(Schedulers.io())
    }

    override fun helpRequestsControllerAddArticleInHelpRequest(
        helpRequestId: HelpRequest?,
        articleId: BigDecimal?,
        createOrUpdateHelpRequestArticleDto: CreateOrUpdateHelpRequestArticleDto?
    ): Observable<HelpRequest> {
        return helpRequestsApi.helpRequestsControllerAddArticleInHelpRequest(helpRequestId, articleId, createOrUpdateHelpRequestArticleDto).subscribeOn(Schedulers.io())
    }

    override fun helpRequestsControllerRemoveArticleInHelpRequest(
        helpRequestId: HelpRequest?,
        articleId: BigDecimal?
    ): Observable<HelpRequest> {
        return helpRequestsApi.helpRequestsControllerRemoveArticleInHelpRequest(helpRequestId, articleId).subscribeOn(Schedulers.io())
    }

    override fun helpRequestsControllerGetAll(
        userId: String?,
        zipCode: MutableList<String>?,
        includeRequester: String?,
        status: MutableList<String>?
    ): Observable<MutableList<HelpRequest>> {
        return helpRequestsApi.helpRequestsControllerGetAll(userId, zipCode, includeRequester, status).subscribeOn(Schedulers.io())
    }

    override fun helpRequestsControllerUpdateRequest(
        helpRequestId: BigDecimal?,
        helpRequestCreateDto: HelpRequestCreateDto?
    ): Observable<HelpRequest> {
        return helpRequestsApi.helpRequestsControllerUpdateRequest(helpRequestId, helpRequestCreateDto).subscribeOn(Schedulers.io())
    }

    override fun helpRequestsControllerInsertRequestWithArticles(helpRequestCreateDto: HelpRequestCreateDto?): Observable<HelpRequest> {
        return helpRequestsApi.helpRequestsControllerInsertRequestWithArticles(helpRequestCreateDto).subscribeOn(Schedulers.io())
    }

    override fun helpRequestsControllerGetSingleRequest(helpRequestId: BigDecimal?): Observable<HelpRequest> {
        return helpRequestsApi.helpRequestsControllerGetSingleRequest(helpRequestId).subscribeOn(Schedulers.io())
    }

    override fun userControllerUpdate(
        userId: String?,
        updateUserDto: UpdateUserDto?
    ): Observable<User> {
        return usersApi.userControllerUpdate(userId, updateUserDto).subscribeOn(Schedulers.io())
    }

    override fun userControllerFindOne(userId: String?): Observable<User> {
        return usersApi.userControllerFindOne(userId).subscribeOn(Schedulers.io())
    }

    override fun userControllerGetAll(): Observable<MutableList<User>> {
        return usersApi.userControllerGetAll().subscribeOn(Schedulers.io())
    }

    override fun userControllerFindMe(): Observable<User> {
        return usersApi.userControllerFindMe().subscribeOn(Schedulers.io())
    }

    override fun userControllerUpdateMyself(updateUserDto: UpdateUserDto?): Observable<User> {
        return usersApi.userControllerUpdateMyself(updateUserDto).subscribeOn(Schedulers.io())
    }


}