package com.swagger.petstore.test.api;

import static com.swagger.petstore.test.api.utils.Constants.PETSTORE_CONTENT_TYPE_JSON;
import static com.swagger.petstore.test.api.utils.Constants.PETSTORE_PET_ENDPOINT;
import static com.swagger.petstore.test.api.utils.Constants.PETSTORE_PET_GET_ENDPOINT;

import com.swagger.petstore.test.api.model.Pet;
import io.restassured.http.ContentType;
import javax.xml.bind.JAXBException;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * API tests for Pet
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PetApiTest extends BaseTest {

    //TODO Fix XML conversion to make the test pass
    @Test
    public void createPet() throws JSONException, JAXBException {
        //given
        getRequestSpecification()
                .contentType(PETSTORE_CONTENT_TYPE_JSON)
                .accept(ContentType.XML)
                .body(createRequestBodyForPet())
                .when()
                .post(PETSTORE_PET_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    //TODO Fix XML conversion to make the test pass
    @Test
    public void getPet() throws JSONException, JAXBException {
        //given
        Pet testPet = createTestPet();

        //when
        getRequestSpecification()
                .accept(ContentType.XML)
                .get(PETSTORE_PET_GET_ENDPOINT, testPet.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Pet.class);
    }
}
