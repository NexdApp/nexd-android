package de.andrestefanov.android.nearbuy.api;

import de.andrestefanov.android.nearbuy.ApiClient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for CallsApi
 */
public class CallsApiTest {

    private CallsApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(CallsApi.class);
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void callControllerIndexTest() {
        // api.callControllerIndex();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void callControllerListenTest() {
        // api.callControllerListen();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void callControllerWebhookTest() {
        // api.callControllerWebhook();

        // TODO: test validations
    }
}
