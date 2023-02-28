package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Specs.*;

@Epic("API Reqres")
@Feature("Actions with users")
public class UsersTests {

    @Test
    @Owner("a.denyushina")
    @DisplayName("Get user data by id")
    void getUserByIdTest() {

        UserResponseData responseData = given()
                .spec(request)
                .when()
                .pathParam("id", "11")
                .get("/users/{id}")
                .then()
                .spec(response200)
                .extract().as(UserResponseData.class);

        assertThat(responseData.getData().getId()).isEqualTo(11);
        assertThat(responseData.getData().getEmail()).isEqualTo("george.edwards@reqres.in");
        assertThat(responseData.getData().getFirstName()).isEqualTo("George");
        assertThat(responseData.getData().getLastName()).isEqualTo("Edwards");
        assertThat(responseData.getData().getAvatar()).isEqualTo("https://reqres.in/img/faces/11-image.jpg");
    }

    @Test
    @Owner("a.denyushina")
    @DisplayName("Delete user by id")
    void deleteUserByIdTest() {

        given()
                .spec(request)
                .when()
                .pathParam("id", "2")
                .delete("/users/{id}")
                .then()
                .spec(response204);
    }
}
