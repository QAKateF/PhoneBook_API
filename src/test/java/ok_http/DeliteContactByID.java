package ok_http;

import DTO.ContactDTO;
import DTO.ContactResponseDTO;
import Helpers.HelperBase;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeliteContactByID extends AddNewContact {
    String endpoint = "/v1/contacts";

    @BeforeMethod
    public void precondition() throws Exception {
        addNewContactPositive();
    }

    @Test
    public void deleteContactByIDPositive() throws Exception {
        Request request = new Request.Builder().url(BASEURL + endpoint + "/" + id).addHeader(authHeader, TOKEN)
                .delete().build();
        Response response = client.newCall(request).execute();
        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
        Assert.assertTrue(response.isSuccessful());
        String message = contactResponseDTO.getMessage();
        System.out.println(message);
    }
}
