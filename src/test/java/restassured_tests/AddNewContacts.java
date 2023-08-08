package restassured_tests;

import DTO.ContactDTO;
import Helpers.HelperBase;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class AddNewContacts implements HelperBase {
    String endpoint = "/v1/contacts";
    ContactDTO contactDTO;
    String id;

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = BASEURL;
    }

    @Test
    public void addNewContactsPositive(){
        contactDTO = ContactDTO.builder().name("Test").
                lastName("Qa").email("testqa" + i +"@df.sdf").phone(i + "125" + i)
                .address("Tel-Aviv").description("Friend").build();
        given().header(authHeader, TOKEN).body(contactDTO).contentType(ContentType.JSON)
                .when().post(endpoint).then().assertThat().statusCode(200);
    }
}
