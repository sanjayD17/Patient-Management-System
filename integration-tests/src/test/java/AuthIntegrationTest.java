import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AuthIntegrationTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:8000";
    }

    @Test
    public void shouldRegisterUserSuccessfully() {
        String uniqueEmail = "user" + System.currentTimeMillis() + "@gmail.com";

        String registerPayload = """
      {
        "email": "%s",
        "password": "mypassword",
        "role": "USER"
      }
    """.formatted(uniqueEmail);

        String response = given()
                .contentType("application/json")
                .body(registerPayload)
                .when()
                .post("/auth/register")
                .then()
                .statusCode(anyOf(is(200), is(201)))
                .extract()
                .asString();

        System.out.println("Register Response: " + response);
        // Optionally assert plain text content
        assert response.equals("User registered successfully");
    }
    @Test
    public void shouldLoginSuccessfullyAfterRegistration() {
        // Use a unique email for this test
        String uniqueEmail = "user" + System.currentTimeMillis() + "@test.com";

        // Step 1: Register the user
        String registerPayload = """
          {
            "email": "%s",
            "password": "mypassword",
            "role": "USER"
          }
        """.formatted(uniqueEmail);

        given()
                .contentType("application/json")
                .body(registerPayload)
                .when()
                .post("/auth/register")
                .then()
                .statusCode(anyOf(is(200), is(201)));

        // Step 2: Login with the same user
        String loginPayload = """
          {
            "email": "%s",
            "password": "mypassword"
          }
        """.formatted(uniqueEmail);

        Response loginResponse = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();

        String token = loginResponse.jsonPath().getString("token");
        System.out.println("Generated Token: " + token);
    }

    @Test
    public void shouldReturnUnauthorizedOnInvalidLogin() {
        String loginPayload = """
          {
            "email": "invalid_user@test.com",
            "password": "wrongpassword"
          }
        """;

        given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401);
    }
}
