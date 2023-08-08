package restassured_tests;

import DTO.AuthenticationRequestDTO;
import Helpers.HelperBase;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RegistrationTests implements HelperBase {
    String endpoint = "/v1/user/registration/usernamepassword";

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = BASEURL;
    }

    @Test
    public void RegistrationPositive(){
        AuthenticationRequestDTO authRequest = AuthenticationRequestDTO.builder()
                .username("QA38_" + i + "@dfg.rt").password("12345Rty$").build();
        String token = given().body(authRequest).contentType(ContentType.JSON)
                .when().post(endpoint).then().assertThat().statusCode(200)
                .extract().path("token");
        System.out.println("Token: " + token);
    }

    @Test
    public void RegistrationNegtive(){
        AuthenticationRequestDTO authRequest = AuthenticationRequestDTO.builder()
                .username("QA38_" + i + "dfg.rt").password("12345Rty$").build();
        given().body(authRequest).contentType(ContentType.JSON)
                .when().post(endpoint).then().assertThat().statusCode(400)
                .assertThat().body("message.username", containsString("must be a well-formed email address"));
    }
}
