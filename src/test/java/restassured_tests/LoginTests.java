package restassured_tests;

import DTO.AuthenticationRequestDTO;
import DTO.AuthenticationResponseDTO;
import DTO.ErrorDTO;
import Helpers.HelperBase;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;

public class LoginTests implements HelperBase {
    String endpoint = "/v1/user/login/usernamepassword";

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = BASEURL;
    }

    @Test
    public void loginPositive(){
        AuthenticationRequestDTO requestDTO = AuthenticationRequestDTO.builder()
                .username(LOGIN).password(PASSWORD).build();
        AuthenticationResponseDTO responseDTO = given().body(requestDTO).contentType(ContentType.JSON).when().post(endpoint).
                then().assertThat().statusCode(200).extract()
                .as(AuthenticationResponseDTO.class);
        System.out.println("Token: " + responseDTO.getToken());
    }

    @Test
    public void loginNegativeWrongEmail(){
        AuthenticationRequestDTO requestDTO = AuthenticationRequestDTO.builder()
                .username("qwertygm.com").password(PASSWORD).build();
        ErrorDTO errorDTO = given().body(requestDTO).contentType(ContentType.JSON).when().post(endpoint).
                then().assertThat().statusCode(401).extract().as(ErrorDTO.class);
        System.out.println(errorDTO.getMessage());
    }
}
