package com.swagger.petstore.test.api;

import static com.swagger.petstore.test.api.utils.Constants.PETSTORE_CONTENT_TYPE_JSON;
import static com.swagger.petstore.test.api.utils.Constants.PETSTORE_ORDER_ENDPOINT;
import static com.swagger.petstore.test.api.utils.Constants.PETSTORE_PET_ENDPOINT;
import static com.swagger.petstore.test.api.utils.Constants.PETSTORE_USER_ENDPOINT;
import static com.swagger.petstore.test.api.utils.Constants.PET_STATUS_AVAILABLE;
import static com.swagger.petstore.test.api.utils.Constants.USER_PASSWORD;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.swagger.petstore.test.api.config.ClientConfigProperties;
import com.swagger.petstore.test.api.model.Category;
import com.swagger.petstore.test.api.model.Order;
import com.swagger.petstore.test.api.model.OrderBuilder;
import com.swagger.petstore.test.api.model.Pet;
import com.swagger.petstore.test.api.model.PetBuilder;
import com.swagger.petstore.test.api.model.Tag;
import com.swagger.petstore.test.api.model.User;
import com.swagger.petstore.test.api.model.UserBuilder;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseTest {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssX";
    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

    @Autowired
    SwaggerPetstoreClient swaggerPetstoreClient;

    @Autowired
    ClientConfigProperties clientConfigProperties;

    @Bean
    public SwaggerPetstoreClient swaggerPetstoreClient(
            final ClientConfigProperties properties) {
        return new SwaggerPetstoreClient(properties);
    }

    @Rule
    public TestName name = new TestName();

    @BeforeClass
    public static void setupRestAssured() {
        RestAssured.replaceFiltersWith(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter()
        );
    }

    @Before
    public void globalSetUp() {
        LOG.info(String.format("########### Test name: %s ###########", name.getMethodName()));
        Jackson2ObjectMapperFactory mapperFactory = (cls, charset) -> {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper
                    .setDateFormat(new SimpleDateFormat(DATE_FORMAT,
                            Locale.getDefault(Locale.Category.FORMAT)));
            return objectMapper;
        };
        RestAssured.config = RestAssured.config()
                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
                        .jackson2ObjectMapperFactory(mapperFactory))
                .httpClient(RestAssured.config().getHttpClientConfig()
                        .setParam("org.apache.http.params.CoreConnectionPNames#SO_TIMEOUT",
                                600000));
    }

    RequestSpecification getRequestSpecification() {
        return swaggerPetstoreClient.getRequestSpecification();
    }

    User createTestUser() throws JSONException {
        Response createdUserResponse = getRequestSpecification()
                .contentType(PETSTORE_CONTENT_TYPE_JSON)
                .accept(ContentType.JSON)
                .body(createRequestBodyForUser())
                .post(PETSTORE_USER_ENDPOINT);
        if (createdUserResponse.statusCode() != HttpStatus.SC_OK) {
            throw new IllegalStateException(
                    "Failed to create test user.");
        }
        return createdUserResponse.then().extract().as(User.class);
    }

    String createRequestBodyForUser() throws JSONException {
        JSONObject body = new JSONObject();
        User user = populateTestUser();
        body.put("id", user.getId()).
                put("username", user.getUsername()).
                put("firstName", user.getFirstName()).
                put("lastName", user.getLastName()).
                put("password", user.getPassword()).
                put("phone", user.getPhone()).
                put("userStatus", user.getUserStatus());
        return body.toString();
    }

    User populateTestUser() {
        return new UserBuilder()
                .setId(new RandomDataGenerator().nextLong(10L, 100L))
                .setUsername(RandomStringUtils.randomAlphanumeric(6))
                .setFirstName(RandomStringUtils.randomAlphabetic(6))
                .setLastName(RandomStringUtils.randomAlphabetic(6))
                .setEmail(RandomStringUtils.randomAlphanumeric(6) + "@test.de")
                .setPassword(USER_PASSWORD)
                .setPhone(RandomStringUtils.randomNumeric(10))
                .setUserStatus(1)
                .createUser();
    }

    Pet createTestPet() throws JAXBException, JSONException {
        Response createdUserResponse = getRequestSpecification()
                .contentType(PETSTORE_CONTENT_TYPE_JSON)
                .accept(ContentType.XML)
                .body(createRequestBodyForPet())
                .post(PETSTORE_PET_ENDPOINT);
        if (createdUserResponse.statusCode() != HttpStatus.SC_OK) {
            throw new IllegalStateException(
                    "Failed to create test Pet.");
        }
        return createdUserResponse.then().extract().as(Pet.class);
    }

    String createRequestBodyForPet() throws JAXBException {
        return jaxbObjectToXML(populateTestPet());
    }

    private String jaxbObjectToXML(Pet pet) throws JAXBException {
        //Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance(Pet.class);

        //Create Marshaller
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        //Required formatting??
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        //Print XML String to Console
        StringWriter sw = new StringWriter();

        //Write XML to StringWriter
        jaxbMarshaller.marshal(pet, sw);

        //Verify XML Content

        return sw.toString();
    }

    Pet populateTestPet() {
        return new PetBuilder()
                .setId(new RandomDataGenerator().nextLong(10L, 100L))
                .setCategory(populatePetCategory())
                .setName(RandomStringUtils.randomAlphanumeric(6))
                .setPhotoUrls(Collections.singletonList("string"))
                .setStatus(PET_STATUS_AVAILABLE)
                .setTags(populatePetTags())
                .createPet();
    }

    private List<Tag> populatePetTags() {
        return Collections.singletonList(new Tag(new RandomDataGenerator().nextLong(10L, 100L),
                RandomStringUtils.randomAlphanumeric(6)));
    }

    private Category populatePetCategory() {
        return new Category(new RandomDataGenerator().nextLong(10L, 100L),
                RandomStringUtils.randomAlphanumeric(6));
    }

    Order createTestOrder() throws JSONException {
        Response createdOrderResponse = getRequestSpecification()
                .contentType(PETSTORE_CONTENT_TYPE_JSON)
                .accept(ContentType.JSON)
                .body(createRequestBodyForOrder())
                .post(PETSTORE_ORDER_ENDPOINT);
        if (createdOrderResponse.statusCode() != HttpStatus.SC_OK) {
            throw new IllegalStateException(
                    "Failed to create test order.");
        }
        return createdOrderResponse.then().extract().as(Order.class);
    }

    String createRequestBodyForOrder() throws JSONException {
        JSONObject body = new JSONObject();
        Order order = populateTestOrder();
        body.put("id", order.getId()).
                put("petId", order.getPetId()).
                put("quantity", order.getQuantity()).
                put("shipDate", "2021-07-06T00:20:50.592+00:00").
                put("status", order.getStatus()).
                put("complete", order.isComplete());
        return body.toString();
    }

    Order populateTestOrder() {
        return new OrderBuilder()
                .setId(new RandomDataGenerator().nextLong(10L, 100L))
                .setPetId(new RandomDataGenerator().nextLong(10L, 100L))
                .setQuantity(new RandomDataGenerator().nextInt(10, 100))
                .setStatus("approved")
                .setComplete(true)
                .createOrder();
    }

}
