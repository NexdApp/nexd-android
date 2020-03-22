package de.andrestefanov.android.nearbuy.api;

import de.andrestefanov.android.nearbuy.ApiClient;
import de.andrestefanov.android.nearbuy.api.model.LoginPayload;
import de.andrestefanov.android.nearbuy.api.model.RegisterPayload;
import de.andrestefanov.android.nearbuy.api.model.ResponseTokenDto;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for AuthenticationApi
 */
public class AuthenticationApiTest {

    private AuthenticationApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(AuthenticationApi.class);
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void authControllerLoginTest() {
        LoginPayload loginPayload = null;
        // ResponseTokenDto response = api.authControllerLogin(loginPayload);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void authControllerRegisterTest() {
        RegisterPayload registerPayload = null;
        // ResponseTokenDto response = api.authControllerRegister(registerPayload);

        // TODO: test validations
    }
}
