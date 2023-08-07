package ok_http;

import DTO.ContactDTO;
import DTO.ContactResponseDTO;
import Helpers.HelperBase;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewContact implements HelperBase {
    String endpoint = "/v1/contacts";
    String id;
    @Test
    public void addNewContactPositive() throws Exception {
        ContactDTO contactDTO = ContactDTO.builder().name("Test").
                lastName("Qa").email("testqa" + i +"@df.sdf").phone(i + "125" + i)
                .address("Tel-Aviv").description("Friend").build();
        RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);
        Request request = new Request.Builder().url(BASEURL + endpoint).addHeader(authHeader, TOKEN)
                .post(requestBody).build();
        Response response = client.newCall(request).execute();
        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
        Assert.assertTrue(response.isSuccessful());
        String message = contactResponseDTO.getMessage();
        System.out.println(message);
        id = message.substring(message.lastIndexOf(" ")+ 1);
        System.out.println("ID: " + id);
    }

}
