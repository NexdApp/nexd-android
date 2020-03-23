package de.andrestefanov.android.nearbuy.api;

import de.andrestefanov.android.nearbuy.ApiClient;
import de.andrestefanov.android.nearbuy.api.model.Article;
import de.andrestefanov.android.nearbuy.api.model.CreateArticleDto;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ArticlesApi
 */
public class ArticlesApiTest {

    private ArticlesApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(ArticlesApi.class);
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void articlesControllerFindAllTest() {
        // List<Article> response = api.articlesControllerFindAll();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void articlesControllerInsertOneTest() {
        CreateArticleDto createArticleDto = null;
        // Article response = api.articlesControllerInsertOne(createArticleDto);

        // TODO: test validations
    }
}
