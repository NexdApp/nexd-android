package de.andrestefanov.android.nearbuy.api;

import de.andrestefanov.android.nearbuy.ApiClient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for DefaultApi
 */
public class DefaultApiTest {

    private DefaultApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(DefaultApi.class);
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void appControllerRootTest() {
        // api.appControllerRoot();

        // TODO: test validations
    }
}
