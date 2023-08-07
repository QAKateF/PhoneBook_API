package Helpers;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Random;

public interface HelperBase {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoicXdlcnR5QGdtLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjkxOTI1NTU2LCJpYXQiOjE2OTEzMjU1NTZ9.SMyQuo6LN23P9d9eMrMmQWh4c5kNMHSv6RI8gr2bkWk";
    String BASEURL = "https://contactapp-telran-backend.herokuapp.com";
    String authHeader = "Authorization";
    int i = new Random().nextInt(1000) + 1000;
    String LOGIN = "qwerty@gm.com";
    String PASSWORD = "abcD123$";
}
