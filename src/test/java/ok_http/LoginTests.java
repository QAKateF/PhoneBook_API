package ok_http;

import DTO.*;
import Helpers.HelperBase;
import okhttp3.*;
import org.testng.*;
import org.testng.annotations.*;
import java.io.IOException;

public class LoginTests implements HelperBase {
    String endpoint = "/v1/user/login/usernamepassword";
    @Test
    public void loginPositive() throws IOException {
        AuthenticationRequestDTO requestDTO = AuthenticationRequestDTO.builder()
                .username(LOGIN).password(PASSWORD).build();
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url(BASEURL + endpoint)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            AuthenticationResponseDTO responseDTO = gson.fromJson(response.body().string(), AuthenticationResponseDTO.class);
            System.out.println(responseDTO.getToken());
            System.out.println("Response code is: " + response.code());
            Assert.assertTrue(response.isSuccessful());
        } else{
            System.out.println("Response code is: " + response.code());
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
            System.out.println(errorDTO.getStatus() + " " + errorDTO.getMessage() + " " + errorDTO.getError());
            Assert.assertTrue(response.isSuccessful());
        }
    }
}

// eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoicXdlcnR5QGdtLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjkxOTI0MTY5LCJpYXQiOjE2OTEzMjQxNjl9.m2R0QPPc6GqeDr85JZLFfGRnRWKBvdU8AlPx7uVAmLU