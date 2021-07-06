package com.swagger.petstore.test.api;

import com.swagger.petstore.test.api.config.ClientConfigProperties;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class SwaggerPetstoreClient {

    private final ClientConfigProperties configuration;


    public SwaggerPetstoreClient(ClientConfigProperties configuration) {
        this.configuration = configuration;
    }

    public RequestSpecification getRequestSpecification() {
        return RestAssured.given()
                .baseUri(configuration.getEndpoint())
                .port(configuration.getPort());
    }

}
