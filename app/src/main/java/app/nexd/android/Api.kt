package app.nexd.android

import app.nexd.android.api.*
import app.nexd.android.api.model.*
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.logging.HttpLoggingInterceptor

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

    override fun authControllerLogin(loginDto: LoginDto): Observable<TokenDto> {
        return authApi.authControllerLogin(loginDto).subscribeOn(Schedulers.io())
    }

    override fun authControllerRefreshToken(registerDto: TokenDto): Observable<TokenDto> {
        return authApi.authControllerRefreshToken(registerDto).subscribeOn(Schedulers.io())
    }

    override fun articlesControllerInsertOne(createArticleDto: CreateArticleDto?): Observable<Article> {
        return articlesApi.articlesControllerInsertOne(createArticleDto).subscribeOn(Schedulers.io())
    }

    override fun articlesControllerFindAll(): Observable<List<Article>> {
        return articlesApi.articlesControllerFindAll().subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerFindOne(helpListId: Int): Observable<HelpList> {
        return helpListsApi.helpListsControllerFindOne(helpListId).subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerUpdateHelpLists(
        helpListId: Int,
        helpListCreateDto: HelpListCreateDto?
    ): Observable<HelpList> {
        return helpListsApi.helpListsControllerUpdateHelpLists(helpListId, helpListCreateDto).subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerModifyArticleInHelpRequest(
        helpListId: Int,
        helpRequestId: Int,
        articleId: Int,
        articleDone: Boolean
    ): Observable<HelpList> {
        return helpListsApi.helpListsControllerModifyArticleInHelpRequest(helpListId, helpRequestId, articleId, articleDone).subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerGetUserLists(userId: String?): Observable<List<HelpList>> {
        return helpListsApi.helpListsControllerGetUserLists(userId).subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerDeleteHelpRequestFromHelpList(
        helpListId: Int?,
        helpRequestId: Int?
    ): Observable<HelpList> {
        return helpListsApi.helpListsControllerDeleteHelpRequestFromHelpList(helpListId, helpRequestId).subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerModifyArticleInAllHelpRequests(
        helpListId: Int,
        articleId: Int,
        articleDone: Boolean
    ): Observable<HelpList> {
        return helpListsApi.helpListsControllerModifyArticleInAllHelpRequests(helpListId, articleId, articleDone).subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerInsertNewHelpList(helpListCreateDto: HelpListCreateDto?): Observable<HelpList> {
        return helpListsApi.helpListsControllerInsertNewHelpList(helpListCreateDto).subscribeOn(Schedulers.io())
    }

    override fun helpListsControllerAddHelpRequestToList(
        helpListId: Int?,
        helpRequestId: Int?
    ): Observable<HelpList> {
        return helpListsApi.helpListsControllerAddHelpRequestToList(helpListId, helpRequestId).subscribeOn(Schedulers.io())
    }

    override fun helpRequestsControllerAddArticleInHelpRequest(
        helpRequestId: Int,
        articleId: Int,
        createOrUpdateHelpRequestArticleDto: CreateOrUpdateHelpRequestArticleDto?
    ): Observable<HelpRequest> {
        return helpRequestsApi.helpRequestsControllerAddArticleInHelpRequest(helpRequestId, articleId, createOrUpdateHelpRequestArticleDto).subscribeOn(Schedulers.io())
    }

    override fun helpRequestsControllerRemoveArticleInHelpRequest(
        helpRequestId: Int,
        articleId: Int
    ): Observable<HelpRequest> {
        return helpRequestsApi.helpRequestsControllerRemoveArticleInHelpRequest(helpRequestId, articleId).subscribeOn(Schedulers.io())
    }

    override fun helpRequestsControllerGetAll(
        userId: String?,
        excludeUserId: String?,
        zipCode: MutableList<String>?,
        includeRequester: String?,
        status: List<String>?
    ): Observable<List<HelpRequest>> {
        return helpRequestsApi.helpRequestsControllerGetAll(userId, excludeUserId, zipCode, includeRequester, status).subscribeOn(Schedulers.io())
    }

    override fun helpRequestsControllerUpdateRequest(
        helpRequestId: Int?,
        helpRequestCreateDto: HelpRequestCreateDto?
    ): Observable<HelpRequest> {
        return helpRequestsApi.helpRequestsControllerUpdateRequest(helpRequestId, helpRequestCreateDto).subscribeOn(Schedulers.io())
    }

    override fun helpRequestsControllerInsertRequestWithArticles(helpRequestCreateDto: HelpRequestCreateDto?): Observable<HelpRequest> {
        return helpRequestsApi.helpRequestsControllerInsertRequestWithArticles(helpRequestCreateDto).subscribeOn(Schedulers.io())
    }

    override fun helpRequestsControllerGetSingleRequest(helpRequestId: Int?): Observable<HelpRequest> {
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