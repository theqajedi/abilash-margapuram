Please find below the list of proposed test cases for automation.

Due to time limitation, i have added only happy path tests.

###Pet API
- POST /pet
- GET ​/pet​/{petId}

###Store API
- POST /store/order
- GET ​/store/order/{orderId}

###User API
- POST ​/user
- GET ​​/user​/{username}
- GET ​​​/user​/login

Note: The two Pet API tests are failing because of java object to XML conversion error.

I have chosen to use rest assured since it provides a fluent API with all the necessary utils to test REST endpoints.
Also because it supports TDD way (given/when/then) of writing tests.