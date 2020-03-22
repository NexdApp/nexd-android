package de.andrestefanov.android.nearbuy.api;

import de.andrestefanov.android.nearbuy.ApiClient;
import de.andrestefanov.android.nearbuy.api.model.UpdateUserDto;
import de.andrestefanov.android.nearbuy.api.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for UserApi
 */
public class UserApiTest {

    private UserApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(UserApi.class);
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void userControllerFindOneTest() {
        Integer id = null;
        // User response = api.userControllerFindOne(id);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void userControllerGetAllTest() {
        // List<User> response = api.userControllerGetAll();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void userControllerUpdateTest() {
        Integer id = null;
        UpdateUserDto updateUserDto = null;
        // User response = api.userControllerUpdate(id, updateUserDto);

        // TODO: test validations
    }
}
