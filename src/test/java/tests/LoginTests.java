package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import models.UserGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static specs.Specs.*;

@Epic("API Reqres")
@Feature("User authorization")
public class LoginTests {

    @Test
    @Owner("a.denyushina")
    @DisplayName("Successful login")
    void successLoginTest() {

        UserGenerator user = UserGenerator.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();

        given()
                .spec(request)
                .body(user)
                .when()
                .post("/login")
                .then()
                .spec(response200)
                .body("token", is(notNullValue()));
    }

    @Test
    @Owner("a.denyushina")
    @DisplayName("Unsuccessful login with incorrect user data")
    void unSuccessLoginTest() {

        UserGenerator user = UserGenerator.builder()
                .email("test.holt@reqres.in")
                .password("pass")
                .build();

        given()
                .spec(request)
                .body(user)
                .when()
                .post("/login")
                .then()
                .spec(response400)
                .body("error", is("user not found"));
    }
}
