package com.swagger.petstore.test.api;

import static com.swagger.petstore.test.api.utils.Constants.PETSTORE_CONTENT_TYPE_JSON;
import static com.swagger.petstore.test.api.utils.Constants.PETSTORE_ORDER_ENDPOINT;
import static com.swagger.petstore.test.api.utils.Constants.PETSTORE_ORDER_GET_ENDPOINT;

import com.swagger.petstore.test.api.model.Order;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * API tests for Store
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class StoreApiTest extends BaseTest {

    @Test
    public void createOrder() throws JSONException {
        //given
        getRequestSpecification()
                .contentType(PETSTORE_CONTENT_TYPE_JSON)
                .accept(ContentType.JSON)
                .body(createRequestBodyForOrder())
                .when()
                .post(PETSTORE_ORDER_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getOrder() throws JSONException {
        //given
        Order testOrder = createTestOrder();

        //when
        getRequestSpecification()
                .accept(ContentType.XML)
                .get(PETSTORE_ORDER_GET_ENDPOINT, testOrder.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Order.class);
    }

}
