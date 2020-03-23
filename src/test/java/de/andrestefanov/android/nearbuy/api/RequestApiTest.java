package de.andrestefanov.android.nearbuy.api;

import de.andrestefanov.android.nearbuy.ApiClient;
import java.math.BigDecimal;
import de.andrestefanov.android.nearbuy.api.model.RequestArticleStatusDto;
import de.andrestefanov.android.nearbuy.api.model.RequestEntity;
import de.andrestefanov.android.nearbuy.api.model.RequestFormDto;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for RequestApi
 */
public class RequestApiTest {

    private RequestApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(RequestApi.class);
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void requestControllerGetAllTest() {
        String onlyMine = null;
        String zipCode = null;
        // List<RequestEntity> response = api.requestControllerGetAll(onlyMine, zipCode);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void requestControllerGetSingleRequestTest() {
        BigDecimal requestId = null;
        // RequestEntity response = api.requestControllerGetSingleRequest(requestId);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void requestControllerInsertRequestWithArticlesTest() {
        RequestFormDto requestFormDto = null;
        // RequestEntity response = api.requestControllerInsertRequestWithArticles(requestFormDto);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void requestControllerMarkArticleAsDoneTest() {
        BigDecimal requestId = null;
        BigDecimal articleId = null;
        RequestArticleStatusDto requestArticleStatusDto = null;
        // RequestEntity response = api.requestControllerMarkArticleAsDone(requestId, articleId, requestArticleStatusDto);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void requestControllerUpdateRequestTest() {
        BigDecimal requestId = null;
        RequestFormDto requestFormDto = null;
        // RequestEntity response = api.requestControllerUpdateRequest(requestId, requestFormDto);

        // TODO: test validations
    }
}
