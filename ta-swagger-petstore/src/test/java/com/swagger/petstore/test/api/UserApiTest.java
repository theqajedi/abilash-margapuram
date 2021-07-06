package com.swagger.petstore.test.api;

import static com.swagger.petstore.test.api.utils.Constants.PETSTORE_CONTENT_TYPE_JSON;
import static com.swagger.petstore.test.api.utils.Constants.PETSTORE_USER_ENDPOINT;
import static com.swagger.petstore.test.api.utils.Constants.PETSTORE_USER_GET_ENDPOINT;
import static com.swagger.petstore.test.api.utils.Constants.PETSTORE_USER_LOGIN_ENDPOINT;
import static org.hamcrest.Matchers.notNullValue;

import com.swagger.petstore.test.api.model.User;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * API tests for User
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserApiTest extends BaseTest {

    @Test
    public void createUser() throws JSONException {
        //given
        getRequestSpecification()
                .contentType(PETSTORE_CONTENT_TYPE_JSON)
                .accept(ContentType.JSON)
                .body(createRequestBodyForUser())
                .when()
                .post(PETSTORE_USER_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getUser() throws JSONException {
        //given
        User testUser = createTestUser();

        //when
        getRequestSpecification()
                .accept(ContentType.XML)
                .get(PETSTORE_USER_GET_ENDPOINT, testUser.getUsername())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(User.class);
    }

    @Test
    public void loginUser() throws JSONException {
        //given
        User testUser = createTestUser();

        //when
        getRequestSpecification()
                .accept(ContentType.XML)
                .get(PETSTORE_USER_LOGIN_ENDPOINT, testUser.getUsername(), testUser.getPassword())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .header("x-expires-after", notNullValue());
    }

}
