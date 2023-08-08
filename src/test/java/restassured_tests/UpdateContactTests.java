package restassured_tests;

import DTO.ContactDTO;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class UpdateContactTests extends AddNewContacts {
    String endpoint = "/v1/contacts";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASEURL;
        addNewContactsPositive();
        String message = given().header(authHeader, TOKEN).body(contactDTO).contentType(ContentType.JSON)
                .when().post(endpoint).then().assertThat().statusCode(200).extract()
                .path("message");
        id = message.substring(message.lastIndexOf(' ') + 1);
    }

    @Test
    public void updateContactPositive() {
        contactDTO.setId(id);
        contactDTO.setName("Test_UPDATE");
        given().header(authHeader, TOKEN).body(contactDTO).contentType(ContentType.JSON)
                .when().put(endpoint).then().assertThat().statusCode(200)
                .assertThat().body("message", containsString("Contact was updated"));
    }
}
