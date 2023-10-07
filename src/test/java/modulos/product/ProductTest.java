package modulos.product;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import dataFactory.ProductDataFactory;
import dataFactory.UserDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("API Rest Tests of the Product")
public class ProductTest {
    private String token;

    @BeforeEach
    public void beforeEach(){
        //setting up the Lojinha API
        baseURI = "http://165.227.93.41";
        basePath = "/lojinha";

        //getting the user's token
        this.token =
                given()
                    .contentType(ContentType.JSON)
                    .body(UserDataFactory.inputUserLoginAndUserPassword())
                .when()
                    .post("/v2/login")
                .then()
                    .extract()
                    .path("data.token");

    }

    @Test
    @DisplayName("Test Case #1.1: Validate that the registration of a product with a value equal to R$0.00 is not allowed")
    public void testValidateZeroNotAllowedValueRegistrationProduct(){
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
            .body(ProductDataFactory.createProductWithValueEqualsTo("Product Registration 000",0.00))
        .when()
            .post("/v2/produtos")
        .then()
            .assertThat()
                .body("error",equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }

    @Test
    @DisplayName("Test Case #1.2: Validate that the registration of a product with a value equal to R$0.01 is permitted")
    public void testValidateLowerLimitValueRegistrationProduct(){
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
            .body(ProductDataFactory.createProductWithValueEqualsTo("Product Registration 001", 0.01))
        .when()
            .post("/v2/produtos")
        .then()
            .assertThat()
                .body("message",equalTo("Produto adicionado com sucesso"))
                .statusCode(201);
    }

    @Test
    @DisplayName("Test Case #1.3: Validate that the registration of a product with a value equal to R$7000.00 is permitted")
    public void testValidateUpperLimitValueRegistrationProduct(){
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
            .body(ProductDataFactory.createProductWithValueEqualsTo("Product Registration 700000",7000.00))
        .when()
            .post("/v2/produtos")
        .then()
            .assertThat()
                .body("message",equalTo("Produto adicionado com sucesso"))
                .statusCode(201);
    }

    @Test
    @DisplayName("Test Case #1.4: Validate that the registration of a product with a value equal to R$ 7000.01 is not allowed")
    public void testValidateSevenThousandAndOneNotAllowedValueRegistrationProduct(){
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProductDataFactory.createProductWithValueEqualsTo("Product Registration 700001",7000.01))
        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()
                    .body("error",equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
    }

    @Test
    @DisplayName("Test Case #2.1: Validate that changing a product with a value equal to R$0.00 is not allowed")
    public void testValidateZeroNotAllowedValueUpdatedProduct(){
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
            .body(ProductDataFactory.updateProductWithValueEqualsTo("Updated Product 000",0.00))
        .when()
            .put("/v2/produtos/925010")
        .then()
            .assertThat()
                .body("error",equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }

    @Test
    @DisplayName("Test Case #2.2: Validate that the change of a product with a value equal to R$ 0.01 is allowed")
    public void testValidateLowerLimitValueUpdatedProduct(){
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
            .body(ProductDataFactory.updateProductWithValueEqualsTo("Updated Product 001",0.01))
        .when()
            .put("/v2/produtos/925011")
        .then()
            .assertThat()
                .body("message",equalTo("Produto alterado com sucesso"))
                .statusCode(200);
    }

    @Test
    @DisplayName("Test Case #2.3: Validate that the change of a product with a value equal to R$ 7000.00 is allowed")
    public void testValidateUpperLimitValueUpdatedProduct(){
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
            .body(ProductDataFactory.updateProductWithValueEqualsTo("Updated Product 700000",7000.00))
        .when()
            .put("/v2/produtos/925012")
        .then()
            .assertThat()
                .body("message",equalTo("Produto alterado com sucesso"))
                .statusCode(200);
    }

    @Test
    @DisplayName("Test Case #2.4: Validate that the alteration of a product with a value equal to R$ 7000.01 is not allowed")
    public void testValidateSevenThousandAndOneNotAllowedValueUpdatedProduct(){
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
            .body(ProductDataFactory.updateProductWithValueEqualsTo("Updated Product 700001",7000.01))
        .when()
            .put("/v2/produtos/925013")
        .then()
            .assertThat()
                .body("error",equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }

    @Test
    @DisplayName("Test Case #3.1: Validate the deletion of a product")
    public void testValidateDeletionProduct(){
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
        .when()
            .delete("/v2/produtos/925049")
        .then()
            .assertThat()
                .statusCode(204);
    }
}
