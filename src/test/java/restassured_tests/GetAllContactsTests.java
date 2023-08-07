package restassured_tests;

import DTO.ContactDTO;
import DTO.ContactListDTO;
import Helpers.HelperBase;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class GetAllContactsTests implements HelperBase {

    String endpoint = "/v1/contacts";

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = BASEURL;
    }

    @Test
    public void getAllContactsPositive(){
        ContactListDTO listDTO = given().header(authHeader, TOKEN).when().get(endpoint).then()
                .assertThat().statusCode(200).extract().as(ContactListDTO.class);
        for(ContactDTO contactDTO : listDTO.getContacts()){
            System.out.println(contactDTO.getId());
            System.out.println(contactDTO.getEmail());
            System.out.println("===================================");
        }

    }
}