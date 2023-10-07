package modulos.component;

import dataFactory.ComponentDataFactory;
import dataFactory.ProductDataFactory;
import dataFactory.UserDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("API Rest Tests of the Component")
public class ComponentTest {
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
    @DisplayName("Test Case #4.1: Validate that registration of a product without components is allowed")
    public void testValidateRegistrationWithoutComponent(){
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token).body(ProductDataFactory.createProductWithoutComponent("Component Product Registration 0"))
        .when()
            .post("/v2/produtos")
        .then()
            .assertThat()
                .body("message",equalTo("Produto adicionado com sucesso"))
                .statusCode(201);
    }

    @Test
    @DisplayName("Test Case #4.2: Validate that registration of 1 component is allowed")
    public void testValidateRegistrationOneComponent(){
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
            .body(ComponentDataFactory.addOneComponent("Component Registration 1", 1))
        .when()
            .post("/v2/produtos/925015/componentes")
        .then()
            .assertThat()
                .body("message",equalTo("Componente de produto adicionado com sucesso"))
                .statusCode(201);
    }

    @Test
    @DisplayName("Test Case #4.3: Validate that registration of 16 components is allowed")
    public void testValidateRegistrationSixteenComponent(){
        int quantityOfComponents = 16;
        int x = 1;
        for(x=1;x<=quantityOfComponents;x++){
            given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ComponentDataFactory.addOneComponent("Component Registration 16."+ x, x))
            .when()
                .post("/v2/produtos/925016/componentes")
            .then()
                .assertThat()
                    .body("message",equalTo("Componente de produto adicionado com sucesso"))
                    .statusCode(201);
        }
    }

    @Test
    @DisplayName("Test Case #4.4: Validate that registration of 17 components is not allowed")
    public void testValidateRegistrationSeventeenComponent(){
        int quantityOfComponents = 17;
        int x = 1;
        for(x=1;x<=quantityOfComponents;x++){
            if(x<=16) {
                given()
                    .contentType(ContentType.JSON)
                    .header("token", this.token)
                    .body(ComponentDataFactory.addOneComponent("Component Registration 17." + x, x))
                .when()
                    .post("/v2/produtos/925017/componentes")
                .then()
                    .assertThat()
                        .body("message", equalTo("Componente de produto adicionado com sucesso"))
                        .statusCode(201);
            }else {
                given()
                    .contentType(ContentType.JSON)
                    .header("token", this.token)
                    .body(ComponentDataFactory.addOneComponent("Componente Registro 17." + x, x))
                .when()
                    .post("/v2/produtos/924965/componentes")
                .then()
                    .assertThat()
                        .body("error", equalTo("O número máximo de componentes é 16"))
                        .statusCode(422);
            }
        }
    }

    @Test
    @DisplayName("Test Case #5.1: Validate the deletion of a component")
    public void testValidateDeletionComponent(){
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
        .when()
            .delete("/v2/produtos/925018/componentes/947627")
        .then()
            .assertThat()
                .statusCode(204);
    }
}


