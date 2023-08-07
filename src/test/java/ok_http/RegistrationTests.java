package ok_http;

import DTO.AuthenticationRequestDTO;
import DTO.AuthenticationResponseDTO;
import DTO.ErrorDTO;
import Helpers.HelperBase;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests implements HelperBase {
    String endpoint = "/v1/user/registration/usernamepassword";

    @Test
    public void registrationPositive() throws IOException {
        AuthenticationRequestDTO requestDTO = AuthenticationRequestDTO.builder()
                .username("qwerty" + i + "@gm.com").password("abceD" + i+123 + "$").build();
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

    @Test
    public void registrationNegativePassword() throws IOException {
        AuthenticationRequestDTO requestDTO = AuthenticationRequestDTO.builder()
                .username("qwerty" + i + "@gm.com").password("abceD" + i+123).build();
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url(BASEURL + endpoint)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println("Response code is: " + response.code());
        ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
        System.out.println(errorDTO.getStatus() + " " + errorDTO.getMessage() + " " + errorDTO.getError());
        Assert.assertTrue(!response.isSuccessful());
        }

    @Test
    public void registrationNegativeMail() throws IOException {
        AuthenticationRequestDTO requestDTO = AuthenticationRequestDTO.builder()
                .username("qwerty" + i + "gm.com").password("abceD" + i+123 + "$").build();
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url(BASEURL + endpoint)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println("Response code is: " + response.code());
        ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
        System.out.println(errorDTO.getStatus() + " " + errorDTO.getMessage() + " " + errorDTO.getError());
        Assert.assertTrue(!response.isSuccessful());
    }

    }
