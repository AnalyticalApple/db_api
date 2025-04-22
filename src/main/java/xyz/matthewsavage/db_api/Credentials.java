package xyz.matthewsavage.db_api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Credentials {

    private static final Map<String, Credential> CREDENTIALS = populateCredentials();

    public static Map<String, Credential> getCREDENTIALS() {
        return CREDENTIALS;
    }

    private static Map<String, Credential> populateCredentials() {
        JsonObject[] jsonObject;
        try {
            jsonObject = new Gson().fromJson(Files.readString(Paths.get("/home/apps/credentials.json")), JsonObject[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Arrays.stream(jsonObject).collect(Collectors.toMap(object -> object.get("sys").getAsString(), object -> new Credential(object.get("user").getAsString(), object.get("pass").getAsString()), (a, b) -> b));
    }
}

record Credential(String LOGIN, String PASSWORD) {

}
