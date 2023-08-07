package ok_http;

import DTO.ContactDTO;
import DTO.ContactListDTO;
import Helpers.HelperBase;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllContacts implements HelperBase {
    String endpoint = "/v1/contacts";
    @Test
    public void getAllContactsPositive() throws IOException {
        Request request = new Request.Builder().url(BASEURL + endpoint).addHeader(authHeader, TOKEN).build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        ContactListDTO contacts = gson.fromJson(response.body().string(), ContactListDTO.class);
        for(ContactDTO contactDTO : contacts.getContacts()){
            System.out.println(contactDTO.getId());
            System.out.println(contactDTO.getEmail());
            System.out.println("**********************************************************************************");
        }
    }
}

