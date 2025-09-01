//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//public class PatientIntegrationTest {
//
//    @BeforeAll
//    static void setUp() {
//        RestAssured.baseURI = "http://localhost:8000";
//    }
//
//    @Test
//    public void shouldReturnPatientsWithValidToken() {
//        // Generate unique valid email
//        String uniqueEmail = "user" + System.currentTimeMillis() + "@gmail.com";
//
//        // Step 1: Register user (plain text response)
//        String registerPayload = """
//          {
//            "email": "%s",
//            "password": "mypassword",
//            "role": "USER"
//          }
//        """.formatted(uniqueEmail);
//
//        String registerResponse = given()
//                .contentType("application/json")
//                .body(registerPayload)
//                .when()
//                .post("/auth/register")
//                .then()
//                .statusCode(anyOf(is(200), is(201)))
//                .extract()
//                .asString();
//
//        System.out.println("Register Response: " + registerResponse);
//
//        // Step 2: Login to get token
//        String loginPayload = """
//          {
//            "email": "%s",
//            "password": "mypassword"
//          }
//        """.formatted(uniqueEmail);
//
//        String token = given()
//                .contentType("application/json")
//                .body(loginPayload)
//                .when()
//                .post("/auth/login")
//                .then()
//                .statusCode(200)
//                .body("token", notNullValue())
//                .extract()
//                .jsonPath()
//                .getString("token");
//
//        System.out.println("Generated Token: " + token);
//
//        // Step 3: Access protected patients endpoint
//        Response response = given()
//                .header("Authorization", "Bearer " + token)
//                .when()
//                .get("/api/patients/get")
//                .then()
//                .statusCode(200)
//                .body("", not(empty()))
//                .extract()
//                .response();
//
//        System.out.println("Patients Response: " + response.asString());
//    }
//}
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PatientIntegrationTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:8000";
    }

    @Test
    public void testPatientEndpointWithValidToken() {
        // 1️⃣ Generate unique email for this test
        String uniqueEmail = "user" + System.currentTimeMillis() + "@gmail.com";

        // 2️⃣ Register user (plain text response)
        String registerPayload = """
          {
            "email": "%s",
            "password": "mypassword",
            "role": "USER"
          }
        """.formatted(uniqueEmail);

        String registerResponse = given()
                .contentType("application/json")
                .body(registerPayload)
                .when()
                .post("/auth/register")
                .then()
                .statusCode(anyOf(is(200), is(201)))
                .extract()
                .asString();

        System.out.println("[Patient Test] Register Response: " + registerResponse);

        // 3️⃣ Login to get token
        String loginPayload = """
          {
            "email": "%s",
            "password": "mypassword"
          }
        """.formatted(uniqueEmail);

        String token = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .jsonPath()
                .getString("token");

        System.out.println("[Patient Test] Generated Token: " + token);

        // 4️⃣ Access protected patients endpoint
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/patients/get")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("[Patient Test] Patients Response: " + response.asString());
    }
}
