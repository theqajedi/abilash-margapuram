package com.swagger.petstore.test.api.utils;

/**
 * Class containing commonly used constants.
 */
public class Constants {

    public static final String PETSTORE_CONTENT_TYPE_JSON = "application/json";

    public static final String PETSTORE_USER_ENDPOINT = "/api/v3/user/";
    public static final String USER_NAME_PLACE_HOLDER = "{userName}";
    public static final String PETSTORE_USER_GET_ENDPOINT = PETSTORE_USER_ENDPOINT + USER_NAME_PLACE_HOLDER;
    public static final String PETSTORE_USER_LOGIN_ENDPOINT = PETSTORE_USER_ENDPOINT + "login?{username}/{password}";
    public static final String USER_PASSWORD = "Pa$$W0rD";

    public static final String PETSTORE_PET_ENDPOINT = "/api/v3/pet/";
    public static final String PET_ID_PLACE_HOLDER = "{petId}";
    public static final String PETSTORE_PET_GET_ENDPOINT = PETSTORE_PET_ENDPOINT + PET_ID_PLACE_HOLDER;
    public static final String PET_STATUS_AVAILABLE = "Available";

    public static final String PETSTORE_ORDER_ENDPOINT = "/api/v3/store/order/";
    public static final String ORDER_ID_PLACE_HOLDER = "{orderId}";
    public static final String PETSTORE_ORDER_GET_ENDPOINT = PETSTORE_ORDER_ENDPOINT + ORDER_ID_PLACE_HOLDER;

}
